package com.knowledge_network.support.exceptions;

/**
 * Created by pentonbin on 18-3-29
 * <p>
 * OAuth2.0 Scope操作权限不足异常
 */
public class OAuth2ScopeException extends RuntimeException {

    public OAuth2ScopeException(String scope) {
        super("Client scope not permitted:" + scope);
    }
}
