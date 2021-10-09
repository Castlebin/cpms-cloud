package com.cpms.system.modules.sys.feign;

import com.cpms.common.constant.CommonConstant;
import com.cpms.framework.common.core.api.Result;
import com.cpms.framework.common.core.api.ResultUtil;
import com.cpms.framework.common.utils.CsBeanUtil;
import com.cpms.system.api.modules.sys.bo.SysUserLoginBO;
import com.cpms.system.api.modules.sys.dto.SysUserLginDTO;
import com.cpms.system.api.modules.sys.feign.ISysUserClient;
import com.cpms.system.api.modules.sys.vo.SysRoleVO;
import com.cpms.system.api.modules.sys.vo.SysUserLoginVO;
import com.cpms.system.common.enums.SystemResponseResultEnum;
import com.cpms.system.modules.sys.entity.SysRoleEntity;
import com.cpms.system.modules.sys.service.ISysRoleService;
import com.cpms.system.modules.sys.service.ISysUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author gulang
 * @Description:
 */
@RestController
public class SysUserClient implements ISysUserClient {
    @Resource
    private ISysUserService sysUserService;
    @Resource
    private ISysRoleService sysRoleService;

    @Override
    @PostMapping(API_PREFIX+"/login")
    public Result<SysUserLoginVO> sysUserLogin(SysUserLginDTO sysUserLginDTO) {
        SysUserLoginVO sysUserLoginVO = new SysUserLoginVO();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        SysUserLoginBO sysUserLoginBO = sysUserService.querySysUserInfo(sysUserLginDTO);
        if(Objects.isNull(sysUserLoginBO)) {
            return ResultUtil.error(SystemResponseResultEnum.ACCOUNT_OR_PASSWORD_CHECK_FAILED_ERROR);
        }
        if(!bCryptPasswordEncoder.matches(sysUserLginDTO.getUserPassword(),sysUserLoginBO.getUserPassword())) {
            return ResultUtil.error(SystemResponseResultEnum.ACCOUNT_OR_PASSWORD_CHECK_FAILED_ERROR);
        }
        if(sysUserLoginBO.getUserStarus() == CommonConstant.DATA_STATUS_FORBIDDEN){
            return ResultUtil.error(SystemResponseResultEnum.ACCOUNT_FORBIDDEN_ERROR);
        }
        if(sysUserLoginBO.getTenantStatus() == CommonConstant.DATA_STATUS_FORBIDDEN){
            return ResultUtil.error(SystemResponseResultEnum.TENANT_FORBIDDEN_ERROR);
        }
        List<SysRoleEntity> sysRoleEntities = sysRoleService.queryRoleByUserId(sysUserLoginBO.getUserId());
        List<SysRoleVO> roles = sysRoleEntities.stream().map(e -> {
            SysRoleVO sysRoleVO = new SysRoleVO();
            sysRoleVO.setRoleCode(e.getRoleCode());
            sysRoleVO.setRoleName(e.getRoleName());
            sysRoleVO.setRoleId(e.getRoleId());
            return sysRoleVO;
        }).collect(Collectors.toList());
        CsBeanUtil.copyProperties(sysUserLoginBO,sysUserLoginVO);
        sysUserLoginVO.setRoles(roles);
        return ResultUtil.success(sysUserLoginVO);
    }
}
