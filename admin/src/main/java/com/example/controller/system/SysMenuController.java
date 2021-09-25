package com.example.controller.system;

import com.example.CommonResult;
import com.example.constant.Constants;
import com.example.controller.BaseController;
import com.example.model.SysMenu;
import com.example.service.ISysMenuService;
import com.example.untils.StringUtils;
import com.example.util.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author barry.jt.huang
 */
@RestController
@RequestMapping("/system/menu")
public class SysMenuController extends BaseController {
    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private TokenService tokenService;

    /**
     * 获取菜单列表
     */
    @GetMapping("/list")
    public CommonResult list(SysMenu menu) {
        List<SysMenu> menus = menuService.selectMenuList(menu);
        return CommonResult.success(menus);
    }

    /**
     * 根据菜单编号获取详细信息
     */
    @GetMapping(value = "/{id}")
    public CommonResult getInfo(@PathVariable Long id) {
        return CommonResult.success(menuService.selectMenuById(id));
    }

    /**
     * 获取菜单下拉树列表
     */
    @GetMapping("/treeselect")
    public CommonResult treeselect(SysMenu menu) {
        List<SysMenu> menus = menuService.selectMenuList(menu);
        return CommonResult.success(menuService.buildMenuTreeSelect(menus));
    }

    /**
     * 新增菜单
     */
    @PostMapping
    public CommonResult add(@Validated @RequestBody SysMenu menu) {
        if (Constants.YES_FRAME.equals(menu.getIsFrame().toString())
                && !StringUtils.startsWithAny(menu.getPath(), Constants.HTTP, Constants.HTTPS)) {
            return CommonResult.failed("新增菜单'" + menu.getMenuNameZh() + "'失败，地址必须以http(s)://开头");
        }
        return CommonResult.toAjax(menuService.insertMenu(menu));
    }

    /**
     * 修改菜单
     */
    @PutMapping
    public CommonResult edit(@Validated @RequestBody SysMenu menu) {
        if (Constants.YES_FRAME.equals(menu.getIsFrame().toString())
                && !StringUtils.startsWithAny(menu.getPath(), Constants.HTTP, Constants.HTTPS)) {
            return CommonResult.failed("修改菜单'" + menu.getMenuNameZh() + "'失败，地址必须以http(s)://开头");
        } else if (menu.getId().equals(menu.getParentId())) {
            return CommonResult.failed("修改菜单'" + menu.getMenuNameZh() + "'失败，上级菜单不能选择自己");
        }
        return CommonResult.toAjax(menuService.updateMenu(menu));
    }

    /**
     * 删除菜单
     */
    @DeleteMapping("/{id}")
    public CommonResult remove(@PathVariable("id") Long id) {
        if (menuService.hasChildByMenuId(id)) {
            return CommonResult.failed("存在子菜单,不允许删除");
        }
        if (menuService.checkMenuExistRole(id)) {
            return CommonResult.failed("菜单已分配,不允许删除");
        }
        return CommonResult.toAjax(menuService.deleteMenuById(id));
    }

    /**
     * 加载对应角色菜单列表树
     */
    @GetMapping(value = "/roleMenuTreeselect/{roleId}")
    public CommonResult roleMenuTreeselect(@PathVariable("roleId") Long roleId)
    {
        List<SysMenu> menus = menuService.selectMenuList(new SysMenu());
        Map data = new HashMap(2);
        data.put("checkedKeys", menuService.selectMenuListByRoleId(roleId));
        data.put("menus", menuService.buildMenuTreeSelect(menus));
        return CommonResult.success(data);
    }
}