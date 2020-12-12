package com.workStudy.core.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.workStudy.core.project.entity.Post;
import com.workStudy.core.project.mapper.PostMapper;
import com.workStudy.core.project.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JZhi
 * @since 2020-06-03
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    @Resource
    PostMapper postMapper;

    @Override
    public List<Post> getByManagerId(int managerId) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Post::getManagerId, managerId);
        return postMapper.selectList(queryWrapper);
    }

    @Override
    public List getPageByManagerId(int managerId, int pageNum, int pageSize) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Post::getManagerId, managerId);

        Page<Post>  page = new Page<>(pageNum,pageSize);

        IPage iPage = postMapper.selectPage(page, queryWrapper);

        return iPage.getRecords();
    }

    @Override
    public List getExamineByState(int managerId, int pageNum, int pageSize){
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(Post::getManagerId, managerId)
                .eq(Post::getState, -1);

        Page<Post>  page = new Page<>(pageNum,pageSize);

        IPage iPage = postMapper.selectPage(page, queryWrapper);

        return iPage.getRecords();
    }
}
