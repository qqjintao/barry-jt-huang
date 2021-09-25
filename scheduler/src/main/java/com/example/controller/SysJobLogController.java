package com.example.controller;

import com.example.CommonPage;
import com.example.CommonResult;
import com.example.model.SysJobLog;
import com.example.service.ISysJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author barry.jt.huang
 */
@RestController
@RequestMapping("/monitor/jobLog")
public class SysJobLogController extends BaseController {
    @Autowired
    private ISysJobLogService jobLogService;

    /**
     * 查询定时任务调度日志列表
     */
    @PreAuthorize("hasAuthority('monitor:job:list')")
    @GetMapping("/list")
    public CommonResult<CommonPage<SysJobLog>> list(SysJobLog sysJobLog, Date beginTime, Date endTime) {
        startPage();
        List<SysJobLog> list = jobLogService.selectJobLogList(sysJobLog, beginTime, endTime);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 根据调度编号获取详细信息
     */
    @PreAuthorize("hasAuthority('monitor:job:query')")
    @GetMapping(value = "/{configId}")
    public CommonResult getInfo(@PathVariable Long id) {
        return CommonResult.success(jobLogService.selectJobLogById(id));
    }


    /**
     * 删除定时任务调度日志
     */
    @PreAuthorize("hasAuthority('monitor:job:remove')")
    @DeleteMapping("/{ids}")
    public CommonResult remove(@PathVariable Long[] ids) {
        return CommonResult.toAjax(jobLogService.deleteJobLogByIds(ids));
    }

    /**
     * 清空定时任务调度日志
     */
    @PreAuthorize("hasAuthority('monitor:job:remove')")
    @DeleteMapping("/clean")
    public CommonResult clean() {
        jobLogService.cleanJobLog();
        return CommonResult.success();
    }
}
