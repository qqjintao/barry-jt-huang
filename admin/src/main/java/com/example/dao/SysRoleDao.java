package com.example.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: barry.jt.huang
 * @Date: 2021/1/4 0004
 */
public interface SysRoleDao {

    /**
     * 根据用户ID获取角色列表
     *
     * @param userId 用户ID
     * @return 选中角色Name列表
     */
    List<String> selectRoleNameListByUserId(@Param("userId") Long userId);
}
