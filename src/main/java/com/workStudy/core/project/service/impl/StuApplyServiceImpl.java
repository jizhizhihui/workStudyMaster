package com.workStudy.core.project.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workStudy.core.project.entity.Job;
import com.workStudy.core.project.entity.StuApply;
import com.workStudy.core.project.mapper.StuApplyMapper;
import com.workStudy.core.project.service.StuApplyService;
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
public class StuApplyServiceImpl extends ServiceImpl<StuApplyMapper, StuApply> implements StuApplyService {

    @Resource StuApplyMapper stuApplyMapper;

    @Override
    public List getByPostId(int id, int pageNum, int pageSize) {
        QueryWrapper<StuApply> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(StuApply::getPostId, id);

        Page<StuApply> page = new Page<>(pageNum,pageSize);

        IPage iPage = stuApplyMapper.selectPage(page, queryWrapper);

        return iPage.getRecords();
    }

    @Override
    public List getStuApplyByState(int id, int pageNum, int pageSize) {
        QueryWrapper<StuApply> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(StuApply::getPostId, id)
                .eq(StuApply::getState, 0);

        Page<StuApply> page = new Page<>(pageNum,pageSize);

        IPage iPage = stuApplyMapper.selectPage(page, queryWrapper);

        return iPage.getRecords();
    }
}
