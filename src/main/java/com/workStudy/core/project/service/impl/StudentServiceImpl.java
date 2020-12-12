package com.workStudy.core.project.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workStudy.core.project.entity.Student;
import com.workStudy.core.project.mapper.StudentMapper;
import com.workStudy.core.project.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JZhi
 * @since 2020-04-03
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
