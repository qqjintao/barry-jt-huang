package com.example.controller;

import com.example.CommonPage;
import com.example.CommonResult;
import com.example.model.SysJob;
import com.example.service.ISysJobService;
import com.example.util.CronUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author barry.jt.huang
 */
@RestController
@RequestMapping("/monitor/job")
public class SysJobController extends BaseController {

    @Autowired
    private ISysJobService jobService;

    /**
     * 查询定时任务列表
     */
    @PreAuthorize("hasAuthority('monitor:job:list')")
    @GetMapping("/list")
    public CommonResult<CommonPage<SysJob>> list(SysJob sysJob) {
        startPage();
        List<SysJob> list = jobService.selectJobList(sysJob);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 获取定时任务详细信息
     */
    @PreAuthorize("hasAuthority('monitor:job:query')")
    @GetMapping(value = "/{jobId}")
    public CommonResult getInfo(@PathVariable("jobId") Long jobId) {
        return CommonResult.success(jobService.selectJobById(jobId));
    }

    /**
     * 新增定时任务
     */
    @PreAuthorize("hasAuthority('monitor:job:add')")
    @PostMapping
    public CommonResult add(@RequestBody SysJob sysJob) throws SchedulerException {
        if (!CronUtils.isValid(sysJob.getCronExpression())) {
            return CommonResult.failed("cron表达式不正确");
        }
        return CommonResult.toAjax(jobService.insertJob(sysJob));
    }

    /**
     * 修改定时任务
     */
    @PreAuthorize("hasAuthority('monitor:job:edit')")
    @PutMapping
    public CommonResult edit(@RequestBody SysJob sysJob) throws SchedulerException {
        if (!CronUtils.isValid(sysJob.getCronExpression())) {
            return CommonResult.failed("cron表达式不正确");
        }
        return CommonResult.toAjax(jobService.updateJob(sysJob));
    }

    /**
     * 定时任务状态修改
     */
    @PreAuthorize("hasAuthority('monitor:job:changeStatus')")
    @PutMapping("/changeStatus")
    public CommonResult changeStatus(@RequestBody SysJob job) throws SchedulerException {
        SysJob newJob = jobService.selectJobById(job.getId());
        newJob.setStatus(job.getStatus());
        return CommonResult.toAjax(jobService.changeStatus(newJob));
    }

    /**
     * 定时任务立即执行一次
     */
    @PreAuthorize("hasAuthority('monitor:job:changeStatus')")
    @PutMapping("/run")
    public CommonResult run(@RequestBody SysJob job) throws SchedulerException {
        jobService.run(job);
        return CommonResult.success();
    }

    /**
     * 删除定时任务
     */
    @PreAuthorize("hasAuthority('monitor:job:remove')")
    @DeleteMapping("/{jobIds}")
    public CommonResult remove(@PathVariable Long[] jobIds) throws SchedulerException {
        jobService.deleteJobByIds(jobIds);
        return CommonResult.success();
    }
}
