package com.example.mapper;

import com.example.model.SysUserDept;
import com.example.model.SysUserDeptExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserDeptMapper {
    long countByExample(SysUserDeptExample example);

    int deleteByExample(SysUserDeptExample example);

    int deleteByPrimaryKey(@Param("userId") Long userId, @Param("deptId") Long deptId);

    int insert(SysUserDept record);

    int insertSelective(SysUserDept record);

    List<SysUserDept> selectByExample(SysUserDeptExample example);

    int updateByExampleSelective(@Param("record") SysUserDept record, @Param("example") SysUserDeptExample example);

    int updateByExample(@Param("record") SysUserDept record, @Param("example") SysUserDeptExample example);
}