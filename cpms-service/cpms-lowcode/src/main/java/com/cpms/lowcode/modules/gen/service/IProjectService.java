package com.cpms.lowcode.modules.gen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpms.framework.common.core.base.BasePageVO;
import com.cpms.lowcode.modules.gen.dto.ProjectDetailDTO;
import com.cpms.lowcode.modules.gen.dto.QueryProjectDTO;
import com.cpms.lowcode.modules.gen.entity.ProjectEntity;
import com.cpms.lowcode.modules.gen.vo.ProjectDetailVO;



/**
 * @description:
 * @author: gulang
 * @time: 2022/1/25 10:33
 */
public interface IProjectService extends IService<ProjectEntity> {
    BasePageVO<ProjectDetailVO> listProject(QueryProjectDTO queryProjectDTO);

    boolean addProject(ProjectDetailDTO projectDTO);

    boolean updateProject(ProjectDetailDTO projectDTO);

    boolean delProject(ProjectDetailDTO projectDTO);
}
