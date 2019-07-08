package com.knowledge_network.auth.security.ka.impl;

import com.knowledge_network.auth.security.ka.KeyAgreementGenerator;
import org.whispersystems.curve25519.Curve25519;
import org.whispersystems.curve25519.Curve25519KeyPair;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * Created by pentonbin on 18-4-3
 * <p>
 * 参考：https://github.com/signalapp/curve25519-java
 */
public class Curve25519Generator implements KeyAgreementGenerator {

    private Curve25519KeyPair keyPair;

    public Curve25519Generator() {
        keyPair = Curve25519.getInstance(Curve25519.BEST).generateKeyPair();
    }

    @Override
    public byte[] getPublicKey() {
        return keyPair.getPublicKey();
    }

    @Override
    public byte[] getPrivateKey() {
        return keyPair.getPrivateKey();
    }

}
