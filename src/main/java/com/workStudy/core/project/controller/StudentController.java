package com.workStudy.core.project.controller;


import com.workStudy.common.result.CommonResult;
import com.workStudy.core.project.entity.Student;
import com.workStudy.core.project.service.StudentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
* <p>
*  前端控制器
* </p>
*
* @author JZhi
* @since 2020-04-03
*/
@RestController
@RequestMapping("/student")
@Api(tags = "学生")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 获取manager集合
     * @return managerList
     */
    @GetMapping("/list")
    public CommonResult<Object> getList(){
        return CommonResult.success(studentService.list());
    }

    /**
     * 通过id获取student
     * @param id 学生id
     * @return student
     */
    @PostMapping("/getById")
    public CommonResult<Object> getStudentById(@NotBlank @RequestParam String id){
        Student student = studentService.getById(id);
        if(student != null)
            return CommonResult.success(student);
        return CommonResult.failed("id错误!");
    }

    /**
     * 更新student信息
     * @param student 学生
     * @return String
     */
    @PutMapping("/update")
    public CommonResult<String> update(Student student){
        if(studentService.updateById(student))
            return CommonResult.success("更新成功！");
        return CommonResult.failed("更新失败！");
    }

    /**
     * 通过id删除 student (待更改)
     * @param id 学生id
     * @return String
     */
    @DeleteMapping("/delete/{id}")
    public CommonResult<String> delete(@NotBlank @PathVariable String id){
        if(studentService.removeById(id))
            return CommonResult.success("删除成功！");
        return CommonResult.failed("删除失败！");
    }

    /**
     * 添加学生
     * @param student 学生
     * @return String
     */
    @PostMapping("/add")
    public CommonResult<String> add(Student student){
        if(student.getId().equals("") && studentService.save(student))
            return CommonResult.success("添加成功");
        return CommonResult.failed("添加失败");
    }

}
