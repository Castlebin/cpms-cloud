package com.cpms.system.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpms.common.constant.CommonConstant;
import com.cpms.framework.common.core.base.BasePageVO;
import com.cpms.framework.common.enums.RandomTypeEnum;
import com.cpms.framework.common.exception.BizException;
import com.cpms.framework.common.utils.CsBeanUtil;
import com.cpms.framework.common.utils.CsGenerateIdUtil;
import com.cpms.framework.common.utils.CsRandomUtil;
import com.cpms.framework.common.utils.CsSecureUtil;
import com.cpms.system.common.enums.SystemResponseResultEnum;
import com.cpms.system.modules.sys.dto.QueryTenantDTO;
import com.cpms.system.modules.sys.dto.SysTenantDTO;
import com.cpms.system.modules.sys.entity.*;
import com.cpms.system.modules.sys.mapper.SysTenantMapper;
import com.cpms.system.modules.sys.service.*;
import com.cpms.system.modules.sys.vo.InitTenantAccountVO;
import com.cpms.system.modules.sys.vo.SysTenantVO;
import com.google.common.collect.Lists;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/22 11:28
 */
@Service
public class SysTenantServiceImpl extends ServiceImpl<SysTenantMapper, SysTenantEntity> implements ISysTenantService {
    @Resource
    private SysTenantMapper sysTenantMapper;
    @Resource
    private ISysDeptService sysDeptService;
    @Resource
    private ISysRoleService sysRoleService;
    @Resource
    private ISysRoleUserService sysRoleUserService;
    @Resource
    private ISysUserService sysUserService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public InitTenantAccountVO addTenant(SysTenantDTO tenantDTO) {
        // 校验前缀是否存在
        int count = sysTenantMapper.selectCount(
                Wrappers.<SysTenantEntity>lambdaQuery().
                        eq(SysTenantEntity::getAccountPrefix,
                                tenantDTO.getAccountPrefix()));
        if(count > 0){
            throw new BizException(SystemResponseResultEnum.ACCOUNT_PREFIX_EXISTS_ERROR);
        }
        SysTenantEntity sysTenantEntity = new SysTenantEntity();
        CsBeanUtil.copyProperties(tenantDTO,sysTenantEntity);
        // 添加租户信息
        this.save(sysTenantEntity);
        SysDeptEntity sysDeptEntity = new SysDeptEntity();
        sysDeptEntity.setTenantId(sysTenantEntity.getTenantId());
        sysDeptEntity.setDeptName(tenantDTO.getTenantName()+"总部");
        sysDeptEntity.setParentId(0L);
        // 添加部门信息
        sysDeptService.save(sysDeptEntity);
        // 添加角色信息
        SysRoleEntity sysRoleEntity = new SysRoleEntity();
        sysRoleEntity.setTenantId(sysTenantEntity.getTenantId());
        sysRoleEntity.setRoleName(CommonConstant.DEFAULT_ROLE_NAME);
        sysRoleEntity.setRoleCode(CommonConstant.DEFAULT_ROLE_CODE);
        sysRoleEntity.setRoleDesc(CommonConstant.DEFAULT_ROLE_DESC);
        sysRoleService.save(sysRoleEntity);
        // 初始化一个租户管理员账号
        return initAccount(sysTenantEntity,sysDeptEntity.getDeptId(),sysRoleEntity.getRoleId());
    }

    @Override
    public boolean updateTenant(SysTenantDTO tenantDTO) {
        SysTenantEntity sysTenantEntity = new SysTenantEntity();
        CsBeanUtil.copyProperties(tenantDTO,sysTenantEntity,"accountPrefix");
        return this.updateById(sysTenantEntity);
    }

    @Override
    public boolean deleteTenant(SysTenantDTO tenantDTO) {
        LambdaUpdateWrapper<SysTenantEntity> updateWrapper = Wrappers.<SysTenantEntity>lambdaUpdate()
                .set(SysTenantEntity::getDelFlag, CommonConstant.DEL_FLAG_DELETED)
                .eq(SysTenantEntity::getTenantId,tenantDTO.getTenantId());
        return this.update(updateWrapper);
    }

    @Override
    public BasePageVO<SysTenantVO> listTenant(QueryTenantDTO listTenantDTO) {
        BasePageVO<SysTenantVO> basePageVO = new BasePageVO();
        List<SysTenantVO> list;
        int count = sysTenantMapper.listTenantCount(listTenantDTO);
        if(count == 0){
            list = Lists.newArrayList();
        }else{
            list = sysTenantMapper.listTenant(listTenantDTO);
        }
        basePageVO.setTotal(count);
        basePageVO.setList(list);
        return basePageVO;
    }

    @Override
    public List<SysTenantVO> tenants() {
        QueryWrapper<SysTenantEntity> query = Wrappers.query();
        query.select("tenant_id","tenant_name");
        if(!CsSecureUtil.isSuperAdmin()) {
            query.eq("tenant_id", CsSecureUtil.userTenantId());
        }
        query.eq("del_flag", CommonConstant.DEL_FLAG_NOT_DELETED);
        List<SysTenantEntity> list = this.list(query);
        return list.stream().map(e->{
            SysTenantVO sysTenantVO = new SysTenantVO();
            sysTenantVO.setTenantId(e.getTenantId());
            sysTenantVO.setTenantName(e.getTenantName());
            return  sysTenantVO;
        }).collect(Collectors.toList());
    }

    private InitTenantAccountVO initAccount(SysTenantEntity sysTenantEntity,Long deptId,Long roleId){
       InitTenantAccountVO initTenantAccountVO = new InitTenantAccountVO();
       SysUserEntity sysUserEntity = new SysUserEntity();
       sysUserEntity.setTenantId(sysTenantEntity.getTenantId());
       sysUserEntity.setDeptId(deptId);
       sysUserEntity.setUserMobile(sysTenantEntity.getContactNumber());
       sysUserEntity.setUserName(sysTenantEntity.getContacts());
       BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
       String initPassword = CsRandomUtil.random(6, RandomTypeEnum.ALL);
       sysUserEntity.setUserPassword(bCryptPasswordEncoder.encode(initPassword));
       int count = sysUserService.userCount(sysTenantEntity.getTenantId());
       sysUserEntity.setUserAccount(CsGenerateIdUtil.userAccount(sysTenantEntity.getAccountPrefix(),6,count+1));
       sysUserService.save(sysUserEntity);
       // 用户角色关联关系
       SysRoleUserEntity sysRoleUserEntity = new SysRoleUserEntity();
       sysRoleUserEntity.setUserId(sysUserEntity.getUserId());
       sysRoleUserEntity.setRoleId(roleId);
       sysRoleUserService.save(sysRoleUserEntity);
       // 返回给应用层信息
       initTenantAccountVO.setUserAccount(sysUserEntity.getUserAccount());
       initTenantAccountVO.setUserName(sysUserEntity.getUserName());
       initTenantAccountVO.setUserPassword(initPassword);
       return initTenantAccountVO;
   }
}
