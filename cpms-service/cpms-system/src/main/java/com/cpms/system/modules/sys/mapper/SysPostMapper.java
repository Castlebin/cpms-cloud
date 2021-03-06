package com.cpms.system.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpms.system.modules.sys.dto.QueryPostDTO;
import com.cpms.system.modules.sys.entity.SysPostEntity;
import com.cpms.system.modules.sys.vo.SysPostVO;

import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/27 14:18
 */
public interface SysPostMapper extends BaseMapper<SysPostEntity> {
    int listPostCount(QueryPostDTO listPostDTO);
    List<SysPostVO> listPost(QueryPostDTO listPostDTO);
}
