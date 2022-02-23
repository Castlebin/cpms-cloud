package com.cpms.lowcode.modules.dynamic.mapper;

import com.cpms.lowcode.modules.dynamic.dto.DbTableDTO;
import com.cpms.lowcode.modules.dynamic.dto.QueryTableFieldDTO;
import com.cpms.lowcode.modules.dynamic.dto.TableFieldDTO;
import com.cpms.lowcode.modules.dynamic.vo.DbTableVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2022/1/28 15:41
 */
public interface DynamicDbMapper {

    /**
     * 查询指定库中所有表
     */
    List<DbTableVO> selectInformationTables(@Param("dbName") String dbName);

    /**
     * 查询表字段
     * @param queryTableFieldDTO
     * @return
     */
    List<TableFieldDTO> queryTableFields(QueryTableFieldDTO queryTableFieldDTO);
}
