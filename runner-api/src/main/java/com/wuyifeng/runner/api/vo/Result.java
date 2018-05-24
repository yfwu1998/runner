package com.wuyifeng.runner.api.vo;

import java.io.Serializable;


/**
 * 统一API响应结果封装
 *
 * @author 吴贻锋
 *
 * @param <T>
 *            数据对象
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 870435907445893868L;

    public static final int RESULT_CODE_SUCCESS = 200;// 成功
    public static final int RESULT_CODE_FAIL = 400;// 失败
    public static final int RESULT_CODE_UNAUTHORIZED = 401;// 未认证（签名错误）
    public static final int RESULT_CODE_NOT_FOUND = 404;// 接口不存在
    public static final int RESULT_CODE_INTERNAL_SERVER_ERROR = 500;// 服务器内部错误

    private String message;
    private T data;
    private Integer status;


    public String getMessage() {
        return message;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public Result<T> setStatus(int status) {
        this.status = status;
        return this;
    }

}

