package com.workStudy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@MapperScan("com.workStudy.core.project.mapper") //扫描mapper类生成代理对象
public class WorkStudyApplication extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(WorkStudyApplication.class, args);
    }

    @Override
    //重写父类提供的跨域请求处理的接口
    public void addCorsMappings(CorsRegistry registry) {
        //添加映射路径
        registry.addMapping("/**")
                //是否发送Cookie信息
                .allowCredentials(true)
                //放行哪些原始域头部信息
                .allowedHeaders("*")
                //放行哪些原始域（如：”http://admin.com”）
                .allowedOrigins("*")
                //放行哪些原始域请求方式( 如：”GET,POST”)
                .allowedMethods("*");
    }
}