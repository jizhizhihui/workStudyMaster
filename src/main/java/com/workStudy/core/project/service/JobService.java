package com.workStudy.core.project.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.workStudy.core.project.entity.Job;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JZhi
 * @since 2020-04-16
 */
public interface JobService extends IService<Job> {
    List getByPostId(int postId, int pageNum, int pageSize);
    List getByManagerId(int managerId, int pageNum, int pageSize);
}
