package com.cpms.lowcode.modules.gen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpms.lowcode.modules.gen.dto.TableDTO;
import com.cpms.lowcode.modules.gen.entity.TableEntity;
import com.cpms.lowcode.modules.gen.mapper.TableMapper;
import com.cpms.lowcode.modules.gen.service.ITableService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2022/1/25 10:31
 */
@Service
public class TableServiceImpl extends ServiceImpl<TableMapper, TableEntity> implements ITableService {
    @Override
    public List<TableEntity> listTable(TableDTO tableDTO) {
        return null;
    }

    @Override
    public boolean addTable(TableDTO tableDTO) {
        return false;
    }

    @Override
    public boolean updateTable(TableDTO tableDTO) {
        return false;
    }

    @Override
    public boolean delTable(TableDTO tableDTO) {
        return false;
    }
}
