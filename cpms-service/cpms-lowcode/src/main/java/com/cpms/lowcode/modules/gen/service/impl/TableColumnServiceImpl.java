package com.cpms.lowcode.modules.gen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpms.lowcode.modules.gen.dto.TableColumnDTO;
import com.cpms.lowcode.modules.gen.entity.TableColumnEntity;
import com.cpms.lowcode.modules.gen.mapper.TableColumnMapper;
import com.cpms.lowcode.modules.gen.service.ITableColumnService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2022/1/25 10:42
 */
@Service
public class TableColumnServiceImpl extends ServiceImpl<TableColumnMapper, TableColumnEntity> implements ITableColumnService {
    @Override
    public List<TableColumnEntity> listTableColumn(TableColumnDTO tableColumnDTO) {
        return null;
    }

    @Override
    public boolean addTableColumn(TableColumnDTO tableColumnDTO) {
        return false;
    }

    @Override
    public boolean updaTetableColumn(TableColumnDTO tableColumnDTO) {
        return false;
    }

    @Override
    public boolean delTableColumn(TableColumnDTO tableColumnDTO) {
        return false;
    }
}
