package com.example.mapper;

import com.example.model.SysDeptUser;
import com.example.model.SysDeptUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysDeptUserMapper {
    long countByExample(SysDeptUserExample example);

    int deleteByExample(SysDeptUserExample example);

    int deleteByPrimaryKey(@Param("userId") Long userId, @Param("deptId") Long deptId);

    int insert(SysDeptUser record);

    int insertSelective(SysDeptUser record);

    List<SysDeptUser> selectByExample(SysDeptUserExample example);

    int updateByExampleSelective(@Param("record") SysDeptUser record, @Param("example") SysDeptUserExample example);

    int updateByExample(@Param("record") SysDeptUser record, @Param("example") SysDeptUserExample example);
}