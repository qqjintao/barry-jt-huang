package com.example.dao;

import com.example.dto.SysMenuDTO;
import com.example.model.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: barry.jt.huang
 * @Date: 2020/11/20 0020
 */
public interface SysMenuDao {
    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    List<String> selectMenuPermsByUserId(Long userId);

    /**
     * 根据用户ID查询菜单
     *
     * @return 菜单列表
     */
    List<SysMenuDTO> selectMenuTreeAll();

    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysMenuDTO> selectMenuTreeByUserId(Long userId);


    /**
     * 根据用户查询系统菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    List<SysMenu> selectMenuListByUserId(@Param("menu")SysMenu menu,@Param("userId")Long userId);

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
     List<Integer> selectMenuListByRoleId(@Param("roleId") Long roleId);
}
