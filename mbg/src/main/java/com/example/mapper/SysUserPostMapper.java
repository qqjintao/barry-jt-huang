package com.example.mapper;

import com.example.model.SysUserPost;
import com.example.model.SysUserPostExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserPostMapper {
    long countByExample(SysUserPostExample example);

    int deleteByExample(SysUserPostExample example);

    int deleteByPrimaryKey(@Param("userId") Long userId, @Param("postId") Long postId);

    int insert(SysUserPost record);

    int insertSelective(SysUserPost record);

    List<SysUserPost> selectByExample(SysUserPostExample example);

    int updateByExampleSelective(@Param("record") SysUserPost record, @Param("example") SysUserPostExample example);

    int updateByExample(@Param("record") SysUserPost record, @Param("example") SysUserPostExample example);
}