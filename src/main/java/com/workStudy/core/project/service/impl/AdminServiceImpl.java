package com.workStudy.core.project.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workStudy.core.project.entity.Admin;
import com.workStudy.core.project.mapper.AdminMapper;
import com.workStudy.core.project.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JZhi
 * @since 2020-04-10
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
