package com.cpms.system.modouls.sys.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpms.system.modouls.sys.entity.SysDeptEntity;
import com.cpms.system.modouls.sys.entity.SysUserEntity;
import com.cpms.system.modouls.sys.mapper.SysDeptMapper;
import com.cpms.system.modouls.sys.mapper.SysUserMapper;
import com.cpms.system.modouls.sys.service.ISysDeptService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: 01396003
 * @time: 2021/7/20 17:54
 */
@Service
@DS("master")
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDeptEntity> implements ISysDeptService {
}
