package com.example.exception;

import com.example.BaseException;

/**
 * @Author: barry.jt.huang
 * @Date: 2020/11/14 0014
 */
public class UserPasswordNotMatchException extends BaseException {
    private static final long serialVersionUID = -8475740060754173667L;

    public UserPasswordNotMatchException() {
        super("password", "user.password.not.match", null);
    }
}