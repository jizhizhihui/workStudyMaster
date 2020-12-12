package com.workStudy.core.project.controller;


import com.workStudy.common.result.CommonResult;
import com.workStudy.core.project.entity.Admin;
import com.workStudy.core.project.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.constraints.NotBlank;

/**
 *
 * @author JZhi
 * @since 2020-04-10
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "管理员")
public class AdminController {

    @Autowired
    AdminService adminService;

    /**
     * 获取admin集合
     * @return adminList
     */
    @GetMapping("/list")
    @ApiOperation("获取管理员集合")
    public CommonResult<Object> getList(){
        return CommonResult.success(adminService.list());
    }

    /**
     * 通过id获取admin
     * @param id 管理员id
     * @return admin
     */
    @PostMapping("/getById")
    @ApiOperation("通过ID获取管理员信息")
    public CommonResult<Object> getAdminById(@NotBlank String id){
        Admin admin = adminService.getById(id);
        if(admin != null)
            return CommonResult.success(admin);
        return CommonResult.failed("id错误!");
    }

    /**
     * 更新admin信息
     * @param admin 管理员
     * @return String
     */
    @PutMapping(value = "/update")
    @ApiOperation("更新管理员信息")
    public CommonResult<String> update(Admin admin){
        if(adminService.updateById(admin))
            return CommonResult.success("更新成功！");
        return CommonResult.failed("更新失败！");
    }

    /**
     * 通过id删除admin(待更改)
     * @param id 管理员id
     * @return String
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation("通过ID删除管理员")
    public CommonResult<String> delete(@NotBlank @PathVariable String id){
        if(adminService.removeById(id))
            return CommonResult.success("删除成功！");
        return CommonResult.failed("删除失败！");
    }

    /**
     * 添加管理员
     * @param admin 管理员
     * @return String
     */
    @PostMapping("/add")
    @ApiOperation("添加管理员")
    public CommonResult<String> add(Admin admin){
        if(admin.getAdminId().equals("") && adminService.save(admin))
            return CommonResult.success("添加成功");
        return CommonResult.failed("添加失败");
    }

}
