package com.cpms.system.modules.sys.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpms.common.constant.CommonConstant;
import com.cpms.framework.common.core.base.BasePageVO;
import com.cpms.framework.common.exception.BizException;
import com.cpms.framework.common.utils.CsBeanUtil;
import com.cpms.framework.common.utils.CsSecureUtil;
import com.cpms.system.common.enums.SystemResponseResultEnum;
import com.cpms.system.modules.sys.dto.QueryDeptDTO;
import com.cpms.system.modules.sys.dto.SysDeptDTO;
import com.cpms.system.modules.sys.entity.SysDeptEntity;
import com.cpms.system.modules.sys.mapper.SysDeptMapper;
import com.cpms.system.modules.sys.service.ISysDeptService;
import com.cpms.system.modules.sys.service.ISysTenantService;
import com.cpms.system.modules.sys.vo.SysDeptVO;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: gulang
 * @time: 2021/7/20 17:54
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDeptEntity> implements ISysDeptService {
    @Resource
    private SysDeptMapper sysDeptMapper;
    @Resource
    private ISysTenantService sysTenantService;

    @Override
    public BasePageVO<SysDeptVO> listDept(QueryDeptDTO listDeptDTO) {
        BasePageVO<SysDeptVO> basePageVO = new BasePageVO();
        List<SysDeptVO> list;
        listDeptDTO.setTenantId(CsSecureUtil.userTenantId());
        int count = sysDeptMapper.listDeptCount(listDeptDTO);
        if(count ==0){
            list = Lists.newArrayList();
        }else{
            list = sysDeptMapper.listDept(listDeptDTO);
        }
        basePageVO.setTotal(count);
        basePageVO.setList(list);
        return basePageVO;
    }

    @Override
    public boolean addDept(SysDeptDTO deptDTO) {
        SysDeptEntity sysDeptEntity = new SysDeptEntity();
        CsBeanUtil.copyProperties(deptDTO,sysDeptEntity);
        sysDeptEntity.setTenantId(CsSecureUtil.userTenantId());
        return this.save(sysDeptEntity);
    }

    @Override
    public boolean updateDept(SysDeptDTO deptDTO) {
        LambdaUpdateWrapper<SysDeptEntity> updateWrapper = Wrappers.<SysDeptEntity>lambdaUpdate()
                .set(SysDeptEntity::getDeptName, deptDTO.getDeptName())
                .set(SysDeptEntity::getParentId, deptDTO.getParentId())
                .set(SysDeptEntity::getDeptDesc, deptDTO.getDeptDesc())
                .eq(SysDeptEntity::getDeptId,deptDTO.getDeptId())
                .eq(SysDeptEntity::getTenantId,CsSecureUtil.userTenantId());
        return this.update(updateWrapper);
    }

    @Override
    public boolean deleteDept(SysDeptDTO deptDTO) {
        QueryWrapper<SysDeptEntity> query = Wrappers.query();
        query.eq("parent_id",deptDTO.getDeptId());
        query.eq("del_flag", CommonConstant.DEL_FLAG_NOT_DELETED);
        Integer count = baseMapper.selectCount(query);
        if(count > 0){
            throw new BizException(SystemResponseResultEnum.THERE_ARE_CHILD_NODES_ERROR);
        }
        LambdaUpdateWrapper<SysDeptEntity> updateWrapper = Wrappers.<SysDeptEntity>lambdaUpdate()
                .set(SysDeptEntity::getDelFlag, CommonConstant.DEL_FLAG_DELETED)
                .eq(SysDeptEntity::getDeptId,deptDTO.getDeptId())
                .eq(SysDeptEntity::getTenantId,CsSecureUtil.userTenantId());
        return this.update(updateWrapper);
    }

    @Override
    public List<Tree<String>> treeDept() {
        QueryWrapper<SysDeptEntity> query = Wrappers.query();
        query.select("dept_id","dept_name","parent_id");
        if(!CsSecureUtil.isSuperAdmin()) {
            query.eq("tenant_id", CsSecureUtil.userTenantId());
        }
        query.eq("del_flag", CommonConstant.DEL_FLAG_NOT_DELETED);
        List<SysDeptEntity> list = this.list(query);
        List<TreeNode<String>> nodeList =  list.stream().map(e->{
            TreeNode<String> treeNode = new TreeNode();
            treeNode.setId(String.valueOf(e.getDeptId()));
            treeNode.setParentId( String.valueOf(e.getParentId()));
            treeNode.setName(e.getDeptName());
            return  treeNode;
        }).collect(Collectors.toList());
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setIdKey("deptId");
        treeNodeConfig.setNameKey("deptName");
        treeNodeConfig.setDeep(10);
        return TreeUtil.build(nodeList, "0", treeNodeConfig,
                (treeNode, tree) -> {
                    tree.setId(treeNode.getId());
                    tree.setParentId(treeNode.getParentId());
                    tree.setName(treeNode.getName());
                });
    }
}
