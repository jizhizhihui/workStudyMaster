package com.workStudy.core.project.controller;

import com.workStudy.common.result.CommonResult;
import com.workStudy.core.project.entity.Post;
import com.workStudy.core.project.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
* @since 2020-06-03
*/
@RestController
@RequestMapping("/post")
@Api(tags = "岗位管理")
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * 获取post集合
     * @return postList
     */
    @GetMapping("/list")
    @ApiOperation("获取岗位信息集合")
    public CommonResult<Object> getList(){
        return CommonResult.success(postService.list());
    }

    /**
     * 通过id获取post
     * @param id PostId
     * @return post
     */
    @PostMapping("/getByPostId")
    @ApiOperation("通过岗位id获取岗位信息")
    public CommonResult<Object> getPostById(@NotBlank @RequestParam String id){
        Post post = postService.getById(id);
        if(post != null)
            return CommonResult.success(post);
        return CommonResult.failed("id错误!");
    }

    /**
     * 通过id获取post
     * @param id 商家id
     * @return List
     */
    @PostMapping("/getListByManagerId")
    @ApiOperation("通过商家ID获取岗位信息集合")
    public CommonResult<Object> getByManagerId(@NotBlank @RequestParam  int id){
        List<Post> posts = postService.getByManagerId(id);
        if(posts != null)
            return CommonResult.success(posts);
        return CommonResult.failed("id错误!");
    }

    /**
     * 分页查询 岗位信息
     *
     * @param id 商家id
     * @param pageNum 页数
     * @param pageSize 每页条数
     * @return List
     */
    @PostMapping("/getPageByManagerId")
    @ApiOperation("分页查询岗位信息")
    public CommonResult<Object> getPageByManagerId(@NotBlank @RequestParam  int id,
                                                   @RequestParam int pageNum,
                                                   @RequestParam int pageSize){
        List list = postService.getPageByManagerId(id, pageNum, pageSize);
        if(list != null)
            return CommonResult.success(list);
        return CommonResult.failed("参数错误!");
    }

    /**
     * 分页查询 待审核岗位信息
     *
     * @param id 商家id
     * @param pageNum 页数
     * @param pageSize 每页条数
     * @return List
     */
    @PostMapping("/getExamineByState")
    @ApiOperation("分页查询待审核岗位信息")
    public CommonResult<Object> getExamineByState(@NotBlank @RequestParam  int id,
                                                   @RequestParam int pageNum,
                                                   @RequestParam int pageSize){
        List list = postService.getExamineByState(id, pageNum, pageSize);
        if(list != null)
            return CommonResult.success(list);
        return CommonResult.failed("参数错误!");
    }

    /**
     * 更新post信息
     * @param post 管理员
     * @return String
     */
    @PutMapping("/update")
    @ApiOperation("更新岗位信息")
    public CommonResult<String> update(@RequestParam Post post){
        if(postService.updateById(post))
            return CommonResult.success("更新成功！");
        return CommonResult.failed("更新失败！");
    }

    /**
     * 通过id删除post(待更改)
     * @param id 管理员id
     * @return String
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation("通过ID删除岗位")
    public CommonResult<String> delete(@NotBlank @RequestParam String id){
        if(postService.removeById(id))
            return CommonResult.success("删除成功！");
        return CommonResult.failed("删除失败！");
    }

    /**
     * 添加管理员
     * @param post 管理员
     * @return String
     */
    @PostMapping("/add")
    @ApiOperation("添加岗位")
    public CommonResult<String> add(@RequestParam Post post){
        if(post.getPostId().equals("") && postService.save(post))
            return CommonResult.success("添加成功");
        return CommonResult.failed("添加失败");
    }
}
