package com.example.exception;

import com.example.BaseException;

/**
 * @Author: barry.jt.huang
 * @Date: 2020/11/14 0014
 */
public class CaptchaExpireException extends BaseException {
    private static final long serialVersionUID = -3612226082325250687L;

    public CaptchaExpireException() {
        super("captcha", "user.jcaptcha.expire", null);
    }
}
