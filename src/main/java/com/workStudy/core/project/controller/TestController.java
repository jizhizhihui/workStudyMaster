package com.workStudy.core.project.controller;

import com.workStudy.common.result.CommonResult;
import com.workStudy.core.project.entity.Admin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@Api(tags = "测试")
@Log4j2
public class TestController {

    @PostMapping("/parameter1")
    @ApiOperation("参数测试1")
    public CommonResult parameterTest(String s1,String s2){
        log.error(s1 +s2);
        return CommonResult.success(s1 + s2);
    }

    @PostMapping("/parameter2")
    @ApiOperation("参数测试2")
    public CommonResult parameterTest2(@RequestParam("s1") String s1,@RequestParam(value = "s2") String s2){
        //@RequestParam 可以映射到数据
        log.error(s1 +s2);
        return CommonResult.success(s1 + s2);
    }

    @PostMapping("/objectTest")
    @ApiOperation("参数对象测试")
    public CommonResult parameterPUT(Admin admin){
        log.error(admin.toString());
        return CommonResult.success("ok");
    }
}
