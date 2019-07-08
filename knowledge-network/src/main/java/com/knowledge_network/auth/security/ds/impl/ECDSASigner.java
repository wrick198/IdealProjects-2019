package com.knowledge_network.auth.security.ds.impl;

import com.knowledge_network.auth.security.ds.DigitalSignatureSigner;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by pentonbin on 18-4-3
 * <p>
 * ECDSA的数字签名认证
 */
public class ECDSASigner implements DigitalSignatureSigner {


    /**
     * privateKey为privateKey.getEncoded()得到的byte数组
     *
     * @param privateKey 私钥
     * @param message    内容
     * @return
     * @throws RuntimeException
     */
    @Override
    public byte[] calculateSignature(byte[] privateKey, byte[] message) throws RuntimeException {
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey);
            KeyFactory keyFactory = KeyFactory.getInstance("EC");

            PrivateKey key = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Signature signature = Signature.getInstance("SHA1withECDSA");
            // 使用私钥签名
            signature.initSign(key);
            signature.update(message);
            return signature.sign();
        } catch (Exception e) {
            throw new RuntimeException("calculate signature failed:" + e.getMessage());
        }
    }

    /**
     * publicKey为publicKey.getEncoded()得到的byte数组
     *
     * @param publicKey 公钥
     * @param message   内容
     * @param signature 签名内容
     * @return
     */
    @Override
    public boolean verifySignature(byte[] publicKey, byte[] message, byte[] signature) {
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey);
            KeyFactory keyFactory = KeyFactory.getInstance("EC");

            PublicKey key = keyFactory.generatePublic(x509EncodedKeySpec);
            Signature sign = Signature.getInstance("SHA1withECDSA");
            // 使用公钥签名
            sign.initVerify(key);
            sign.update(message);
            // 验证私钥签名
            return sign.verify(signature);
        } catch (Exception e) {
            throw new RuntimeException("verify signature failed" + e.getMessage());
        }
    }
}
