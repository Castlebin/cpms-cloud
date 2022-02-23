package com.cpms.lowcode.modules.dynamic.manage;

import com.cpms.lowcode.modules.dynamic.bo.DynamicDbInfoBO;
import com.cpms.lowcode.modules.dynamic.dto.DbTableDTO;
import com.cpms.lowcode.modules.dynamic.dto.QueryTableFieldDTO;
import com.cpms.lowcode.modules.dynamic.dto.TableFieldDTO;
import com.cpms.lowcode.modules.dynamic.mapper.DynamicDbMapper;
import com.cpms.lowcode.modules.dynamic.vo.DbTableVO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author gulang
 * @Description:
 * @time: 2022/1/27 21:45
 */
@Component
public class DynamicDbManage {

    @Resource
    private DynamicDbMapper dynamicDBMapper;

    public boolean testDb(DynamicDbInfoBO dynamicDbInfoBO){
        return  true;
    }

    public List<DbTableVO> selectInformationTables(DynamicDbInfoBO dynamicDbInfoBO, String dbName){
        return dynamicDBMapper.selectInformationTables(dbName);
    }

    public List<TableFieldDTO> queryTableFields(DynamicDbInfoBO dynamicDbInfoBO, QueryTableFieldDTO queryTableFieldDTO){
        return dynamicDBMapper.queryTableFields(queryTableFieldDTO);
    }
}
