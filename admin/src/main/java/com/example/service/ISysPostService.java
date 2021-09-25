package com.example.service;

import com.example.model.SysPost;

import java.util.List;

/**
 * @Author: barry.jt.huang
 * @Date: 2020/11/29 0029
 */
public interface ISysPostService {
    /**
     * 查询岗位信息集合
     *
     * @param post 岗位信息
     * @return 岗位列表
     */
    List<SysPost> selectPostList(SysPost post);

    /**
     * 查询所有岗位
     *
     * @return 岗位列表
     */
    List<SysPost> selectPostAll();

    /**
     * 通过岗位ID查询岗位信息
     *
     * @param id 岗位ID
     * @return 角色对象信息
     */
    SysPost selectPostById(Long id);

    /**
     * 根据deptIds获取对应岗位
     * @param deptIds
     * @return
     */
    List<SysPost> selectPostByDeptIds(Long[] deptIds);

    /**
     * 批量删除岗位信息
     *
     * @param ids 需要删除的岗位ID
     * @return 结果
     * @throws Exception 异常
     */
    int deletePostByIds(Long[] ids);

    /**
     * 新增保存岗位信息
     *
     * @param post 岗位信息
     * @return 结果
     */
    int insertPost(SysPost post);

    /**
     * 修改保存岗位信息
     *
     * @param post 岗位信息
     * @return 结果
     */
    int updatePost(SysPost post);
}
