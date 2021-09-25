package com.example.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: barry.jt.huang
 * @Date: 2020/11/28 0028
 */
@Data
public class SysMenuDTO implements Serializable {
    private static final long serialVersionUID = -3085604046074792331L;
    /** 菜单ID */
    private Long id;

    /** 菜单名称 */
    private String menuNameZh;

    /** 菜单名称 */
    private String menuNameEh;

    /** 父菜单名称 */
    private String parentName;

    /** 父菜单ID */
    private Long parentId;

    /** 显示顺序 */
    private String orderNum;

    /** 路由地址 */
    private String path;

    /** 组件路径 */
    private String component;

    /** 是否为外链（0是 1否） */
    private String isFrame;

    /** 是否缓存（0缓存 1不缓存） */
    private String isCache;

    /** 类型（M目录 C菜单 F按钮） */
    private String menuType;

    /** 显示状态（0显示 1隐藏） */
    private String visible;

    /** 菜单状态（0显示 1隐藏） */
    private String status;

    /** 权限字符串 */
    private String perms;

    /** 菜单图标 */
    private String icon;

    /** 子菜单 */
    private List<SysMenuDTO> children = new ArrayList<SysMenuDTO>();
}
