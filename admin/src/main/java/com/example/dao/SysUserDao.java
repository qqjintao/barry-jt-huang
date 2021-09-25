package com.example.dao;

import com.example.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author barry.jt.huang
 */
public interface SysUserDao {

    /**
     * 点击部门树获取岗位列表
     * @param
     * @return
     */
    List<SysUser> selectUserList(@Param("user")SysUser user, @Param("deptId")Long deptId,@Param("beginTime")Date beginTime,@Param("endTime")Date endTime);
}
