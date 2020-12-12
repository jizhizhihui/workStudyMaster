package com.workStudy.core.project.controller;

import com.workStudy.common.result.CommonResult;
import com.workStudy.core.project.entity.Manager;
import com.workStudy.core.project.service.ManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
* <p>
*  前端控制器
* </p>
*
* @author JZhi
* @since 2020-04-02
*/
@RestController
@RequestMapping("/manager")
@Api(tags = "商家")
@Log4j2
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    /**
     * 获取manager集合
     * @return managerList
     */
    @GetMapping("/list")
    @ApiOperation("获取商家信息集合")
    public CommonResult getList(){
        return CommonResult.success(managerService.list());
    }

    /**
     * 通过id获取manager
     * @param id 商家id
     * @return manager
     */
    @PostMapping("/getById")
    @ApiOperation("通过ID获取商家信息")
    public CommonResult getManagerById(@NotBlank @RequestParam String id){
        Manager manager = managerService.getById(id);
        if(manager != null)
            return CommonResult.success(manager);
        return CommonResult.failed("id错误!");
    }

    /**
     * 更新manager信息
     * @param manager 商家
     * @return String
     */
    @PutMapping("/update")
    @ApiOperation("更新商家信息")
    public CommonResult update(Manager manager){
        if(managerService.updateById(manager))
            return CommonResult.success("更新成功！");
        return CommonResult.failed("更新失败！");
    }

    /**
     * 通过id删除 manager (待更改)
     * @param id 商家id
     * @return String
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation("通过ID删除商家")
    public CommonResult delete(@PathVariable String id){
        if(managerService.removeById(id))
            return CommonResult.success("删除成功！");
        return CommonResult.failed("删除失败！");
    }

    /**
     * 添加商家
     * @param manager 商家
     * @return String
     */
    @PostMapping("/add")
    @ApiOperation("添加商家")
    public CommonResult add(Manager manager){
        if(manager.getId() != null && managerService.save(manager))
            return CommonResult.success("添加成功");
        return CommonResult.failed("添加失败");
    }
}
