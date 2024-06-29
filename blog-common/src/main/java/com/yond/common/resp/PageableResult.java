package com.yond.common.resp;

import java.io.Serializable;

/**
 * @Author Administrator
 * @Description 分页统一响应
 * @Date 2023/1/20
 */
public class PageableResult<T> implements Serializable {

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
     * {@link Result#SUCCESS}
     * {@link Result#FAILURE}
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

    private PageableResult() {
        //
    }

    public PageableResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> PageableResult<T> custom() {
        return new PageableResult<>();
    }

    public int getCode() {
        return this.code;
    }

    public PageableResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public PageableResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return this.data;
    }

    public PageableResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public long getTotal() {
        return total;
    }

    public PageableResult<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public PageableResult<T> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getPageNo() {
        return pageNo;
    }

    public PageableResult<T> setPageNo(int pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public boolean isSuccess() {
        return this.code == Result.SUCCESS;
    }

    public PageableResult<T> setSuccess() {
        code = Result.SUCCESS;
        return this;
    }

    public PageableResult<T> setFailure(final String msg) {
        code = Result.FAILURE;
        this.message = msg;
        return this;
    }

    public PageableResult<T> setException(final String msg) {
        code = Result.EXCEPTION;
        this.message = msg;
        return this;
    }

    public Long getQueryId() {
        return queryId;
    }

    public PageableResult<T> setQueryId(Long queryId) {
        this.queryId = queryId;
        return this;
    }

    public boolean getHasNext() {
        return hasNext;
    }

    public PageableResult<T> setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
        return this;
    }

    public PageableResult<T> failure(String msg) {
        this.code = Result.FAILURE;
        this.message = msg;
        return this;
    }

    public PageableResult<T> failure() {
        this.code = Result.FAILURE;
        this.message = "server error";
        return this;
    }

    public PageableResult<T> build() {
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

