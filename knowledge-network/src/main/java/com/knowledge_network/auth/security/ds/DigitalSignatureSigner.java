package com.knowledge_network.auth.security.ds;

/**
 * Created by pentonbin on 18-4-3
 */
public interface DigitalSignatureSigner {


    /**
     * 根据私钥对内容进行签名
     *
     * @param privateKey 私钥
     * @param message    内容
     * @return 数字签名
     */
    byte[] calculateSignature(byte[] privateKey, byte[] message) throws RuntimeException;

    /**
     * 验证数字签名
     * <p>
     * 该方法在服务端不！会！被用到：
     * 1. 服务端不需要对客户端进行数字签名认证，因为中间人攻击只需要一方进行认证即可；
     * 2. 使用私钥签名，公钥进行认证；
     *
     * @param publicKey 公钥
     * @param message   内容
     * @param signature 签名内容
     * @return true表示验证通过
     */
    boolean verifySignature(byte[] publicKey, byte[] message, byte[] signature);
}
