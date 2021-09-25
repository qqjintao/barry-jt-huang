package com.example.exception;

import com.example.BaseException;

/**
 * @Author: barry.jt.huang
 * @Date: 2020/11/14 0014
 */
public class CaptchaException extends BaseException {
    private static final long serialVersionUID = 4776443320510226978L;

    public CaptchaException() {
        super("captcha", "user.jcaptcha.error", null);
    }
}