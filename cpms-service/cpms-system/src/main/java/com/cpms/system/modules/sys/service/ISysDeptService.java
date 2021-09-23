package com.cpms.system.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpms.framework.common.core.base.BasePageVO;
import com.cpms.framework.mybatis.groups.AddGroup;
import com.cpms.framework.mybatis.groups.DeleteGroup;
import com.cpms.framework.mybatis.groups.UpdateGroup;
import com.cpms.system.modules.sys.dto.ListDeptDTO;
import com.cpms.system.modules.sys.dto.SysDeptDTO;
import com.cpms.system.modules.sys.entity.SysDeptEntity;
import com.cpms.system.modules.sys.vo.SysDeptVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description:
 * @author: gulang
 * @time: 2021/7/20 17:52
 */
public interface ISysDeptService extends IService<SysDeptEntity> {
    BasePageVO<SysDeptVO> listDept(ListDeptDTO listDeptDTO);
    boolean addDept(SysDeptDTO deptDTO);
    boolean updateDept(SysDeptDTO deptDTO);
    boolean deleteDept(SysDeptDTO deptDTO);
}
