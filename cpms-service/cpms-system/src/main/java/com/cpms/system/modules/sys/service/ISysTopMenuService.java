package com.cpms.system.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpms.framework.mybatis.groups.UpdateGroup;
import com.cpms.system.modules.sys.dto.SysTopMenuDTO;
import com.cpms.system.modules.sys.entity.SysTopMenuEntity;
import com.cpms.system.modules.sys.vo.SysTopMenuVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2021/8/30 14:05
 */
public interface ISysTopMenuService extends IService<SysTopMenuEntity> {
   List<SysTopMenuVO> getTopMenu();

   boolean deleteTopMenu(SysTopMenuDTO sysTopMenuDTO);

   boolean addTopMenu(SysTopMenuDTO sysTopMenuDTO);

   boolean updateTopMenu(SysTopMenuDTO sysTopMenuDTO);

   List<SysTopMenuVO> listTopMenu();

   boolean configTopMenu(SysTopMenuDTO sysTopMenuDTO);
}
