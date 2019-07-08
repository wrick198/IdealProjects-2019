package com.knowledge_network.auth.security.ka;

/**
 * Created by pentonbin on 18-4-3
 * <p>
 * 密钥协商的的密钥对生成器
 */
public interface KeyAgreementGenerator {

    /**
     * 获得公钥
     *
     * @return
     */
    byte[] getPublicKey();

    /**
     * 获得私钥
     *
     * @return
     */
    byte[] getPrivateKey();
}
