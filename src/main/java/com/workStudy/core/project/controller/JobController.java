package com.workStudy.core.project.controller;

import com.workStudy.common.result.CommonResult;
import com.workStudy.core.project.entity.Job;
import com.workStudy.core.project.service.JobService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
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
@RequestMapping("/job")
@Api(tags = "工作情况")
@Log4j2
public class JobController {

    @Autowired
    JobService jobService;

    /**
     * 获取job集合
     * @return jobList
     */
    @GetMapping("/list")
    public CommonResult<Object> getList(){
        return CommonResult.success(jobService.list());
    }

    /**
     * 通过id获取job
     * @param id 管理员id
     * @return job
     */
    @PostMapping("/getById")
    public CommonResult<Object> getJobById(@NotBlank @RequestParam  String id){
        Job job = jobService.getById(id);
        if(job != null)
            return CommonResult.success(job);
        return CommonResult.failed("id错误!");
    }


    /**
     * 更新job信息
     * @return String
     */
    @PutMapping("/update")
    public CommonResult<String> update(Job job){
        if(jobService.updateById(job))
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
        if(jobService.removeById(id))
            return CommonResult.success("删除成功！");
        return CommonResult.failed("删除失败！");
    }


    /**
     * 通过PostID获取job信息
     * @param id postId
     * @return List
     */
    @PostMapping("/getByPostId")
    public CommonResult<Object> getByPostId(@NotBlank @RequestParam int id,
                                            @RequestParam int pageNum,
                                            @RequestParam int pageSize){
        log.error("ID:" + id + "num: " + pageNum + "Size: " + pageSize);
        List list = jobService.getByPostId(id , pageNum, pageSize);
        if(list != null)
            return CommonResult.success(list);
        return CommonResult.failed("id错误!");
    }

    /**
     * 通过 ManagerID 获取所有job信息
     * @param id managerId
     * @return List
     */
    @PostMapping("/getByManagerId")
    public CommonResult<Object> getByManagerId(@NotBlank @RequestParam int id,
                                            @RequestParam int pageNum,
                                            @RequestParam int pageSize){
        List list = jobService.getByManagerId(id , pageNum, pageSize);
        if(list != null)
            return CommonResult.success(list);
        return CommonResult.failed("id错误!");
    }

    /**
     * 添加工作
     * @param postId
     * @param studentId
     * @return String
     */
    @PostMapping("/add")
    public CommonResult<String> add(int postId, String studentId){
        Job job = null;
        job.setPostId(postId);
        job.setStudentId(studentId);
        if(job.getJobId().equals("") && jobService.save(job))
            return CommonResult.success("添加成功");
        return CommonResult.failed("添加失败");
    }
}
