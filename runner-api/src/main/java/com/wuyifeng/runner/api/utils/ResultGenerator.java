package com.wuyifeng.runner.api.utils;

import com.wuyifeng.runner.api.vo.Result;

/**
 * 响应结果生成工具
 *
 * @author 李忠
 *
 */
public class ResultGenerator {

    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static <T> Result<T> ok() {
        return new Result<T>().setStatus(Result.RESULT_CODE_SUCCESS).setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> Result<T> ok(T data) {
        return new Result<T>().setStatus(Result.RESULT_CODE_SUCCESS).setData(data).setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> Result<T> ok(T data, String message) {
        return new Result<T>().setStatus(Result.RESULT_CODE_SUCCESS).setData(data).setMessage(message);
    }

    public static Result<String> fail() {
        return new Result<String>().setStatus(Result.RESULT_CODE_INTERNAL_SERVER_ERROR).setMessage("未知错误，请联系系统管理员").setData("");
    }


    public static Result<String> fail(String message,String data) {
        return new Result<String>().setStatus(Result.RESULT_CODE_INTERNAL_SERVER_ERROR).setMessage(message).setData(data);
    }

    public static Result<String> fail(int code, String message,String data) {
        return new Result<String>().setStatus(code).setMessage(message).setData(data);
    }

    public static Result<String> fail404(String requestURI){
        return new Result<String>().setStatus(Result.RESULT_CODE_NOT_FOUND).setMessage("未存在请求资源").setData("请求路径"+ requestURI);
    }

    public static Result<String> fail500(String exceptionMsg) {
        return new Result<String>().setStatus(Result.RESULT_CODE_INTERNAL_SERVER_ERROR).setMessage("未知错误，请联系系统管理员").setData("错误信息：" + exceptionMsg);
    }

    public static Result<String> unauthorized (String exceptionMsg) {
        return new Result<String>().setStatus(Result.RESULT_CODE_UNAUTHORIZED).setMessage("认证失败").setData("错误信息：" + exceptionMsg);
    }
}
