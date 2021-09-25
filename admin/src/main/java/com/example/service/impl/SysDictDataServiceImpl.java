package com.example.service.impl;

import com.example.mapper.SysDictDataMapper;
import com.example.model.SysDictData;
import com.example.model.SysDictDataExample;
import com.example.service.ISysDictDataService;
import com.example.until.DictUtils;
import com.example.untils.StringUtils;
import com.example.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author barry.jt.huang
 */
@Service
public class SysDictDataServiceImpl implements ISysDictDataService {
    @Autowired
    private SysDictDataMapper dictDataMapper;

    /**
     * 根据条件分页查询字典数据
     *
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataList(SysDictData dictData) {
        SysDictDataExample example = new SysDictDataExample();
        SysDictDataExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotEmpty(dictData.getDictType())){
            criteria.andDictTypeEqualTo(dictData.getDictType());
        }
        if(StringUtils.isNotEmpty(dictData.getDictLabelZh())){
            criteria.andDictLabelZhLike(StringUtils.getSqlLike(dictData.getDictLabelZh()));
        }
        if(StringUtils.isNotEmpty(dictData.getDictLabelEn())){
            criteria.andDictLabelEnLike(StringUtils.getSqlLike(dictData.getDictLabelEn()));
        }
        if(StringUtils.isNotEmpty(dictData.getStatus())){
            criteria.andStatusEqualTo(dictData.getStatus());
        }
        example.setOrderByClause("dict_sort asc");
        return dictDataMapper.selectByExample(example);
    }

    /**
     * 根据字典数据ID查询信息
     *
     * @param id 字典数据ID
     * @return 字典数据
     */
    @Override
    public SysDictData selectDictDataById(Long id) {
        return dictDataMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除字典数据信息
     *
     * @param ids 需要删除的字典数据ID
     * @return 结果
     */
    @Override
    public int deleteDictDataByIds(Long[] ids) {
        SysDictDataExample example = new SysDictDataExample();
        SysDictDataExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        int row = dictDataMapper.deleteByExample(example);
        if (row > 0) {
            DictUtils.clearDictCache();
        }
        return row;
    }

    /**
     * 新增保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    @Override
    public int insertDictData(SysDictData dictData) {
        dictData.setCreateBy(SecurityUtils.getUserId());
        dictData.setCreateTime(new Date());
        int row = dictDataMapper.insertSelective(dictData);
        if (row > 0) {
            DictUtils.clearDictCache();
        }
        return row;
    }

    /**
     * 修改保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    @Override
    public int updateDictData(SysDictData dictData) {
        int row = dictDataMapper.updateByPrimaryKeySelective(dictData);
        if (row > 0) {
            DictUtils.clearDictCache();
        }
        return row;
    }
}