package com.workStudy.core.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.workStudy.core.jwt.UserToken;
import com.workStudy.core.project.entity.Admin;
import com.workStudy.core.project.entity.Manager;
import com.workStudy.core.project.entity.Student;
import com.workStudy.core.project.service.AdminService;
import com.workStudy.core.project.service.ILoginService;
import com.workStudy.core.project.service.ManagerService;
import com.workStudy.core.project.service.StudentService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {

    @Autowired
    AdminService adminService;
    @Autowired
    ManagerService managerService;
    @Autowired
    StudentService studentService;

    @Override
    public Admin adminLogin(String name,String password){
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(Admin::getName, name)
                .eq(Admin::getPassword, password);
        return adminService.getOne(queryWrapper);
    }

    @Override
    public Manager managerLogin(String id,String password){
        Manager manager = managerService.getById(id);
        if(manager != null && manager.getPassword().equals(password))
            return manager;
        return null;
    }
    @Override    
    public Student studentLogin(String id, String password){
        Student student = studentService.getById(id);
        if(student != null && student.getPassword().equals(password))
            return student;
        return null;
    }

    @Override
    public void login(String id, String password, String role){
        SecurityUtils.getSubject().getSession().setTimeout(-1000L);
        // 调用安全认证框架的登录方法
        SecurityUtils.getSubject().login(new UserToken(id, password, role));
//        SecurityUtils.getSubject().getSession().setAttribute("RememberMe",rememberMe);
//        return userToken;
    }
}
