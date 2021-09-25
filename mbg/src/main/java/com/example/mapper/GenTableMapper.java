package com.example.mapper;

import com.example.model.GenTable;
import com.example.model.GenTableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GenTableMapper {
    long countByExample(GenTableExample example);

    int deleteByExample(GenTableExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GenTable record);

    int insertSelective(GenTable record);

    List<GenTable> selectByExample(GenTableExample example);

    GenTable selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GenTable record, @Param("example") GenTableExample example);

    int updateByExample(@Param("record") GenTable record, @Param("example") GenTableExample example);

    int updateByPrimaryKeySelective(GenTable record);

    int updateByPrimaryKey(GenTable record);
}