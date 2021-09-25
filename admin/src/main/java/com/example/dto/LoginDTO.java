package com.example.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author barry.jt.huang
 */
@Data
public class LoginDTO implements Serializable {
    private static final long serialVersionUID = -3577576387514449011L;
    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid = "";
}
