package com.knowledge_network.user.service;

/**
 * Created by pentonbin on 18-3-29
 */
public interface OAuth2Service {

    /**
     * 检查clientId的客户端是否具有相应的操作权限scope
     *
     * @param clientId
     * @param scope
     */
    void validateScope(String clientId, String scope);

    /**
     * 检查系统是否支持scope的操作权限
     *
     * @param scope
     */
    void checkScope(String scope);
}
