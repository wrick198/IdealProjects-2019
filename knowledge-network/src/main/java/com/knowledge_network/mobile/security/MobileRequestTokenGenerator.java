package com.knowledge_network.mobile.security;

import com.knowledge_network.support.utils.HashUtils;
import com.knowledge_network.support.utils.StringUtils;
import org.apache.log4j.Logger;

/**
 * Created by pentonbin on 17-12-6.
 */
public class MobileRequestTokenGenerator {

    private static final Logger logger = Logger.getLogger(MobileRequestTokenGenerator.class);

    private static final String KEY = "SYSU-CS-KN-2017";

    public static String tokenGenerate(String url) {
        String utf8 = StringUtils.urlEncode(url);
        byte[] bytes = HashUtils.hmacSha1(new String[]{utf8}, KEY.getBytes());
        return StringUtils.encodeHexStr(bytes);
    }
}
