package com.workStudy.core.project.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workStudy.core.project.entity.Job;
import com.workStudy.core.project.entity.Post;
import com.workStudy.core.project.mapper.JobMapper;
import com.workStudy.core.project.service.JobService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JZhi
 * @since 2020-04-16
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {
    @Resource JobMapper jobMapper;

    @Override
    public List getByPostId(int id, int pageNum, int pageSize) {
//        QueryWrapper<Job> queryWrapper = new QueryWrapper<>();
//        queryWrapper.lambda().eq(Job::getPostId, id);
//
//        Page<Job> page = new Page<>(pageNum,pageSize);
//
//        IPage iPage = jobMapper.selectPage(page, queryWrapper);
//
//        return iPage.getRecords();
        return null;
    }

    @Override
    public List getByManagerId(int id, int pageNum, int pageSize) {
        QueryWrapper<Job> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Job::getManagerId, id);

        Page<Job> page = new Page<>(pageNum,pageSize);

        IPage iPage = jobMapper.selectPage(page, queryWrapper);

        return iPage.getRecords();
    }
}
