package com.example.service.impl;

import com.example.mapper.GenTableColumnMapper;
import com.example.model.GenTableColumn;
import com.example.model.GenTableColumnExample;
import com.example.service.IGenTableColumnService;
import com.example.text.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author barry.jt.huang
 */
@Service
public class GenTableColumnServiceImpl implements IGenTableColumnService {
    @Autowired
    private GenTableColumnMapper genTableColumnMapper;

    /**
     * 查询业务字段列表
     *
     * @param tableId 业务字段编号
     * @return 业务字段集合
     */
    @Override
    public List<GenTableColumn> selectGenTableColumnListByTableId(Long tableId) {
        GenTableColumnExample example = new GenTableColumnExample();
        GenTableColumnExample.Criteria criteria = example.createCriteria();
        criteria.andTableIdEqualTo(tableId);
        return genTableColumnMapper.selectByExample(example);
    }

    /**
     * 新增业务字段
     *
     * @param genTableColumn 业务字段信息
     * @return 结果
     */
    @Override
    public int insertGenTableColumn(GenTableColumn genTableColumn) {
        return genTableColumnMapper.insertSelective(genTableColumn);
    }

    /**
     * 修改业务字段
     *
     * @param genTableColumn 业务字段信息
     * @return 结果
     */
    @Override
    public int updateGenTableColumn(GenTableColumn genTableColumn) {
        return genTableColumnMapper.updateByPrimaryKeySelective(genTableColumn);
    }

    /**
     * 删除业务字段对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGenTableColumnByIds(String ids) {
        GenTableColumnExample example = new GenTableColumnExample();
        GenTableColumnExample.Criteria criteria = example.createCriteria();
        criteria.andTableIdIn(Arrays.asList(Convert.toLongArray(ids)));
        return genTableColumnMapper.deleteByExample(example);
    }
}

