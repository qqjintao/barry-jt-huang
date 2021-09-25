package com.example.service.impl;

import com.example.mapper.SysOperLogMapper;
import com.example.model.SysOperLog;
import com.example.model.SysOperLogExample;
import com.example.untils.StringUtils;
import com.example.service.ISysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: barry.jt.huang
 * @Date: 2020/11/14 0014
 */
@Service
public class SysOperLogServiceImpl implements ISysOperLogService {
    @Autowired
    private SysOperLogMapper operLogMapper;

    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    @Override
    public void insertOperlog(SysOperLog operLog) {
        operLogMapper.insertSelective(operLog);
    }

    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    @Override
    public List<SysOperLog> selectOperLogList(SysOperLog operLog) {
        SysOperLogExample example = new SysOperLogExample();
        SysOperLogExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(operLog.getTitle())) {
            criteria.andTitleLike(StringUtils.getSqlLike(operLog.getTitle()));
        }
        if (StringUtils.isNotEmpty(operLog.getStatus())) {
            criteria.andStatusEqualTo(operLog.getStatus());
        }
        return operLogMapper.selectByExample(example);
    }

    /**
     * 批量删除系统操作日志
     *
     * @param operIds 需要删除的操作日志ID
     * @return 结果
     */
    @Override
    public int deleteOperLogByIds(Long[] operIds) {
        SysOperLogExample example = new SysOperLogExample();
        SysOperLogExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(operIds));
        return operLogMapper.deleteByExample(example);
    }

    /**
     * 查询操作日志详细
     *
     * @param operId 操作ID
     * @return 操作日志对象
     */
    @Override
    public SysOperLog selectOperLogById(Long operId) {
        return operLogMapper.selectByPrimaryKey(operId);
    }
}

