package com.workStudy.core.project.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.workStudy.common.result.CommonResult;
import com.workStudy.utils.KaptchaUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码校验与获取
 */
@RestController
@Api(tags = "验证码")
@Log4j2
public class CaptchaController {

    @Resource
    private DefaultKaptcha captchaProducer;

    /**
     * 注册验证码图片SessionKey
     */
    public static final String LOGIN_VALIDATE_CODE = "regist_validate_code";

    /**
     * 获取验证码
     */
    @ApiOperation(value = "获取验证码")
    @GetMapping("/getValidateCode")
    public void getValidateCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        KaptchaUtil.validateCode(request, response, captchaProducer, LOGIN_VALIDATE_CODE);
    }

    /**
     * 验证码校验
     */
    @ApiOperation(value = "验证码校验")
    @GetMapping(value = "/checkValidateCode")
    public CommonResult checkValidateCode(HttpServletRequest request, @RequestParam("validateCode") String validateCode) {
        String registValidateCode = request.getSession().getAttribute(LOGIN_VALIDATE_CODE).toString();
        if (registValidateCode.equals(validateCode.toUpperCase()))
            return CommonResult.success("");
        return CommonResult.validateFailed();
    }
}
