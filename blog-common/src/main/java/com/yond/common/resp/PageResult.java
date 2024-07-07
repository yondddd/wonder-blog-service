package com.yond.common.resp;

import java.io.Serializable;

/**
 * @Author Administrator
 * @Description 分页统一响应
 * @Date 2023/1/20
 */
public class PageResult<T> implements Serializable {

    /**
     * 序列化uid
     */
    private static final long serialVersionUID = 586262417756505439L;

    /**
     * request id
     */
    private Long queryId;

    /**
     * 返回值状态码
     * {@link Response#SUCCESS}
     * {@link Response#FAILURE}
     */
    private int code;

    /**
     * 返回结果描述信息
     */
    private String message;

    /**
     * 返回结果数据
     */
    private T data;

    /**
     * 总数量
     */
    private long total;

    /**
     * 每页的大小
     */
    private int pageSize;

    /**
     * 页码的偏移量
     */
    private int pageNo;

    /**
     * 是否存在下一页
     */
    private boolean hasNext;

    private PageResult() {
        //
    }

    public PageResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> PageResult<T> custom() {
        return new PageResult<>();
    }

    public int getCode() {
        return this.code;
    }

    public PageResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public PageResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return this.data;
    }

    public PageResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public long getTotal() {
        return total;
    }

    public PageResult<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public PageResult<T> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getPageNo() {
        return pageNo;
    }

    public PageResult<T> setPageNo(int pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public boolean isSuccess() {
        return this.code == Response.SUCCESS;
    }

    public PageResult<T> setSuccess() {
        code = Response.SUCCESS;
        return this;
    }

    public PageResult<T> setFailure(final String msg) {
        code = Response.FAILURE;
        this.message = msg;
        return this;
    }

    public PageResult<T> setException(final String msg) {
        code = Response.EXCEPTION;
        this.message = msg;
        return this;
    }

    public Long getQueryId() {
        return queryId;
    }

    public PageResult<T> setQueryId(Long queryId) {
        this.queryId = queryId;
        return this;
    }

    public boolean getHasNext() {
        return hasNext;
    }

    public PageResult<T> setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
        return this;
    }

    public PageResult<T> failure(String msg) {
        this.code = Response.FAILURE;
        this.message = msg;
        return this;
    }

    public PageResult<T> failure() {
        this.code = Response.FAILURE;
        this.message = "server error";
        return this;
    }

    public PageResult<T> build() {
        return this;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

