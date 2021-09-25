package com.example.controller.system;

import com.example.CommonPage;
import com.example.CommonResult;
import com.example.constant.Constants;
import com.example.controller.BaseController;
import com.example.controller.config.SysConfig;
import com.example.dto.SysUserDTO;
import com.example.model.LoginUser;
import com.example.model.SysRole;
import com.example.model.SysUser;
import com.example.service.ISysRoleService;
import com.example.service.ISysUserService;
import com.example.untils.ServletUtils;
import com.example.untils.StringUtils;
import com.example.untils.bean.BeanUtils;
import com.example.untils.file.FileUploadUtils;
import com.example.util.SecurityUtils;
import com.example.util.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author barry.jt.huang
 */
@RestController
@Api(tags = "SysUserController", description = "系统用户管理")
@RequestMapping("/system/user")
public class SysUserController extends BaseController {
    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private TokenService tokenService;

    /**
     * 新增用户
     */
    @ApiOperation("新增用户")
    @PostMapping("/register")
    public CommonResult register(@RequestBody SysUser user) {
        if (Constants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName()))) {
            return CommonResult.failed("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        } else if (Constants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user.getPhone(), user.getId()))) {
            return CommonResult.failed("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (Constants.NOT_UNIQUE.equals(userService.checkEmailUnique(user.getEmail(), user.getId()))) {
            return CommonResult.failed("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        return CommonResult.toAjax(userService.insertUser(user, null, null, null));
    }

    /**
     * 获取用户列表
     */
    @ApiOperation(value = "获取用户列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<SysUser>> list(SysUser user, Date beginTime, Date endTime, Long deptId) {
        startPage();
        List<SysUser> list = userService.selectUserList(user, beginTime, endTime, deptId);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 根据用户编号获取详细信息
     */
    @GetMapping(value = {"/", "/{id}"})
    public CommonResult getInfo(@PathVariable(value = "id", required = false) Long id) {
        Map data = new HashMap(5);
        List<SysRole> roles = roleService.selectRoleList(new SysRole(), null, null);
        data.put("roles", roles);
        if (StringUtils.isNotNull(id)) {
            data.put("list", userService.selectUserById(id));
            data.put("roleIds", userService.selectRoleListByUserId(id));
            data.put("postIds", userService.selectPostListByUserId(id));
            data.put("deptIds", userService.selectDeptListByUserId(id));
        }
        return CommonResult.success(data);
    }

    /**
     * 新增用户
     */
    @PostMapping
    public CommonResult add(@RequestBody SysUserDTO dto) {
        SysUser user = new SysUser();
        BeanUtils.copyBeanProp(user, dto);
        if (Constants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName()))) {
            return CommonResult.failed("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        } else if (Constants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user.getPhone(), user.getId()))) {
            return CommonResult.failed("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (Constants.NOT_UNIQUE.equals(userService.checkEmailUnique(user.getEmail(), user.getId()))) {
            return CommonResult.failed("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        return CommonResult.toAjax(userService.insertUser(user, dto.getDeptIds(), dto.getPostIds(), dto.getRoleIds()));
    }

    /**
     * 修改用户
     */
    @PutMapping
    public CommonResult edit(@RequestBody SysUserDTO dto) {
        if (Constants.NOT_UNIQUE.equals(userService.checkPhoneUnique(dto.getPhone(), dto.getId()))) {
            return CommonResult.failed("新增用户'" + dto.getUserName() + "'失败，手机号码已存在");
        } else if (Constants.NOT_UNIQUE.equals(userService.checkEmailUnique(dto.getEmail(), dto.getId()))) {
            return CommonResult.failed("新增用户'" + dto.getUserName() + "'失败，邮箱账号已存在");
        }
        return CommonResult.toAjax(userService.updateUser(dto, dto.getDeptIds(), dto.getPostIds(), dto.getRoleIds()));
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{ids}")
    public CommonResult remove(@PathVariable Long[] ids) {
        return CommonResult.toAjax(userService.deleteUserByIds(ids));
    }

    /**
     * 重置密码
     */
    @PutMapping("/resetPwd")
    public CommonResult resetPwd(@RequestBody SysUser user) {
        return CommonResult.toAjax(userService.resetPwd(user));
    }

    /**
     * 状态修改
     */
    @PutMapping("/changeStatus")
    public CommonResult changeStatus(@RequestBody SysUser user) {
        return CommonResult.toAjax(userService.updateUserStatus(user));
    }


    /**
     * 个人信息
     */
    @GetMapping("/profile")
    public CommonResult profile() {
        SysUser user = userService.selectUserById(SecurityUtils.getUserId());
        Map data = new HashMap(5);
        data.put("user", user);
        data.put("roleGroup", userService.selectRoleNameListByUserId(SecurityUtils.getUserId()).toArray());
        data.put("postGroup", userService.selectPostNameListByUserId(SecurityUtils.getUserId()).toArray());
        data.put("deptGroup", userService.selectDeptNameListByUserId(SecurityUtils.getUserId()).toArray());
        return CommonResult.success(data);
    }

    /**
     * 修改用户
     */
    @PutMapping("/profile")
    public CommonResult updateProfile(@RequestBody SysUser user) {
        if (userService.updateUserStatus(user) > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed("修改个人信息异常，请联系管理员");
    }

    /**
     * 重置密码
     */
    @PutMapping("/profile/updatePwd")
    public CommonResult updatePwd(String oldPassword, String newPassword) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        String password = loginUser.getPassword();
        SysUser user = new SysUser();
        user.setId(loginUser.getId());
        user.setPassword(newPassword);
        if (!SecurityUtils.matchesPassword(oldPassword, password)) {
            return CommonResult.failed("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(newPassword, password)) {
            return CommonResult.failed("新密码不能与旧密码相同");
        }
        if (userService.resetPwd(user) > 0) {
            // 更新缓存用户密码
            loginUser.setPassword(SecurityUtils.encryptPassword(newPassword));
            tokenService.setLoginUser(ServletUtils.getRequest(), loginUser);
            return CommonResult.success();
        }
        return CommonResult.failed("修改密码异常，请联系管理员");
    }

    /**
     * 头像上传
     */
    @PostMapping("/profile/avatar")
    public CommonResult avatar(@RequestParam("avatarfile") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            FileUploadUtils.setDefaultBaseDir(SysConfig.getProfile());
            String avatar = FileUploadUtils.upload(SysConfig.getAvatarPath(), file);
            if (userService.updateUserAvatar(avatar)) {
                return CommonResult.success(avatar);
            }
        }
        return CommonResult.failed("上传图片异常，请联系管理员");
    }
}