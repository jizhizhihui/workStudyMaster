package com.workStudy.core.project.controller;

import com.workStudy.common.result.CommonResult;
import com.workStudy.core.project.entity.Job;
import com.workStudy.core.project.entity.StuApply;
import com.workStudy.core.project.service.JobService;
import com.workStudy.core.project.service.StuApplyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
* <p>
*  前端控制器
* </p>
*
* @author JZhi
* @since 2020-04-16
*/
@RestController
@RequestMapping("/stuApply")
@Api(tags = "学生申请")
public class StuApplyController {
    @Autowired
    StuApplyService stuApplyService;

    /**
     * 获取job集合
     * @return jobList
     */
    @GetMapping("/list")
    public CommonResult<Object> getList(){
        return CommonResult.success(stuApplyService.list());
    }

    /**
     * 通过id获取job
     * @param id 管理员id
     * @return job
     */
    @PostMapping("/getById")
    public CommonResult<Object> getJobById(@NotBlank @RequestParam String id){
        StuApply stuApply = stuApplyService.getById(id);
        if(stuApply != null)
            return CommonResult.success(stuApply);
        return CommonResult.failed("id错误!");
    }
    /**
     * 更新job信息
     * @param stuApply 管理员
     * @return String
     */
    @PutMapping("/update")
    public CommonResult<String> update(StuApply stuApply){
        if(stuApplyService.updateById(stuApply))
            return CommonResult.success("更新成功！");
        return CommonResult.failed("更新失败！");
    }

    /**
     * 通过id删除job(待更改)
     * @param id 管理员id
     * @return String
     */
    @DeleteMapping("/delete/{id}")
    public CommonResult<String> delete(@NotBlank @PathVariable String id){
        if(stuApplyService.removeById(id))
            return CommonResult.success("删除成功！");
        return CommonResult.failed("删除失败！");
    }

    /**
     * 添加管理员
     * @param stuApply 管理员
     * @return String
     */
    @PostMapping("/add")
    public CommonResult<String> add(StuApply stuApply){
        if(stuApply.getStuApplyId().equals("") && stuApplyService.save(stuApply))
            return CommonResult.success("添加成功");
        return CommonResult.failed("添加失败");
    }

    /**
     * 通过PostID 获取 未审核 的工作信息
     * @param id postId
     * @return List
     */
    @PostMapping("/getStuApplyByState")
    public CommonResult<Object> getStuApplyByState(@NotBlank @RequestParam int id,
                                            @RequestParam int pageNum,
                                            @RequestParam int pageSize){
        List list = stuApplyService.getStuApplyByState(id , pageNum, pageSize);
        if(list != null)
            return CommonResult.success(list);
        return CommonResult.failed("id错误!");
    }

    /**
     * 通过PostID获取工作信息
     * @param id postId
     * @return List
     */
    @PostMapping("/getByPostId")
    public CommonResult<Object> getByPostId(@NotBlank @RequestParam int id,
                                                   @RequestParam int pageNum,
                                                   @RequestParam int pageSize){
        List list = stuApplyService.getByPostId(id , pageNum, pageSize);
        if(list != null)
            return CommonResult.success(list);
        return CommonResult.failed("id错误!");
    }
}
