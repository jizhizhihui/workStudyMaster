package com.workStudy.core.project.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.workStudy.core.project.entity.StuApply;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JZhi
 * @since 2020-04-16
 */
public interface StuApplyService extends IService<StuApply> {
    List getByPostId(int managerId, int pageNum, int pageSize);
    List getStuApplyByState(int id, int pageNum, int pageSize);
}
