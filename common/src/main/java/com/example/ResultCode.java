package com.example;

/**
 * 枚举了一些常用API操作码
 * @author barry.jt.huang
 */
public enum ResultCode{
    SUCCESS(200, "操作成功"),
    CREATED(201,"对象创建成功"),
    ACCEPTED(202,"请求已经被接受"),
    NO_CONTENT(204,"操作已经执行成功，但是没有返回数据"),
    MOVED_PERM(301,"资源已被移除"),
    SEE_OTHER(303,"重定向"),
    NOT_MODIFIED(304,"资源没有被修改"),
    BAD_REQUEST(400,"参数列表错误（缺少，格式不匹配）"),
    NOT_FOUND(404,"资源，服务未找到"),
    BAD_METHOD(405, "不允许的http方法"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    CONFLICT(409,"资源冲突，或者资源被锁"),
    UNSUPPORTED_TYPE(415,"不支持的数据，媒体类型"),
    ERROR(500,"系统内部错误"),
    NOT_IMPLEMENTED(501,"接口未实现");
    private long code;
    private String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }


    public long getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }
}
