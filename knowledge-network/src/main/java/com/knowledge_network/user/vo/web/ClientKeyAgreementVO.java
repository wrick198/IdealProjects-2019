package com.knowledge_network.user.vo.web;

/**
 * Created by pentonbin on 18-4-3
 * <p>
 * 密钥协商：客户端发送的数据格式
 */
public class ClientKeyAgreementVO {

    private String clientPublicKey;
    private String clientId;

    public ClientKeyAgreementVO() {
    }

    public String getClientPublicKey() {
        return clientPublicKey;
    }

    public void setClientPublicKey(String clientPublicKey) {
        this.clientPublicKey = clientPublicKey;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
