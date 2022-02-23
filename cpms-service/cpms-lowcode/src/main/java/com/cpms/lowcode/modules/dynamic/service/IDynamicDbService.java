package com.cpms.lowcode.modules.dynamic.service;


import com.cpms.lowcode.modules.dynamic.dto.DynamicDbDTO;
import com.cpms.lowcode.modules.dynamic.dto.QueryTableFieldDTO;
import com.cpms.lowcode.modules.dynamic.dto.TableFieldDTO;
import com.cpms.lowcode.modules.dynamic.vo.DbTableVO;

import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2022/1/28 15:44
 */
public interface IDynamicDbService {
    boolean testDb(DynamicDbDTO dynamicDbDTO);

    List<DbTableVO> selectInformationTables(Long dbId);

    List<TableFieldDTO> queryTableFields(QueryTableFieldDTO queryTableFieldDTO);


}
