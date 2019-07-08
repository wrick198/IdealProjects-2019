package com.knowledge_network.support.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

/**
 * Created by pentonbin on 17-12-2.
 * <p>
 * 字符串相关工具类
 */
public class StringUtils {

    public static final String CHARSET_NAME_UTF8 = "UTF-8";

    public static final char[] DIGITAL = "0123456789ABCDEF".toCharArray();

    public static final String DEFAULT_DATA_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    public final static Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}"
            + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}"
            + "(" + "\\."
            + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+");

    private static Logger logger = LoggerFactory.getLogger(StringUtils.class);

    private static AtomicInteger randomInt = new AtomicInteger(1);

    public static String format(Date date) {
        String retString = "";
        try {
            SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATA_TIME_FORMAT);
            retString = format.format(date);
        } catch (Exception e) {
            logger.error("format error:", e);
        }
        return retString;
    }

    public static boolean isNullOrEmpty(String string) {
        return string == null || string.trim().length() == 0;
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static String encodeHexStr(final byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        char[] result = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            result[i * 2] = DIGITAL[(bytes[i] & 0xf0) >> 4];
            result[i * 2 + 1] = DIGITAL[bytes[i] & 0x0f];
        }
        return new String(result);
    }

    public static byte[] decodeHexStr(final String str) {
        if (str == null) {
            return null;
        }
        char[] charArray = str.toCharArray();
        if (charArray.length % 2 != 0) {
            throw new RuntimeException("hex str length must can mod 2, str:" + str);
        }
        byte[] bytes = new byte[charArray.length / 2];
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            int b;
            if (c >= '0' && c <= '9') {
                b = (c - '0') << 4;
            } else if (c >= 'A' && c <= 'F') {
                b = (c - 'A' + 10) << 4;
            } else {
                throw new RuntimeException("unsupported hex str:" + str);
            }
            c = charArray[++i];
            if (c >= '0' && c <= '9') {
                b |= c - '0';
            } else if (c >= 'A' && c <= 'F') {
                b |= c - 'A' + 10;
            } else {
                throw new RuntimeException("unsupported hex str:" + str);
            }
            bytes[i / 2] = (byte) b;
        }
        return bytes;
    }

    public static String toString(final byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        try {
            return new String(bytes, CHARSET_NAME_UTF8);
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static String toString(final byte[] bytes, String charset) {
        if (bytes == null) {
            return null;
        }
        try {
            return new String(bytes, charset);
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static byte[] toBytes(final String str) {
        if (str == null) {
            return null;
        }
        try {
            return str.getBytes(CHARSET_NAME_UTF8);
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static int getCharLength(String str) {
        int counter = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < 255) {
                counter++;
            } else {
                counter = counter + 2;
            }
        }
        return counter;
    }

    public static String urlEncode(String url) {
        if (url == null) return null;
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException:", e);
            return "";
        }
    }

    public static boolean isEmailFormat(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    /**
     * 使用RC4算法，key为UUID，对递增的数字进行加密生成
     *
     * @return
     */
    public static String getRandomString() throws Exception {
        int i = randomInt.incrementAndGet();
        String key = UUID.randomUUID().toString();
        key = key.replace("", "").toUpperCase();
        byte[] bytes = RC4.encrypt(String.format("%06d", i).getBytes(), key.getBytes());
        return encodeHexStr(bytes);
    }

}
