package com.yond.common.resp;


import java.io.Serializable;

/**
 * @Description: 封装响应结果
 * @Author: Naccl
 * @Date: 2020-07-19
 */

public class Response<T> implements Serializable {

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

    private Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    private Response(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Response ok(String msg, Object data) {
        return new Response(SUCCESS, msg, data);
    }

    public static Response ok(String msg) {
        return new Response(SUCCESS, msg);
    }

    public static Response failure(String msg) {
        return new Response(FAILURE, msg);
    }

    public static Response create(Integer code, String msg) {
        return new Response(code, msg);
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

    public Response() {
        //
    }

    public boolean isSuccess() {
        return this.code == SUCCESS;
    }

    public static <T> Response<T> custom(int code, String msg) {
        return new Response<>(code, msg);
    }

    public static <T> Response<T> custom() {
        return new Response<>();
    }

    public static Response<Boolean> success() {
        return Response
                .<Boolean>custom()
                .setSuccess()
                .setData(Boolean.TRUE);
    }

    public static <T> Response<T> success(T data) {
        final Response<T> Result =
                Response
                        .<T>custom()
                        .setSuccess()
                        .setData(data);
        return Result;
    }

    public static <T> Response<T> fail(String msg) {
        return Response
                .<T>custom()
                .setFailure(msg);
    }

    public Response<T> setSuccess() {
        code = SUCCESS;
        this.msg = "success";
        return this;
    }

    public Response<T> setFailure(final String msg) {
        code = FAILURE;
        this.msg = msg;
        return this;
    }

    public Response<T> setData(T data) {
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
