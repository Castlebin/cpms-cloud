package com.cpms.system.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpms.common.constant.CommonConstant;
import com.cpms.framework.common.core.base.BasePageVO;
import com.cpms.framework.common.utils.CsBeanUtil;
import com.cpms.framework.common.utils.CsSecureUtil;
import com.cpms.system.modules.sys.dto.QueryPostDTO;
import com.cpms.system.modules.sys.dto.SysPostDTO;
import com.cpms.system.modules.sys.entity.SysPostEntity;
import com.cpms.system.modules.sys.mapper.SysPostMapper;
import com.cpms.system.modules.sys.service.ISysPostService;
import com.cpms.system.modules.sys.vo.SysPostVO;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


/**
 * @description:
 * @author: gulang
 * @time: 2021/9/27 14:22
 */
@Service
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper, SysPostEntity> implements ISysPostService {
    @Resource
    private SysPostMapper sysPostMapper;

    @Override
    public BasePageVO<SysPostVO> listPost(QueryPostDTO queryPostDTO) {
        BasePageVO<SysPostVO> basePageVO = new BasePageVO();
        List<SysPostVO> list;
        if(Objects.isNull(queryPostDTO.getTenantId())) {
            queryPostDTO.setTenantId(CsSecureUtil.userTenantId());
        }else{
            queryPostDTO.setTenantId(queryPostDTO.getTenantId());
        }
        int count = sysPostMapper.listPostCount(queryPostDTO);
        if(count ==0){
            list = Lists.newArrayList();
        }else{
            list = sysPostMapper.listPost(queryPostDTO);
        }
        basePageVO.setTotal(count);
        basePageVO.setList(list);
        return basePageVO;
    }

    @Override
    public boolean addPost(SysPostDTO postDTO) {
        SysPostEntity sysPostEntity = new SysPostEntity();
        postDTO.setPostCode(postDTO.getPostCode().toUpperCase());
        CsBeanUtil.copyProperties(postDTO,sysPostEntity);
        sysPostEntity.setTenantId(CsSecureUtil.userTenantId());
        return this.save(sysPostEntity);
    }

    @Override
    public boolean deletePost(SysPostDTO postDTO) {
        LambdaUpdateWrapper<SysPostEntity> updateWrapper = Wrappers.<SysPostEntity>lambdaUpdate()
                .set(SysPostEntity::getDelFlag, CommonConstant.DEL_FLAG_DELETED)
                .eq(SysPostEntity::getPostId,postDTO.getPostId())
                .eq(SysPostEntity::getTenantId,CsSecureUtil.userTenantId());
        return this.update(updateWrapper);
    }

    @Override
    public boolean updatePost(SysPostDTO postDTO) {
        LambdaUpdateWrapper<SysPostEntity> updateWrapper = Wrappers.<SysPostEntity>lambdaUpdate()
                .set(SysPostEntity::getPostName, postDTO.getPostName())
                .set(SysPostEntity::getPostCode, postDTO.getPostCode().toUpperCase())
                .set(SysPostEntity::getPostDesc, postDTO.getPostDesc())
                .set(SysPostEntity::getPostSort, postDTO.getPostSort())
                .eq(SysPostEntity::getPostId,postDTO.getPostId())
                .eq(SysPostEntity::getTenantId,CsSecureUtil.userTenantId());
        return this.update(new SysPostEntity(),updateWrapper);
    }
}
