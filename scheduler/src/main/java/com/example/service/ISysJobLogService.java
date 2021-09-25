package com.example.service;

import com.example.model.SysJobLog;

import java.util.Date;
import java.util.List;

/**
 * @author barry.jt.huang
 */
public interface ISysJobLogService {
    /**
     * 获取quartz调度器日志的计划任务
     * @return 调度任务日志集合
     */
    List<SysJobLog> selectJobLogList(SysJobLog jobLog, Date beginTime, Date endTime);

    /**
     * 通过调度任务日志ID查询调度信息
     * @return 调度任务日志对象信息
     */
    SysJobLog selectJobLogById(Long id);

    /**
     * 新增任务日志
     */
    void addJobLog(SysJobLog jobLog);

    /**
     * 批量删除调度日志信息
     */
    int deleteJobLogByIds(Long[] logIds);

    /**
     * 删除任务日志
     */
    int deleteJobLogById(Long jobId);

    /**
     * 清空任务日志
     */
    void cleanJobLog();
}
