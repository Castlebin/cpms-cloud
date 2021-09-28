package com.cpms.system.modules.sys.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpms.system.modules.sys.dto.ListPostDTO;
import com.cpms.system.modules.sys.entity.SysPostEntity;
import com.cpms.system.modules.sys.vo.SysPostVO;

import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/27 14:18
 */
@DS("master")
public interface SysPostMapper extends BaseMapper<SysPostEntity> {
    int listPostCount(ListPostDTO listPostDTO);
    List<SysPostVO> listPost(ListPostDTO listPostDTO);
}
