package com.example.mapper;

import com.example.model.SysLoginInfo;
import com.example.model.SysLoginInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysLoginInfoMapper {
    long countByExample(SysLoginInfoExample example);

    int deleteByExample(SysLoginInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysLoginInfo record);

    int insertSelective(SysLoginInfo record);

    List<SysLoginInfo> selectByExample(SysLoginInfoExample example);

    SysLoginInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysLoginInfo record, @Param("example") SysLoginInfoExample example);

    int updateByExample(@Param("record") SysLoginInfo record, @Param("example") SysLoginInfoExample example);

    int updateByPrimaryKeySelective(SysLoginInfo record);

    int updateByPrimaryKey(SysLoginInfo record);
}