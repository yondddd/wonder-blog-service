package com.yond.resp;


import java.io.Serializable;

/**
 * @Description: 封装响应结果
 * @Author: Naccl
 * @Date: 2020-07-19
 */

public class Result<T> implements Serializable {

    private static final long serialVersionUID = 5000194819741415657L;

    /**
     * 成功状态码
     */
    public static final int SUCCESS = 200;

    /**
     * 服务端内部异常状态码
     */
    public static final int FAILURE = 500;
    /**
     * 非法参数
     */
    public static final int ILLEGAL_PARAMETER = 501;

    /**
     * 客户端请求有语法错误，不能被服务器所理解
     */
    public static final int EXCEPTION = 400;

    /**
     * 未授权
     */
    public static final int UNAUTHORIZED = 401;

    private int code;
    private String msg;
    private T data;

    private Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result ok(String msg, Object data) {
        return new Result(SUCCESS, msg, data);
    }

    public static Result ok(String msg) {
        return new Result(SUCCESS, msg);
    }

    public static Result failure(String msg) {
        return new Result(FAILURE, msg);
    }

    public static Result create(Integer code, String msg) {
        return new Result(code, msg);
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public Result() {
        //
    }

    public boolean isSuccess() {
        return this.code == SUCCESS;
    }

    public static <T> Result<T> custom() {
        return new Result<>();
    }

    public static Result<Boolean> success() {
        final Result<Boolean> remoteResponse =
                Result
                        .<Boolean>custom()
                        .setSuccess()
                        .setData(Boolean.TRUE);
        return remoteResponse;
    }

    public static <T> Result<T> success(T data) {
        final Result<T> Result =
                com.yond.resp.Result
                        .<T>custom()
                        .setSuccess()
                        .setData(data);
        return Result;
    }

    public Result<T> setSuccess() {
        code = SUCCESS;
        this.msg = "success";
        return this;
    }

    public Result<T> setFailure(final String msg) {
        code = FAILURE;
        this.msg = msg;
        return this;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }


    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
