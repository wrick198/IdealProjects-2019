package com.knowledge_network.support.utils;

import org.apache.log4j.Logger;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {

    private static Logger log = Logger.getLogger(HashUtils.class);

    /**
     * @param ori
     * @return 32 length String; if fails, then return ori String.
     */
    public static String md5(String ori) {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            md.update(ori.getBytes(), 0, ori.length());
            byte[] bytes = md.digest();
            BigInteger i = new BigInteger(1, bytes);
            return String.format("%1$032x", i);
        } catch (NoSuchAlgorithmException e) {
            log.error("md5 error:", e);
            return ori;
        }
    }

    public static String sha1(String ori) {
        try {
            MessageDigest md = MessageDigest.getInstance("sha1");
            md.update(ori.getBytes(), 0, ori.length());
            BigInteger i = new BigInteger(1, md.digest());
            return String.format("%1$040x", i);
        } catch (NoSuchAlgorithmException e) {
            log.error("sha1 error:", e);
            return ori;
        }
    }

    public static final String HMAC_SHA1 = "HmacSHA1";

    public static byte[] hmacSha1(String[] datas, byte[] key) {
        SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);
        Mac mac;
        try {
            mac = Mac.getInstance(HMAC_SHA1);
            mac.init(signingKey);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        try {
            for (String data : datas) {
                mac.update(data.getBytes(StringUtils.CHARSET_NAME_UTF8));
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return mac.doFinal();
    }

}
