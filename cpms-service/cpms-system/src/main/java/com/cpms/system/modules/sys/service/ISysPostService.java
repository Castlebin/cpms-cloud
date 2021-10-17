package com.cpms.system.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpms.framework.common.core.base.BasePageVO;
import com.cpms.system.modules.sys.dto.QueryPostDTO;
import com.cpms.system.modules.sys.dto.SysPostDTO;
import com.cpms.system.modules.sys.entity.SysPostEntity;
import com.cpms.system.modules.sys.vo.SysPostVO;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/27 14:21
 */
public interface ISysPostService extends IService<SysPostEntity> {
    BasePageVO<SysPostVO> listPost(QueryPostDTO listPostDTO);
    boolean addPost(SysPostDTO postDTO);
    boolean deletePost(SysPostDTO postDTO);
    boolean updatePost(SysPostDTO postDTO);
}
