package com.cpms.lowcode.modules.gen.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cpms.framework.common.utils.*;
import com.cpms.lowcode.common.enums.DbTypeEnum;
import com.cpms.lowcode.common.enums.TplGenParamEnum;
import com.cpms.lowcode.modules.dynamic.dto.DbTableDTO;
import com.cpms.lowcode.modules.dynamic.dto.QueryTableFieldDTO;
import com.cpms.lowcode.modules.dynamic.dto.TableFieldDTO;
import com.cpms.lowcode.modules.dynamic.service.IDynamicDbService;
import com.cpms.lowcode.modules.gen.dto.*;
import com.cpms.lowcode.modules.gen.entity.ProjectDbEntity;
import com.cpms.lowcode.modules.gen.entity.ProjectEntity;
import com.cpms.lowcode.modules.gen.entity.TableColumnEntity;
import com.cpms.lowcode.modules.gen.entity.TableEntity;
import com.cpms.lowcode.modules.gen.service.*;
import com.cpms.lowcode.utils.CsDownLoadFileUtil;
import com.cpms.lowcode.utils.CsConvertUtil;
import com.google.common.collect.Maps;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.VelocityContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.zip.ZipOutputStream;

/**
 * @description:
 * @author: gulang
 * @time: 2022/1/27 10:22
 */
@Service
public class GenCodeServiceImpl implements IGenCodeService {
    @Resource
    private IProjectService projectService;

    @Resource
    private IProjectDbService projectDbService;

    @Resource
    private IDynamicDbService dynamicDbService;

    @Resource
    private ITableService tableService;

    @Resource
    private ITableColumnService tableColumnService;

    @Override
    public void genScaffold(Long projectId, HttpServletResponse response) {
        GenScaffoldParamDTO genScaffoldParamDTO = GenScaffoldParamDTO.builder()
                .genDate(CsDateUtil.dateFormat(new Date()))
                .author(CsSecureUtil.userAccount())
                .build();
        ProjectEntity projectEntity = projectService.getById(projectId);
        CsBeanUtil.copyNotNull(projectEntity,genScaffoldParamDTO);
        LambdaQueryWrapper<ProjectDbEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(ProjectDbEntity::getProjectId,projectId);
        queryWrapper.eq(ProjectDbEntity::getDelFlag,0);
        List<ProjectDbEntity> dbEntities = projectDbService.list(queryWrapper);
        List<DbParamDTO> dbList = Lists.newArrayList();
        initDb(dbList,dbEntities);
        dbList.sort(Comparator.comparing(DbParamDTO::getDbPrimary).reversed());
        genScaffoldParamDTO.setDbList(dbList);
        genScaffoldParamDTO.setContextPath(genScaffoldParamDTO.getContextPath().startsWith("/")
                ? genScaffoldParamDTO.getContextPath() : "/"+genScaffoldParamDTO.getContextPath());
        genScaffoldParamDTO.setStartupName(CsConvertUtil.formatClassName(genScaffoldParamDTO.getProjectName()));
        downloadWithZip(genScaffoldParamDTO,1,genScaffoldParamDTO.getProjectName(),response);
    }

    private void downloadWithZip(Object paramDTO,Integer type,String zipName,HttpServletResponse response){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        Map<String, String> replaceValue = Maps.newHashMap();
        if(type == 1){
            GenScaffoldParamDTO genScaffoldParamDTO =   (GenScaffoldParamDTO)paramDTO;
            replaceValue.put("startupName" ,genScaffoldParamDTO.getStartupName());
            replaceValue.put("packageName" ,genScaffoldParamDTO.getPackageName());
            renderTpl(zipOutputStream,paramDTO,type,replaceValue);
        }else if(type==2){
            List<GenTableFunDTO> genTableFunDTOS = Convert.convert(new TypeReference<List<GenTableFunDTO>>(){}, paramDTO);
            genTableFunDTOS.forEach(e->{
                replaceValue.put("packageName",e.getPackageName());
                replaceValue.put("moduleName",e.getModuleName());
                replaceValue.put("className",e.getClassName());
                renderTpl(zipOutputStream,e,type,replaceValue);
            });
        }
        IOUtils.closeQuietly(zipOutputStream);
        outputStream.toByteArray();
        try {
            CsDownLoadFileUtil.DownloadGenCode(response, outputStream.toByteArray(), zipName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 渲染模板
     */
    private void renderTpl(ZipOutputStream zipOutputStream,Object paramDTO,Integer type,Map<String, String> replaceMap) {
        CsDownLoadFileUtil.initVelocity();
        VelocityContext velocityContext = new VelocityContext(CsBeanUtil.beanToMap(paramDTO));
        for (Map.Entry<String, File> entry : TplGenParamEnum.TemplateTypeEnum.getByType(type).getTplFilePathMap().entrySet()) {
            // 文件名包含路径
            String filePathName = entry.getKey();
            File file = entry.getValue();
            String fileBaseName;
            fileBaseName = replaceFileNameParam(filePathName,replaceMap);
            CsDownLoadFileUtil.LoadZipOutputStream(velocityContext,
                    file,
                    fileBaseName,
                    zipOutputStream);
        }
    }

    private String replaceFileNameParam(String replaceTpl,Map<String, String> replaceMap){
        Map<String, String> replaceValue = Maps.newHashMap();
        String packageName = replaceMap.get("packageName").replaceAll("\\." , Matcher.quoteReplacement(File.separator));
        replaceValue.put("packageName",packageName);
        return CsStringUtil.removeEnd(CsStringUtil.strSubReplace(replaceMap,replaceTpl), ".vm");
    }

    private void initDb(List<DbParamDTO> dbList,List<ProjectDbEntity> dbEntities){
        dbEntities.forEach(e->{
            Map<String, String> replaceValue = Maps.newHashMap();
            replaceValue.put("dbIpPort" , e.getDbIp()+":"+e.getDbPort());
            replaceValue.put("dbName" ,e.getDbName());
            DbParamDTO dbParamDTO = DbParamDTO.builder()
                    .dbPrimary(e.getDbPrimary())
                    .userName(e.getDbUser())
                    .password(e.getDbPassword())
                    .dbName(CsConvertUtil.formatName(e.getDbName()))
                    .dbUrl(CsStringUtil.strSubReplace(replaceValue, DbTypeEnum.MYSQL.getUrl()))
                    .build();
            dbList.add(dbParamDTO);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void genFun(GenFuncDTO genFuncDTO, HttpServletResponse response) {
        saveTableField(genFuncDTO);
        String createTime = CsDateUtil.dateFormat(new Date());
        String author     = CsSecureUtil.userAccount();
        ProjectEntity projectEntity = projectService.getById(genFuncDTO.getProjectId());
        List<TableEntity> tableEntities = queryTables(genFuncDTO);
        List<TableColumnEntity> tableColumnEntities = tableColumnService.list(Wrappers.<TableColumnEntity>lambdaQuery()
                .in(TableColumnEntity::getTableId,
                        tableEntities.stream().map(TableEntity::getTableId).collect(Collectors.toList())));
        Map <Long,List < TableColumnEntity >> tableColumnMap = tableColumnEntities.stream().collect(Collectors.groupingBy(TableColumnEntity::getTableId));
        List<GenTableFunDTO> genTableFunDTOS = tableEntities.stream().map(e -> {
            GenTableFunDTO genTableFunDTO = GenTableFunDTO.builder()
                    .genDate(createTime).author(author).packageName(projectEntity.getPackageName())
                    .build();
            genTableFunDTO.setClassName(e.getClassName());
            genTableFunDTO.setModuleName(e.getModuleName());
            genTableFunDTO.setClassAlias(CsConvertUtil.firstLowerCase(e.getClassName()));
            genTableFunDTO.setTableName(e.getTableName());
            genTableFunDTO.setColumnList(CsBeanUtil.copyList(tableColumnMap.get(e.getTableId()),TableColumnDTO.class));
            return genTableFunDTO;
        }).collect(Collectors.toList());
        downloadWithZip(genTableFunDTOS,2,projectEntity.getProjectName(),response);
    }

    private void saveTableField(GenFuncDTO genFuncDTO){
        List<DbTableDTO> tables = filterTable(genFuncDTO);
        if(!CsCollectionUtil.isEmpty(tables)){
            QueryTableFieldDTO queryTableFieldDTO = QueryTableFieldDTO.builder()
                    .tableNames(tables.stream().map(DbTableDTO::getTableName).collect(Collectors.toList()))
                    .dbId(genFuncDTO.getDbId())
                    .dbName(genFuncDTO.getDbName())
                    .build();
            List<TableFieldDTO> tableFieldDTOS = dynamicDbService.queryTableFields(queryTableFieldDTO);
            List<TableEntity> tableEntities = buildTableInfo(tables,genFuncDTO.getDbId());
            tableService.saveBatch(tableEntities);
            tableEntities.forEach(e->{
                tableFieldDTOS.forEach(v->{
                    if(e.getTableName().equals(v.getTableName())){
                        v.setTableId(e.getTableId());
                    }
                });
            });
            List<TableColumnEntity> tableColumnEntities = buildTableColumn(tableFieldDTOS);
            tableColumnService.saveBatch(tableColumnEntities);
        }
    }


    private List<TableEntity> queryTables(GenFuncDTO genFuncDTO){
        List<String> tableNames = genFuncDTO.getTableList().stream().map(DbTableDTO::getTableName).collect(Collectors.toList());
        LambdaQueryWrapper<TableEntity> tableQueryWrapper = Wrappers.lambdaQuery();
        tableQueryWrapper.eq(TableEntity::getDbId,genFuncDTO.getDbId());
        tableQueryWrapper.in(TableEntity::getTableName,tableNames);
        return tableService.list(tableQueryWrapper);
    }
    /**
     * // 过滤出已经存在的表信息，
     * @param genFuncDTO
     * @return
     */
    private List<DbTableDTO> filterTable(GenFuncDTO genFuncDTO){
        List<String> tableEntities = queryTables(genFuncDTO).stream().map(TableEntity::getTableName).collect(Collectors.toList());
        return genFuncDTO.getTableList().stream().filter(e-> !tableEntities.contains(e.getTableName())).collect(Collectors.toList());
    }

    private List<TableEntity> buildTableInfo(List<DbTableDTO> tables, Long dbId){
        if(!CsCollectionUtil.isEmpty(tables)){
            return tables.stream().map(e->{
                TableEntity tableEntity = new TableEntity();
                tableEntity.setDbId(dbId);
                tableEntity.setTableComment(e.getTableComment());
                tableEntity.setTableName(e.getTableName());
                tableEntity.setClassName(CsConvertUtil.formatClassName(e.getTableName()));
                tableEntity.setModuleName(e.getTableName().replaceAll("_",""));
                return tableEntity;
            }).collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }

    private List<TableColumnEntity> buildTableColumn(List<TableFieldDTO> tableFieldDTOS){
        if(!CsCollectionUtil.isEmpty(tableFieldDTOS)){
          return  tableFieldDTOS.stream().map(e->{
              TableColumnEntity tableColumnEntity = new TableColumnEntity();
              tableColumnEntity.setTableId(e.getTableId());
              tableColumnEntity.setColumnComment(e.getColumnComment());
              tableColumnEntity.setColumnName(e.getColumnName());
              tableColumnEntity.setColumnType(e.getDataType());
              tableColumnEntity.setIfPk("PRI".equals(e.getColumnKey()) ? 1 : 0);
              tableColumnEntity.setIfIncrement("PRI".equals(e.getColumnKey())
                      &&  CsStringUtil.isNotBlank(e.getExtra())
                      && "auto_increment".equals(e.getExtra().toLowerCase()) ? 1 : 0);
              tableColumnEntity.setJavaType(CsConvertUtil.toJavaType(e.getDataType()));
              tableColumnEntity.setJavaField(CsConvertUtil.formatFieldName(e.getColumnName()));
              return  tableColumnEntity;
            }).collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean syncField(SyncFieldDTO syncFieldDTO) {
        QueryTableFieldDTO queryTableFieldDTO = QueryTableFieldDTO.builder()
                .tableNames(Arrays.asList(syncFieldDTO.getTableName()))
                .dbId(syncFieldDTO.getDbId())
                .dbName(syncFieldDTO.getDbName())
                .build();
        List<TableFieldDTO> tableFieldDTOS = dynamicDbService.queryTableFields(queryTableFieldDTO);
        tableFieldDTOS.forEach(e->{
            e.setTableId(syncFieldDTO.getTableId());
        });
        List<TableColumnEntity> tableColumnEntities = buildTableColumn(tableFieldDTOS);
        tableColumnService.getBaseMapper().delete(Wrappers.<TableColumnEntity>lambdaQuery().eq(TableColumnEntity::getTableId, syncFieldDTO.getTableId()));
        tableColumnService.saveBatch(tableColumnEntities);
        return true;
    }
}
