package com.example.controller.system;

import com.example.CommonPage;
import com.example.CommonResult;
import com.example.controller.BaseController;
import com.example.dto.SysRoleDTO;
import com.example.model.LoginUser;
import com.example.model.SysMenu;
import com.example.model.SysRole;
import com.example.service.ISysMenuService;
import com.example.service.ISysRoleService;
import com.example.untils.ServletUtils;
import com.example.util.SecurityUtils;
import com.example.util.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: barry.jt.huang
 * @Date: 2020/12/7 0007
 */
@RestController
@RequestMapping("/system/role")
public class SysRoleController extends BaseController {
    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ISysMenuService permissionService;

    @GetMapping("/list")
    public CommonResult<CommonPage<SysRole>> list(SysRole role, Date beginTime, Date endTime) {
        startPage();
        List<SysRole> list = roleService.selectRoleList(role, beginTime, endTime);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 根据角色编号获取详细信息
     */
    @GetMapping(value = "/{roleId}")
    public CommonResult getInfo(@PathVariable Long roleId) {
        return CommonResult.success(roleService.selectRoleById(roleId));
    }

    /**
     * 新增角色
     */
    @PostMapping
    public CommonResult add(@RequestBody SysRoleDTO dto) {
        return CommonResult.toAjax(roleService.insertRole(dto.changeSysRole(), dto.getMenuIds()));
    }

    /**
     * 修改保存角色
     */
    @PutMapping
    public CommonResult edit(@RequestBody SysRoleDTO dto) {
        if (roleService.updateRole(dto.changeSysRole(), dto.getMenuIds()) > 0) {
            // 更新缓存用户权限
            LoginUser loginUser = SecurityUtils.getLoginUser();
            loginUser.setPermissions(permissionService.getMenuPermission(SecurityUtils.getUserId()));
            tokenService.setLoginUser(ServletUtils.getRequest(), loginUser);
            return CommonResult.success();
        }
        return CommonResult.failed("修改角色失败，请联系管理员");
    }

    /**
     * 状态修改
     */
    @PutMapping("/changeStatus")
    public CommonResult changeStatus(@RequestBody SysRole role) {
        return CommonResult.toAjax(roleService.updateRoleStatus(role));
    }

    /**
     * 删除角色
     */
    @DeleteMapping("/{roleIds}")
    public CommonResult remove(@PathVariable Long[] roleIds) {
        return CommonResult.toAjax(roleService.deleteRoleByIds(roleIds));
    }

    /**
     * 获取角色选择框列表
     */
    @GetMapping("/optionselect")
    public CommonResult optionselect() {
        return CommonResult.success(roleService.selectRoleList(new SysRole(), null, null));
    }
}

