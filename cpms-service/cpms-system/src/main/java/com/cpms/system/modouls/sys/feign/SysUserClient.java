package com.cpms.system.modouls.sys.feign;

import com.cpms.common.core.api.Result;
import com.cpms.common.core.api.ResultUtil;
import com.cpms.system.api.bo.SysUserLoginBO;
import com.cpms.system.api.dto.SysUserLginDTO;
import com.cpms.system.api.feign.ISysUserClient;
import com.cpms.system.api.vo.SysRoleVO;
import com.cpms.system.api.vo.SysUserLoginVO;
import com.cpms.system.common.constants.SystemConstant;
import com.cpms.system.common.enums.SystemResponseResultEnum;
import com.cpms.system.modouls.sys.entity.SysRoleEntity;
import com.cpms.system.modouls.sys.service.ISysRoleService;
import com.cpms.system.modouls.sys.service.ISysUserService;
import org.springframework.beans.BeanUtils;
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
        SysUserLoginBO sysUserLoginBO = sysUserService.querySysUserInfo(sysUserLginDTO);
        if(Objects.isNull(sysUserLoginBO)) {
            return ResultUtil.error(SystemResponseResultEnum.ACCOUNT_OR_PASSWORD_CHECK_FAILED_ERROR);
        }
        if(sysUserLoginBO.getUserStarus() == SystemConstant.DATA_STATUS_FORBIDDEN){
            return ResultUtil.error(SystemResponseResultEnum.ACCOUNT_FORBIDDEN_ERROR);
        }
        if(sysUserLoginBO.getTenantStatus() == SystemConstant.DATA_STATUS_FORBIDDEN){
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
        BeanUtils.copyProperties(sysUserLoginBO,sysUserLoginVO);
        sysUserLoginVO.setRoles(roles);
        return ResultUtil.success(sysUserLoginVO);
    }
}
