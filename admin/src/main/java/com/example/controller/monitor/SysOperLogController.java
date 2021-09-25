package com.example.controller.monitor;

import com.example.CommonPage;
import com.example.CommonResult;
import com.example.controller.BaseController;
import com.example.model.SysOperLog;
import com.example.service.ISysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: barry.jt.huang
 * @Date: 2020/11/14 0014
 */
@RestController
@RequestMapping("/monitor/operLog")
public class SysOperLogController extends BaseController
{
    @Autowired
    private ISysOperLogService operLogService;

//    @PreAuthorize("hasAuthority('monitor:operlog:list')")
    @GetMapping("/list")
    public CommonResult<CommonPage<SysOperLog>> list(SysOperLog operLog)
    {
        startPage();
        List<SysOperLog> list = operLogService.selectOperLogList(operLog);
        return CommonResult.success(CommonPage.restPage(list));
    }
}
