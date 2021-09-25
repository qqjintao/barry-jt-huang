package com.example.service;

import com.example.model.SysDictData;

import java.util.List;

/**
 * @author barry.jt.huang
 */
public interface ISysDictDataService {
    /**
     * 根据条件分页查询字典数据
     *
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    public List<SysDictData> selectDictDataList(SysDictData dictData);


    /**
     * 根据字典数据ID查询信息
     *
     * @param id 字典数据ID
     * @return 字典数据
     */
    public SysDictData selectDictDataById(Long id);

    /**
     * 批量删除字典数据信息
     *
     * @param ids 需要删除的字典数据ID
     * @return 结果
     */
    public int deleteDictDataByIds(Long[] ids);

    /**
     * 新增保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    public int insertDictData(SysDictData dictData);

    /**
     * 修改保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    public int updateDictData(SysDictData dictData);
}

