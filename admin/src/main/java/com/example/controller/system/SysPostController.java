package com.example.controller.system;

import com.example.CommonPage;
import com.example.CommonResult;
import com.example.controller.BaseController;
import com.example.model.SysPost;
import com.example.service.ISysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: barry.jt.huang
 * @Date: 2020/11/29 0029
 */
@RestController
@RequestMapping("/system/post")
public class SysPostController extends BaseController {
    @Autowired
    private ISysPostService postService;

    /**
     * 获取岗位列表
     */
    @GetMapping("/list")
    public CommonResult<CommonPage<SysPost>> list(SysPost post) {
        startPage();
        List<SysPost> list = postService.selectPostList(post);
        return CommonResult.success(CommonPage.restPage(list));
    }


    /**
     * 根据岗位编号获取详细信息
     */
    @GetMapping(value = "/{id}")
    public CommonResult getInfo(@PathVariable Long id) {
        return CommonResult.success(postService.selectPostById(id));
    }

    /**
     * 根据部门ids 获取对应岗位列表
     * @param deptIds
     * @return
     */
    @GetMapping(value = "getPostByDeptIds/{deptIds}")
    public CommonResult getPostByDeptIds(@PathVariable Long[] deptIds) {
        return CommonResult.success(postService.selectPostByDeptIds(deptIds));
    }

    /**
     * 新增岗位
     */
    @PostMapping
    public CommonResult add(@Validated @RequestBody SysPost post) {
        return CommonResult.toAjax(postService.insertPost(post));
    }

    /**
     * 修改岗位
     */
    @PutMapping
    public CommonResult edit(@Validated @RequestBody SysPost post) {
        return CommonResult.toAjax(postService.updatePost(post));
    }

    /**
     * 删除岗位
     */
    @DeleteMapping("/{ids}")
    public CommonResult remove(@PathVariable Long[] ids) {
        return CommonResult.success(postService.deletePostByIds(ids));
    }

}
