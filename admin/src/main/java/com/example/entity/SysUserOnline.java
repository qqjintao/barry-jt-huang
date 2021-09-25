package com.example.entity;

import lombok.Data;

/**
 * @Author: barry.jt.huang
 * @Date: 2020/11/14 0014
 */
@Data
public class SysUserOnline {
    /**
     * 会话编号
     */
    private String tokenId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地址
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 准备登出时间
     */
    private Long expireTime;
}

