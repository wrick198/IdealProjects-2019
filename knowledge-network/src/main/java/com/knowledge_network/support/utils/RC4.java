package com.knowledge_network.support.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;

/**
 * Created by pentonbin on 18-4-18
 */
public class RC4 {

    public static byte[] encrypt(byte[] plainText, byte[] key) throws Exception {
        KeyGenerator rc4 = KeyGenerator.getInstance("RC4");
        rc4.init(new SecureRandom(key));
        SecretKey secretKey = rc4.generateKey();

        // create an instance of cipher
        Cipher cipher = Cipher.getInstance("RC4");

        // initialize the cipher with the key
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // encrypt!
        return cipher.doFinal(plainText);
    }

    public static byte[] decrypt(byte[] encrypted, byte[] key) throws Exception {
        KeyGenerator rc4 = KeyGenerator.getInstance("RC4");
        rc4.init(new SecureRandom(key));
        SecretKey secretKey = rc4.generateKey();

        // do the decryption with that key
        Cipher cipher = Cipher.getInstance("RC4");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(encrypted);
    }
}
