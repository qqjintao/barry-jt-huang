package com.example.mapper;

import com.example.model.GenTableColumn;
import com.example.model.GenTableColumnExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GenTableColumnMapper {
    long countByExample(GenTableColumnExample example);

    int deleteByExample(GenTableColumnExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GenTableColumn record);

    int insertSelective(GenTableColumn record);

    List<GenTableColumn> selectByExample(GenTableColumnExample example);

    GenTableColumn selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GenTableColumn record, @Param("example") GenTableColumnExample example);

    int updateByExample(@Param("record") GenTableColumn record, @Param("example") GenTableColumnExample example);

    int updateByPrimaryKeySelective(GenTableColumn record);

    int updateByPrimaryKey(GenTableColumn record);
}