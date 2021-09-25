package com.example.controller.monitor;

import com.example.CommonResult;
import com.example.controller.BaseController;

import com.example.web.Server;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: barry.jt.huang
 * @Date: 2020/11/14 0014
 */
@RestController
@RequestMapping("/monitor/server")
public class ServerController extends BaseController
{
    @PreAuthorize("hasAuthority('monitor:server:list')")
    @GetMapping()
    public CommonResult getInfo() throws Exception
    {
        Server server = new Server();
        server.copyTo();
        return CommonResult.success(server);
    }
}