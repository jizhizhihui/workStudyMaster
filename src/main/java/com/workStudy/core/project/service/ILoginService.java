package com.workStudy.core.project.service;


import com.workStudy.core.project.entity.Admin;
import com.workStudy.core.project.entity.Manager;
import com.workStudy.core.project.entity.Student;

public interface ILoginService {
    Admin adminLogin(String name, String password);
    Manager managerLogin(String id, String password);
    Student studentLogin(String id, String password);
    void login(String id, String password, String role);
}
