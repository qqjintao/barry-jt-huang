package com.example.service;

import com.example.dto.SysMenuDTO;
import com.example.model.SysMenu;
import com.example.model.SysUser;
import com.example.vo.RouterVo;
import com.example.vo.TreeSelect;

import java.util.List;
import java.util.Set;

/**
 * @author barry.jt.huang
 */
public interface ISysMenuService {
    /**
     * 获取菜单数据权限
     *
     * @return 菜单权限信息
     */
    Set<String> getMenuPermission(Long id);

    /**
     * 根据用户ID查询菜单树信息
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysMenuDTO> selectMenuTreeByUserId(Long userId);

    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    List<RouterVo> buildMenus(List<SysMenuDTO> menus);

    /**
     * 根据用户查询系统菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    List<SysMenu> selectMenuList(SysMenu menu);

    /**
     * 根据菜单ID查询信息
     *
     * @param id 菜单ID
     * @return 菜单信息
     */
    SysMenu selectMenuById(Long id);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param menus 菜单列表
     * @return 下拉树结构列表
     */
    List<TreeSelect> buildMenuTreeSelect(List<SysMenu> menus);

    /**
     * 删除菜单管理信息
     *
     * @param id 菜单ID
     * @return 结果
     */
    int deleteMenuById(Long id);

    /**
     * 新增保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    int insertMenu(SysMenu menu);

    /**
     * 修改保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    int updateMenu(SysMenu menu);

    /**
     * 是否存在菜单子节点
     *
     * @param id 菜单ID
     * @return 结果 true 存在 false 不存在
     */
    boolean hasChildByMenuId(Long id);

    /**
     * 查询菜单是否存在角色
     *
     * @param id 菜单ID
     * @return 结果 true 存在 false 不存在
     */
    boolean checkMenuExistRole(Long id);

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    List<Integer> selectMenuListByRoleId(Long roleId);
}