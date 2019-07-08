package com.knowledge_network.support.utils;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by pentonbin on 17-12-2.
 * <p>
 * Jackson的工具类
 */
public class JsonMapper {
    private static final Logger logger = Logger.getLogger(JsonMapper.class);

    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final String DATE_FORMAT = "yyyyMMddHHmmss";

    private static final JsonFactory JF = new JsonFactory();

    static {
        // 允许使用不带引号的变量名称，以及允许单引号
        JF.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        JF.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    private static final ObjectMapper MAPPER = new ObjectMapper(JF);

    static {
        SerializationConfig serializationConfig = MAPPER.getSerializationConfig();
        MAPPER.setSerializationConfig(serializationConfig.with(SerializationConfig.Feature.USE_ANNOTATIONS).
                withDateFormat(new SimpleDateFormat(DATE_FORMAT)));

        DeserializationConfig deserializationConfig = MAPPER.getDeserializationConfig();
        MAPPER.setDeserializationConfig(deserializationConfig.with(DeserializationConfig.Feature.USE_ANNOTATIONS).
                withDateFormat(new SimpleDateFormat(DATE_FORMAT)));

        // 设置输入时忽略JSON字符串中存在，而Java对象实际没有的属性
        MAPPER.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static ObjectMapper getObjectMapper() {
        return MAPPER;
    }

    public static <T> T json2Obj(String json, Class<T> pojoClass) {
        try {
            return getObjectMapper().readValue(json, pojoClass);
        } catch (IOException e) {
            logger.error("Fail to convert json to obj");
            e.printStackTrace();
        }
        return null;
    }

    public static String obj2Json(Object obj) {
        try {
            return getObjectMapper().writeValueAsString(obj);
        } catch (IOException e) {
            logger.error("Fail to convert obj to json");
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> json2List(String json) {
        try {
            return getObjectMapper().readValue(json, new TypeReference<List<T>>() {
            });
        } catch (IOException e) {
            logger.error("Fail to convert json to list");
            e.printStackTrace();
        }
        return null;
    }

    public static <T> String list2Json(List<T> objs) {
        // TODO
        return null;
    }

    public static <T> Map<String, T> json2Map(String json) {
        try {
            return getObjectMapper().readValue(json, new TypeReference<Map<String, T>>() {
            });
        } catch (IOException e) {
            logger.error("Fail to convert json to map");
            e.printStackTrace();
        }
        return null;
    }

    public static <K, V> String map2Json(Map<K, V> objs) {
        // TODO
        return null;
    }
}
