package com.example.dao;

import com.example.model.SysPost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author barry.jt.huang
 */
public interface SysPostDao {

    /**
     * 点击部门树获取岗位列表
     * @param post
     * @return
     */
    List<SysPost> selectPostList(@Param("post") SysPost post);

    /**
     * 根据用户ID获取岗位列表
     *
     * @param userId 用户ID
     * @return 选中岗位Name列表
     */
    List<String> selectPostNameListByUserId(@Param("userId") Long userId);
}
