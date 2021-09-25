package com.example.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.constant.Constants;
import com.example.constant.SysConstants;
import com.example.dto.SysDictDataDTO;
import com.example.mapper.SysDictDataMapper;
import com.example.mapper.SysDictTypeMapper;
import com.example.model.SysDictData;
import com.example.model.SysDictDataExample;
import com.example.model.SysDictType;
import com.example.model.SysDictTypeExample;
import com.example.service.ISysDictTypeService;
import com.example.until.DictUtils;
import com.example.untils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author barry.jt.huang
 */
@Service
public class SysDictTypeServiceImpl implements ISysDictTypeService {
    @Autowired
    private SysDictTypeMapper dictTypeMapper;

    @Autowired
    private SysDictDataMapper dictDataMapper;

    /**
     * 项目启动时，初始化字典到缓存
     */
    @PostConstruct
    public void init() {
        SysDictTypeExample example = new SysDictTypeExample();
        SysDictTypeExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(SysConstants.STATUS_ON);
        List<SysDictType> dictTypeList = dictTypeMapper.selectByExample(example);
        for (SysDictType dictType : dictTypeList) {
            SysDictDataExample example1 = new SysDictDataExample();
            SysDictDataExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andDictTypeEqualTo(dictType.getDictType());
            criteria1.andStatusEqualTo("1");
            example1.setOrderByClause("dict_sort asc");
            List<SysDictData> dictData = dictDataMapper.selectByExample(example1);
            List<SysDictDataDTO> dictDates = new ArrayList<>();
            for(SysDictData entity:dictData){
                SysDictDataDTO dto = new SysDictDataDTO();
                dto.setCssClass(entity.getCssClass());
                dto.setDictLabel(entity.getDictLabelZh());
                dto.setDictType(entity.getDictType());
                dto.setDictValue(entity.getDictValue());
                dto.setIsDefault(entity.getIsDefault());
                dto.setListClass(entity.getListClass());
                dictDates.add(dto);
            }
            DictUtils.setDictCache(dictType.getDictType(), dictDates);
        }
    }

    /**
     * 根据条件分页查询字典类型
     *
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeList(SysDictType dictType, Date beginTime, Date endTime) {
        SysDictTypeExample example = new SysDictTypeExample();
        SysDictTypeExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotEmpty(dictType.getDictName())){
            criteria.andDictNameLike(StringUtils.getSqlLike(dictType.getDictName()));
        }
        if(StringUtils.isNotEmpty(dictType.getStatus())){
            criteria.andStatusEqualTo(dictType.getStatus());
        }
        if(StringUtils.isNotEmpty(dictType.getDictType())){
            criteria.andDictTypeLike(StringUtils.getSqlLike(dictType.getDictType()));
        }
        if (StringUtils.isNotEmpty(beginTime) && StringUtils.isNotEmpty(endTime)) {
            criteria.andCreateTimeBetween(beginTime, endTime);
        }
        return dictTypeMapper.selectByExample(example);
    }

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeAll() {
        return dictTypeMapper.selectByExample(new SysDictTypeExample());
    }

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictDataDTO> selectDictDataByType(String dictType) {
        List<SysDictDataDTO> dictDates = DictUtils.getDictCache(dictType);
        if (StringUtils.isNotEmpty(dictDates)) {
            return dictDates;
        }
        SysDictDataExample example=new SysDictDataExample();
        SysDictDataExample.Criteria criteria = example.createCriteria();
        criteria.andDictTypeEqualTo(dictType);
        criteria.andStatusEqualTo(SysConstants.STATUS_ON);
        example.setOrderByClause("dict_sort asc");
        List<SysDictData> dictData = dictDataMapper.selectByExample(example);
        dictDates = new ArrayList<>();
        for(SysDictData entity:dictData){
            SysDictDataDTO dto = new SysDictDataDTO();
            dto.setCssClass(entity.getCssClass());
            dto.setDictLabel(entity.getDictLabelZh());
            dto.setDictType(entity.getDictType());
            dto.setDictValue(entity.getDictValue());
            dto.setIsDefault(entity.getIsDefault());
            dto.setListClass(entity.getListClass());
            dictDates.add(dto);
        }
        if (StringUtils.isNotEmpty(dictDates)) {
            DictUtils.setDictCache(dictType, dictDates);
            return dictDates;
        }
        return null;
    }

    /**
     * 根据字典类型ID查询信息
     *
     * @param id 字典类型ID
     * @return 字典类型
     */
    @Override
    public SysDictType selectDictTypeById(Long id) {
        return dictTypeMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据字典类型查询信息
     *
     * @param dictType 字典类型
     * @return 字典类型
     */
    @Override
    public SysDictType selectDictTypeByType(String dictType) {
        SysDictTypeExample example = new SysDictTypeExample();
        SysDictTypeExample.Criteria criteria = example.createCriteria();
        criteria.andDictTypeEqualTo(dictType);
        return dictTypeMapper.selectByExample(example).get(0);
    }

    /**
     * 批量删除字典类型信息
     *
     * @param ids 需要删除的字典ID
     * @return 结果
     */
    @Override
    public int deleteDictTypeByIds(Long[] ids) {
        for (Long id : ids) {
            SysDictType dictType = selectDictTypeById(id);
            SysDictDataExample example = new SysDictDataExample();
            SysDictDataExample.Criteria criteria = example.createCriteria();
            criteria.andDictTypeEqualTo(dictType.getDictType());
            if (dictDataMapper.countByExample(example) > 0) {
                throw new RuntimeException(String.format("%1$s已分配,不能删除", dictType.getDictName()));
            }
        }
        SysDictTypeExample example = new SysDictTypeExample();
        SysDictTypeExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        int count = dictTypeMapper.deleteByExample(example);
        if (count > 0) {
            DictUtils.clearDictCache();
        }
        return count;
    }

    /**
     * 清空缓存数据
     */
    @Override
    public void clearCache() {
        DictUtils.clearDictCache();
    }

    /**
     * 新增保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Override
    public int insertDictType(SysDictType dictType) {
        int row = dictTypeMapper.insertSelective(dictType);
        if (row > 0) {
            DictUtils.clearDictCache();
        }
        return row;
    }

    /**
     * 修改保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateDictType(SysDictType dictType) {
        SysDictDataExample example = new SysDictDataExample();
        SysDictDataExample.Criteria criteria = example.createCriteria();
        criteria.andDictTypeEqualTo(dictType.getDictType());
        SysDictData sysDictData = new SysDictData();
        sysDictData.setDictType(dictType.getDictType());
        dictDataMapper.updateByExampleSelective(sysDictData, example);
        int row = dictTypeMapper.updateByPrimaryKeySelective(dictType);
        if (row > 0) {
            DictUtils.clearDictCache();
        }
        return row;
    }

    /**
     * 校验字典类型称是否唯一
     *
     * @param dict 字典类型
     * @return 结果
     */
    @Override
    public String checkDictTypeUnique(SysDictType dict) {
        Long id = StringUtils.isNull(dict.getId()) ? -1L : dict.getId();
        SysDictTypeExample example = new SysDictTypeExample();
        SysDictTypeExample.Criteria criteria = example.createCriteria();
        criteria.andDictTypeEqualTo(dict.getDictType());
        List<SysDictType> list = dictTypeMapper.selectByExample(example);
        if(StringUtils.isNotEmpty(list)){
            SysDictType dictType = list.get(0);
            if (StringUtils.isNotNull(dictType) && dictType.getId().longValue() != id.longValue()) {
                return Constants.NOT_UNIQUE;
            }
        }
        return Constants.UNIQUE;
    }
}
