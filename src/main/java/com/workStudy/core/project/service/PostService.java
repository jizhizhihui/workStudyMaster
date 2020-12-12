package com.workStudy.core.project.service;

import com.workStudy.core.project.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JZhi
 * @since 2020-06-03
 */
public interface PostService extends IService<Post> {

    List<Post> getByManagerId(int managerId);

    List getPageByManagerId(int managerId, int pageNum, int pageSize);

    List getExamineByState(int managerId, int pageNum, int pageSize);
}
