package com.knowledge_network.auth.security.ds.impl;

import com.knowledge_network.auth.security.ds.DigitalSignatureGenerator;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

/**
 * Created by pentonbin on 18-4-3
 */
public class ECDSAGenerator implements DigitalSignatureGenerator {

    private ECPrivateKey privateKey;
    private ECPublicKey publicKey;

    public ECDSAGenerator() {
        try {
            KeyPairGenerator keyPairGenerator = null;
            keyPairGenerator = KeyPairGenerator.getInstance("EC");
            keyPairGenerator.initialize(256);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            // 生成ECDSA算法的公钥和私钥
            privateKey = (ECPrivateKey) keyPair.getPrivate();
            publicKey = (ECPublicKey) keyPair.getPublic();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to generate key pair:" + e.getMessage());
        }
    }

    @Override
    public byte[] getVerifyKey() {
        return publicKey.getEncoded();
    }

    @Override
    public byte[] getSignKey() {
        return privateKey.getEncoded();
    }
}
