package com.knowledge_network.auth.security.ka;

/**
 * Created by pentonbin on 18-4-3
 * <p>
 * 密钥协商的密钥计算
 */
public interface KeyAgreementCalculator {

    /**
     * 根据公钥和私钥计算共享密钥
     *
     * @param publicKey
     * @param privateKey
     * @return
     */
    byte[] calculateSharedSecret(byte[] publicKey, byte[] privateKey);

}
