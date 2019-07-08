package com.knowledge_network.auth.security.se;

/**
 * Created by pentonbin on 18-4-3
 * <p>
 * 对称加密接口
 */
public interface SymmetricEncryptProcessor {

    /**
     * 加密方法
     *
     * @param content 明文的字节数组
     * @return
     */
    byte[] encrypt(byte[] content);

    /**
     * 解密方法
     *
     * @param cipher 密文的字节数组
     * @return
     */
    byte[] decrypt(byte[] cipher);
}
