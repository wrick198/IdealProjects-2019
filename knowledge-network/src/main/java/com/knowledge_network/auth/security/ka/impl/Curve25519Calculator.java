package com.knowledge_network.auth.security.ka.impl;

import com.knowledge_network.auth.security.ka.KeyAgreementCalculator;
import org.whispersystems.curve25519.Curve25519;

/**
 * Created by pentonbin on 18-4-3
 * <p>
 * 参考：https://github.com/signalapp/curve25519-java
 */
public class Curve25519Calculator implements KeyAgreementCalculator {

    @Override
    public byte[] calculateSharedSecret(byte[] publicKey, byte[] privateKey) {
        Curve25519 cipher = Curve25519.getInstance(Curve25519.BEST);
        return cipher.calculateAgreement(publicKey, privateKey);
    }
}
