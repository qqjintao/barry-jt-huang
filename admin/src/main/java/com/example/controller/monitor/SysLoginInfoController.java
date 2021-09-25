package com.example.controller.monitor;

import com.example.CommonPage;
import com.example.CommonResult;
import com.example.controller.BaseController;
import com.example.model.SysLoginInfo;
import com.example.service.ISysLoginInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author: barry.jt.huang
 * @Date: 2020/11/14 0014
 */
@RestController
@RequestMapping("/monitor/loginInfo")
public class SysLoginInfoController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(SysLoginInfoController.class);

    @Autowired
    private ISysLoginInfoService loginInfoService;

//    @PreAuthorize("hasAuthority('monitor:loginInfo:list')")
    @GetMapping("/list")
    public CommonResult<CommonPage<SysLoginInfo>> list(SysLoginInfo loginInfo, Date beginTime, Date endTime) {
        startPage();
        List<SysLoginInfo> list = loginInfoService.selectLoginInfoList(loginInfo,beginTime,endTime);
        return CommonResult.success(CommonPage.restPage(list));
    }
    
//    @PreAuthorize("hasAuthority('monitor:loginInfo:export')")
    @GetMapping("/export")
    public CommonResult export(SysLoginInfo loginInfo) {
        return null;
    }

//    @PreAuthorize("hasAuthority('monitor:loginInfo:remove')")
    @DeleteMapping("/{infoIds}")
    public CommonResult remove(@PathVariable Long[] infoIds) {
        return CommonResult.success(loginInfoService.deleteLoginInfoByIds(infoIds));
    }

//    @PreAuthorize("hasAuthority('monitor:loginInfo:remove')")
    @DeleteMapping("/clean")
    public CommonResult clean() {
        loginInfoService.cleanLoginInfo();
        return CommonResult.success(null);
    }
}
