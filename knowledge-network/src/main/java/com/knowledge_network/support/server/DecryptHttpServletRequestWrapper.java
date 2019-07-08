package com.knowledge_network.support.server;

import com.knowledge_network.support.utils.JsonMapper;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Created by pentonbin on 18-4-4
 * <p>
 * 封装解密后的ServletRequest
 */
public class DecryptHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private Map<String, String[]> params;
    private byte[] body;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request
     * @throws IllegalArgumentException if the request is null
     */
    public DecryptHttpServletRequestWrapper(HttpServletRequest request, String requestBody) {
        super(request);
        try {
            body = requestBody.getBytes("UTF-8");
        } catch (Exception e) {
            body = requestBody.getBytes();
        }
        Map<String, String> requestMap = JsonMapper.json2Map(requestBody);
        params = new HashMap<>();
        if (requestMap != null) {
            for (Map.Entry<String, String> entry : requestMap.entrySet()) {
                if (!entry.getKey().equalsIgnoreCase("timestampSeq")) {
                    params.put(entry.getKey(), new String[]{entry.getValue()});
                }
            }
        }
    }

    public void setParameter(String key, String value) {
        params.put(key, new String[]{value});
    }

    public void setParameter(String key, String[] values) {
        params.put(key, values);
    }

    @Override
    public String getParameter(String name) {
        Object v = params.get(name);
        if (v == null) {
            return null;
        } else if (v instanceof String[]) {
            String[] strArr = (String[]) v;
            if (strArr.length > 0) {
                return strArr[0];
            } else {
                return null;
            }
        } else {
            return v.toString();
        }
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return params;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        Vector l = new Vector(params.keySet());
        return l.elements();
    }

    @Override
    public String[] getParameterValues(String name) {
        return params.get(name);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
}
