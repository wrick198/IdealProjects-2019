package com.knowledge_network.auth.security.ds;

/**
 * Created by pentonbin on 18-4-3
 * <p>
 * 数字签名的密钥对生成
 */
public interface DigitalSignatureGenerator {

    /**
     * 生成公钥
     *
     * @return
     */
    byte[] getVerifyKey();

    /**
     * 生成密钥
     *
     * @return
     */
    byte[] getSignKey();
}
