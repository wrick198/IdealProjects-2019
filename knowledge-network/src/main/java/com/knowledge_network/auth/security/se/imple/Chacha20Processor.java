package com.knowledge_network.auth.security.se.imple;

import com.knowledge_network.auth.security.se.SymmetricEncryptProcessor;

/**
 * Created by pentonbin on 18-4-3
 */
public class Chacha20Processor implements SymmetricEncryptProcessor {

    private static final byte[] DEFAULT_NONCE = "\0\0\0\0\0\0\0\0".getBytes();
    private static final int DEFAULT_COUNTER = 0;

    private byte[] key;
    private byte[] nonce;
    private int counter;

    public Chacha20Processor(byte[] key) {
        this(key, DEFAULT_NONCE, DEFAULT_COUNTER);
    }

    public Chacha20Processor(byte[] key, byte[] nonce, int counter) {
        if (key.length != 32) {
            throw new IllegalArgumentException("Key's length must be 32!");
        }
        if (nonce.length != 8) {
            throw new IllegalArgumentException("nonce's length must be 8!");
        }
        this.key = key;
        this.nonce = nonce;
        this.counter = counter;
    }

    @Override
    public byte[] encrypt(byte[] plaintext) {
        byte[] ret = new byte[plaintext.length];
        Chacha20 cipher = new Chacha20(key, nonce, counter);
        cipher.encrypt(ret, plaintext, plaintext.length);
        return ret;
    }

    @Override
    public byte[] decrypt(byte[] cipher) {
        byte[] plaintext = new byte[cipher.length];
        Chacha20 decoder = new Chacha20(key, nonce, counter);
        decoder.encrypt(plaintext, cipher, cipher.length);
        return plaintext;
    }
}
