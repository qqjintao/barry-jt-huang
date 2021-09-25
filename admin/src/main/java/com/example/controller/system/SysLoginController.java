package com.example.controller.system;

import com.example.CommonResult;
import com.example.constant.Constants;
import com.example.dto.LoginDTO;
import com.example.dto.SysMenuDTO;
import com.example.model.LoginUser;
import com.example.model.SysMenu;
import com.example.model.SysUser;
import com.example.service.ISysLoginService;
import com.example.service.ISysMenuService;
import com.example.untils.ServletUtils;
import com.example.util.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: barry.jt.huang
 * @Date: 2020/11/14 0014
 */
@RestController
public class SysLoginController {
    @Autowired
    private ISysLoginService loginService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ISysMenuService menuService;

    /**
     * 登录方法
     *
     * @return 结果
     */
    @PostMapping("/login")
    public CommonResult login(@RequestBody LoginDTO login) {
        // 生成令牌
        String token = loginService.login(login.getUserName(), login.getPassword(), login.getCode(), login.getUuid());
        HashMap date = new HashMap(1);
        date.put(Constants.TOKEN, token);
        return CommonResult.success(date);
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public CommonResult getInfo() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginService.selectUserById(loginUser.getId());
        // 权限集合
        Set<String> permissions = loginUser.getPermissions();
        HashMap date = new HashMap(2);
        date.put("user", user);
        date.put("permissions", permissions);
        return CommonResult.success(date);
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public CommonResult getRouters() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<SysMenuDTO> menus = menuService.selectMenuTreeByUserId(loginUser.getId());
        return CommonResult.success(menuService.buildMenus(menus));
    }
}

