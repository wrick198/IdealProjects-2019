package com.knowledge_network.mobile.request;

import com.knowledge_network.support.utils.JsonMapper;

import java.io.IOException;

/**
 * Created by pentonbin on 17-12-6.
 * <p>
 * 服务端返回给客户端的Response基类
 */
public class MobileServerResponse<T> {

    public static final int OK = 200;
    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int SERVER_ERROR = 500;

    public static final MobileServerResponse<String> OK_RESPONSE
            = new MobileServerResponse<>(OK, "OK", null);
    public static final MobileServerResponse<String> BAD_REQUEST_RESPONSE
            = new MobileServerResponse<>(BAD_REQUEST, null, "Bad request");
    public static final MobileServerResponse<String> UNAUTHORIZED_RESPONSE
            = new MobileServerResponse<>(UNAUTHORIZED, null, "Unauthorized");
    public static final MobileServerResponse<String> FORBIDDEN_RESPONSE
            = new MobileServerResponse<>(FORBIDDEN, null, "Forbidden");
    public static final MobileServerResponse<String> SERVER_ERROR_RESPONSE
            = new MobileServerResponse<>(SERVER_ERROR, null, "Server error");

    private int responseCode;
    private String entity;
    private String errorMsg;

    public MobileServerResponse(int responseCode, T entity, String errorMsg) {
        this.responseCode = responseCode;
        this.entity = JsonMapper.obj2Json(entity);
        this.errorMsg = errorMsg;
    }

    public T getBody(Class<T> entityClass) throws IOException {
        return JsonMapper.json2Obj(entity, entityClass);
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getEntity() {
        return entity;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
