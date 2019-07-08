package com.knowledge_network.support.common;

/**
 * Created by pentonbin on 17-12-14
 * <p>
 * web请求的响应结果
 */
public class ResponseResult<T> {

    /**
     * 请求是否成功
     */
    private boolean succeed;
    /**
     * 请求失败的错误码
     */
    private int errorCode;
    /**
     * 回复信息：
     * 1. 请求成功：成功的通知信息
     * 2. 请求失败：失败的通知信息
     */
    private String message;
    /**
     * 请求返回的数据对象（VO）
     */
    private T data;

    private ResponseResult(boolean succeed, int errorCode, String message, T data) {
        this.succeed = succeed;
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseResult<T> newSucceedInstance(String message, T data) {
        return new ResponseResult<>(true, -1, message, data);
    }

    public static <T> ResponseResult<T> newErrorInstance(ResponseErrorEnum error, T data) {
        return new ResponseResult<>(false, error.getErrorCode(), error.getErrorMessage(), data);
    }

    public boolean isSucceed() {
        return succeed;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
