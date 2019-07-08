package com.knowledge_network.user.vo.web;

/**
 * Created by pentonbin on 18-4-3
 * <p>
 * 密钥协商：服务端回复的数据格式
 */
public class ServerKeyAgreementVO {

    private String serverPublicKey;
    private String signature;

    public ServerKeyAgreementVO() {
    }

    public String getServerPublicKey() {
        return serverPublicKey;
    }

    public void setServerPublicKey(String serverPublicKey) {
        this.serverPublicKey = serverPublicKey;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

}
