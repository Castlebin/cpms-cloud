package com.cpms.system.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpms.system.modules.sys.entity.SysTopMenuEntity;
import com.cpms.system.modules.sys.vo.SysTopMenuVO;

import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2021/8/30 14:05
 */
public interface ISysTopMenuService extends IService<SysTopMenuEntity> {
   List<SysTopMenuVO> getTopMenu();
}
