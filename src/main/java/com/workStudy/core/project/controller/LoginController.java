package com.workStudy.core.project.controller;

import com.workStudy.common.result.CommonResult;
import com.workStudy.core.project.entity.Admin;
import com.workStudy.core.project.entity.Manager;
import com.workStudy.core.project.entity.Student;
import com.workStudy.core.project.service.ILoginService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/login")
@Api(tags = "登录")
@Log4j2
public class LoginController {

    @Autowired
    private ILoginService loginService;

    /**
     * 注册验证码图片SessionKey
     */
    private static final String LOGIN_VALIDATE_CODE = "regist_validate_code";

    @PostMapping("/admin")
    public CommonResult<Object> adminLogin(HttpServletRequest request,
                                           @RequestParam("account") String account,
                                           @RequestParam("password") String password,
                                           @RequestParam("validateCode") String validateCode) {

        if (checkValidateCode(request.getSession(), validateCode))
            return CommonResult.validateFailed();

        if (account.equals("") || password.equals(""))
            return CommonResult.failed("账号或密码为空");
        Admin admin = loginService.adminLogin(account, password);
        if (admin == null)
            return CommonResult.failed("账号或密码错误");
        System.out.println(admin.toString());
        return CommonResult.success(admin);
    }

    @PostMapping("/manager")
    public CommonResult managerLogin(HttpServletRequest request,
                                     @RequestParam String account,
                                     @RequestParam String password,
                                     @RequestParam("validateCode") String validateCode) {

        if (checkValidateCode(request.getSession(), validateCode))
            return CommonResult.validateFailed();

        if (account.equals("") || password.equals(""))
            return CommonResult.failed("账号或密码为空");
        Manager user = loginService.managerLogin(account, password);
        if (user == null)
            return CommonResult.failed("账号或密码错误");
        return CommonResult.success(user);
    }

    @PostMapping("/student")
    public CommonResult studentLogin(HttpServletRequest request,
                                     @RequestParam String account,
                                     @RequestParam String password,
                                     @RequestParam("validateCode") String validateCode,
                                     @RequestParam(defaultValue = "false", required = false) Boolean rememberMe) {

        if (checkValidateCode(request.getSession(), validateCode))
            return CommonResult.validateFailed();

        Student student = loginService.studentLogin(account, password);
        loginService.login(account, password, "Student");
        if (student == null)
            return CommonResult.failed("账号或密码错误");
//        if (rememberMe)
//            session.setAttribute("rememberMe",userToken);
        return CommonResult.success(student);
    }

    @PostMapping("/logout")
    public CommonResult logout() {
        SecurityUtils.getSubject().logout();
        return CommonResult.success("注销成功");
    }
    
    /**
     * 验证码校验
     */
    private boolean checkValidateCode(HttpSession session, String validateCode) {
        String registValidateCode = session.getAttribute(LOGIN_VALIDATE_CODE).toString();
        return !registValidateCode.equals(validateCode.toUpperCase());
    }
}
