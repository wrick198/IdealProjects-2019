package com.knowledge_network.mobile.request;

import com.knowledge_network.mobile.security.MobileRequestTokenGenerator;
import com.knowledge_network.support.utils.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by pentonbin on 17-12-6.
 * <p>
 * 客户端请求的封装类，包含请求的url以及对应url的解析
 */
public class MobileRequest {

    private static final Logger log = Logger.getLogger(MobileRequest.class);

    private static final String SALT = "OneChance";

    private boolean GET = true; // 是否为GET请求，false则为POST

    private String requestURL; // 请求的url
    private String queryString; // 查询串，用于GET，不适用于POST
    private String servletPath; // servlet路径："auth" + model + invokeMethod
    private String model; // 调用的模块
    private String invokeMethod; // 调用的模块接口
    private Map<String, String> queryParams; // 查询参数，如果是GET请求则将queryString转化为Map
    private String token; // 请求的验证token
    private boolean isBadRequest = true; // 客户端请求格式是否正确
    private boolean isLegalRequest = false; // 该请求是否为合法请求

    private MobileRequest() {
    }

    public static MobileRequest build(HttpServletRequest request) {
        return build(request, true);
    }

    public static MobileRequest build(HttpServletRequest request, boolean isGET) {
        MobileRequest mobileRequest = new MobileRequest();
        mobileRequest.setGET(isGET);
        mobileRequest.setRequestURL(request.getRequestURL().toString());
        if (isGET) { // GET请求，设置查询串
            mobileRequest.setQueryString(request.getQueryString());
        } else { // POST请求，设置查询参数
            Map<String, String> paramMap = new HashMap<>();
            Map<String, String[]> requestParamMap = request.getParameterMap();
            for (Map.Entry<String, String[]> entry : requestParamMap.entrySet()) {
                paramMap.put(entry.getKey(), entry.getValue()[0]);
            }
            mobileRequest.setQueryParams(paramMap);
        }
        mobileRequest.setServletPath(request.getServletPath());
        parseRequest(mobileRequest);
        return mobileRequest;
    }

    private static void parseRequest(MobileRequest mobileRequest) {
        if (mobileRequest == null) {
            return;
        }
        String servletPath = mobileRequest.getServletPath(); // 该字段表示为"auth" + model + invokeMethod，对应访问的接口
        if (servletPath.charAt(0) == '/') {
            servletPath = servletPath.substring(1);
        }
        String[] sp = servletPath.split("/");

        if (sp == null || sp.length != 3) {
            mobileRequest.setBadRequest(true);
            return;
        }
        mobileRequest.setBadRequest(false); // 是否请求格式正确

        mobileRequest.setModel(sp[1]);
        mobileRequest.setInvokeMethod(sp[2]);
        if (mobileRequest.isGET()) { // GET请求
            if (mobileRequest.getQueryString() == null) {
                return;
            }
            Map<String, String> queryParams = new HashMap<>(); // 将查询串存储到Map中
            String queryString = mobileRequest.getQueryString();
            String[] queries = queryString.split("&");
            for (String query : queries) {
                String[] entity = query.split("=");
                if (entity.length == 2) {
                    queryParams.put(entity[0], entity[1]);
                }
            }
            mobileRequest.setQueryParams(queryParams);
            mobileRequest.setToken(queryParams.get("token"));

        } else { // POST请求
            if (mobileRequest.getQueryParams() == null) {
                return;
            }
            mobileRequest.setToken(mobileRequest.getQueryParams().get("token"));
        }
        String token = generateToken(mobileRequest.getRequestURL(), mobileRequest.getQueryParams());
        mobileRequest.setLegalRequest(!StringUtils.isNullOrEmpty(token) && !StringUtils.isNullOrEmpty(mobileRequest.getToken())
                && token.equals(mobileRequest.getToken()));
    }

    private static String generateToken(String requestURL, Map<String, String> queryParams) {
        if (queryParams == null) {
            return null;
        }
        TreeMap<String, String> sortedParams = new TreeMap<>();
        sortedParams.putAll(queryParams);
        StringBuilder sb = new StringBuilder();
        sb.append(requestURL).append("?");
        for (Map.Entry<String, String> param : sortedParams.entrySet()) {
            if (!param.getKey().equals("token")) {
                sb.append(param.getKey()).append("=").append(param.getValue()).append("&");
            }
        }
        sb.append(SALT);
        String url = sb.toString();
        return MobileRequestTokenGenerator.tokenGenerate(url);
    }

    public boolean isGET() {
        return GET;
    }

    private void setGET(boolean GET) {
        this.GET = GET;
    }

    public String getRequestURL() {
        return requestURL;
    }

    private void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public String getQueryString() {
        return queryString;
    }

    private void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getServletPath() {
        return servletPath;
    }

    private void setServletPath(String servletPath) {
        this.servletPath = servletPath;
    }

    public String getModel() {
        return model;
    }

    private void setModel(String model) {
        this.model = model;
    }

    public String getInvokeMethod() {
        return invokeMethod;
    }

    private void setInvokeMethod(String invokeMethod) {
        this.invokeMethod = invokeMethod;
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    private void setQueryParams(Map<String, String> queryParams) {
        this.queryParams = queryParams;
    }

    public String getToken() {
        return token;
    }

    private void setToken(String token) {
        this.token = token;
    }

    public boolean isLegalRequest() {
        return isLegalRequest;
    }

    private void setLegalRequest(boolean legalRequest) {
        isLegalRequest = legalRequest;
    }

    public boolean isBadRequest() {
        return isBadRequest;
    }

    private void setBadRequest(boolean badRequest) {
        isBadRequest = badRequest;
    }
}
