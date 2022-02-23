package com.cpms.lowcode.modules.gen.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpms.common.constant.CommonConstant;
import com.cpms.framework.common.core.base.BasePageVO;
import com.cpms.framework.common.utils.CsBeanUtil;
import com.cpms.framework.mybatis.utils.CsPageRespUtil;
import com.cpms.lowcode.modules.gen.dto.ProjectDetailDTO;
import com.cpms.lowcode.modules.gen.dto.QueryProjectDTO;
import com.cpms.lowcode.modules.gen.entity.ProjectDbEntity;
import com.cpms.lowcode.modules.gen.entity.ProjectEntity;
import com.cpms.lowcode.modules.gen.mapper.ProjectMapper;
import com.cpms.lowcode.modules.gen.service.IProjectDbService;
import com.cpms.lowcode.modules.gen.service.IProjectService;
import com.cpms.lowcode.modules.gen.vo.ProjectDetailVO;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @description:
 * @author: gulang
 * @time: 2022/1/25 10:37
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, ProjectEntity>  implements IProjectService {
    @Resource
    private IProjectDbService projectDbService;

    @Override
    public BasePageVO<ProjectDetailVO> listProject(QueryProjectDTO queryProjectDTO) {
        List<ProjectEntity> list = Lists.newArrayList();
        int count = getBaseMapper().listProjectCount(queryProjectDTO);
        if(count > 0){
            list = getBaseMapper().listProject(queryProjectDTO);
        }
        return CsPageRespUtil.buildPageResult(list, count, ProjectDetailVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addProject(ProjectDetailDTO projectDTO) {
        ProjectEntity projectEntity = new ProjectEntity();
        CsBeanUtil.copyProperties(projectDTO,projectEntity);
        this.save(projectEntity);
        List<ProjectDbEntity> projectDbEntities = CsBeanUtil.copyList(projectDTO.getDbDto(), ProjectDbEntity.class);
        projectDbEntities.forEach(e->e.setProjectId(projectEntity.getProjectId()));
        return projectDbService.saveBatch(projectDbEntities);
    }

    @Override
    public boolean updateProject(ProjectDetailDTO projectDTO) {
        ProjectEntity projectEntity = new ProjectEntity();
        CsBeanUtil.copyProperties(projectDTO,projectEntity);
        this.updateById(projectEntity);
        List<ProjectDbEntity> projectDbEntities = CsBeanUtil.copyList(projectDTO.getDbDto(), ProjectDbEntity.class);
        // 先删除在保存
        projectDbService.remove(Wrappers.<ProjectDbEntity>lambdaUpdate().eq(ProjectDbEntity::getProjectId,projectDTO.getProjectId()));
        return projectDbService.saveBatch(projectDbEntities);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delProject(ProjectDetailDTO projectDTO) {
        LambdaUpdateWrapper<ProjectEntity> updateWrapper = Wrappers.<ProjectEntity>lambdaUpdate()
                .set(ProjectEntity::getDelFlag, CommonConstant.DEL_FLAG_DELETED)
                .eq(ProjectEntity::getProjectId,projectDTO.getProjectId());
        this.update(updateWrapper);
        LambdaUpdateWrapper<ProjectDbEntity> updateWrapper2 = Wrappers.<ProjectDbEntity>lambdaUpdate()
                .set(ProjectDbEntity::getDelFlag, CommonConstant.DEL_FLAG_DELETED)
                .eq(ProjectDbEntity::getProjectId,projectDTO.getProjectId());
        return projectDbService.update(updateWrapper2);
    }
}
