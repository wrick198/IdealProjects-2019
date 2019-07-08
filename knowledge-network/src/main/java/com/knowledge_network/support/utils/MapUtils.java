package com.knowledge_network.support.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pentonbin on 17-12-2.
 */
public class MapUtils {

    public static Map<String, String> transferMap(Map<String, String[]> params) {
        if (params == null) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            map.put(entry.getKey(), entry.getValue()[0]);
        }
        return map;
    }
}
