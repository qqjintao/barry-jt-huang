package com.example.service.impl;

import com.example.dao.SysJobLogDao;
import com.example.mapper.SysJobLogMapper;
import com.example.model.SysJobLog;
import com.example.model.SysJobLogExample;
import com.example.service.ISysJobLogService;
import com.example.untils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author barry.jt.huang
 */
@Service
public class SysJobLogServiceImpl implements ISysJobLogService {
    @Autowired
    private SysJobLogDao jobLogDao;
    @Autowired
    private SysJobLogMapper jobLogMapper;

    /**
     * 获取quartz调度器日志的计划任务
     *
     * @param jobLog 调度日志信息
     * @return 调度任务日志集合
     */
    @Override
    public List<SysJobLog> selectJobLogList(SysJobLog jobLog, Date beginTime, Date endTime) {
        SysJobLogExample example = new SysJobLogExample();
        SysJobLogExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(jobLog.getJobName())) {
            criteria.andJobNameLike(StringUtils.getSqlLike(jobLog.getJobName()));
        }
        if (StringUtils.isNotEmpty(jobLog.getJobGroup())) {
            criteria.andJobGroupEqualTo(jobLog.getJobGroup());
        }
        if (StringUtils.isNotEmpty(jobLog.getStatus())) {
            criteria.andStatusEqualTo(jobLog.getStatus());
        }
        if (StringUtils.isNotEmpty(jobLog.getInvokeTarget())) {
            criteria.andInvokeTargetLike(StringUtils.getSqlLike(jobLog.getInvokeTarget()));
        }
        if (StringUtils.isNotEmpty(beginTime) && StringUtils.isNotEmpty(endTime)) {
            criteria.andCreateTimeBetween(beginTime, endTime);
        }
        return jobLogMapper.selectByExample(example);
    }

    /**
     * 通过调度任务日志ID查询调度信息
     *
     * @param id 调度任务日志ID
     * @return 调度任务日志对象信息
     */
    @Override
    public SysJobLog selectJobLogById(Long id) {
        return jobLogMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增任务日志
     *
     * @param jobLog 调度日志信息
     */
    @Override
    public void addJobLog(SysJobLog jobLog) {
        jobLogMapper.insertSelective(jobLog);
    }

    /**
     * 批量删除调度日志信息
     *
     * @param logIds 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteJobLogByIds(Long[] logIds) {
        SysJobLogExample example = new SysJobLogExample();
        SysJobLogExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(logIds));
        return jobLogMapper.deleteByExample(example);
    }

    /**
     * 删除任务日志
     *
     * @param jobId 调度日志ID
     */
    @Override
    public int deleteJobLogById(Long jobId) {
        return jobLogMapper.deleteByPrimaryKey(jobId);
    }

    /**
     * 清空任务日志
     */
    @Override
    public void cleanJobLog() {
        jobLogDao.cleanJobLog();
    }
}

