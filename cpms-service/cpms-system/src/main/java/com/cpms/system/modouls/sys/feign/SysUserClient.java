package com.cpms.system.modouls.sys.feign;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cpms.common.core.api.Result;
import com.cpms.common.core.api.ResultUtil;
import com.cpms.system.api.bo.SysUserLoginBO;
import com.cpms.system.api.dto.SysUserLginDTO;
import com.cpms.system.api.feign.ISysUserClient;
import com.cpms.system.api.vo.SysUserLoginVO;
import com.cpms.system.common.constants.SystemConstant;
import com.cpms.system.common.enums.SystemResponseResultEnum;
import com.cpms.system.modouls.sys.entity.SysDeptEntity;
import com.cpms.system.modouls.sys.service.ISysDeptService;
import com.cpms.system.modouls.sys.service.ISysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author gulang
 * @Description:
 */
@RestController
public class SysUserClient implements ISysUserClient {
    @Resource
    private ISysUserService sysUserService;
    @Resource
    private ISysDeptService sysDeptService;
    @Override
    @PostMapping(API_PREFIX+"/login")
    public Result<SysUserLoginVO> sysUserLogin(SysUserLginDTO sysUserLginDTO) {
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
        QueryWrapper<SysDeptEntity> query = Wrappers.query();
        query.eq("dept_id",sysUserLoginBO.getDeptId());
        query.eq("del_flag",SystemConstant.DEL_FLAG_NOT_DELETED);
        SysDeptEntity sysDeptEntity = sysDeptService.getOne(query);
        if(Objects.nonNull(sysDeptEntity)){
            sysUserLoginBO.setDeptName(sysDeptEntity.getDeptName());
        }
        SysUserLoginVO sysUserLoginVO = new SysUserLoginVO();
        BeanUtils.copyProperties(sysUserLoginBO,sysUserLoginVO);
        return ResultUtil.success(sysUserLoginVO);
    }
}
