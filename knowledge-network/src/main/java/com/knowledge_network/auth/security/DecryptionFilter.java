package com.knowledge_network.auth.security;

import com.knowledge_network.auth.security.entity.SymmetricEncryption;
import com.knowledge_network.auth.security.se.SymmetricEncryptProcessor;
import com.knowledge_network.auth.security.se.imple.Chacha20Processor;
import com.knowledge_network.auth.security.service.SymmetricEncryptionService;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.support.server.DecryptHttpServletRequestWrapper;
import com.knowledge_network.support.server.EncryptHttpServletResponseWrapper;
import com.knowledge_network.support.utils.JsonMapper;
import com.knowledge_network.support.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Created by pentonbin on 18-4-4
 * <p>
 * 加解密的安全过滤器
 */
public class DecryptionFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(DecryptionFilter.class);

    @Autowired
    private SymmetricEncryptionService symmetricEncryptionService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 支持@Autowired
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        EncryptHttpServletResponseWrapper responseWrapper = new EncryptHttpServletResponseWrapper((HttpServletResponse) response);

        // 不包含客户端唯一序号
        String clientSequence = httpServletRequest.getHeader("clientSequence");
        if (clientSequence == null || clientSequence.trim().length() == 0) {
            rejectRequest(response, ResponseErrorEnum.BAD_REQUEST, "Request's header must contain clientSequence");
            return;
        }

        if (!isKeyAgreement(httpServletRequest)) { // 不是进行密钥协商，进行解密
            SymmetricEncryption client = symmetricEncryptionService.getByClientSequence(clientSequence);
            if (client == null) { // 查找不到该客户端唯一序号
                logger.warn("Client[clientSequence=" + clientSequence + "] call request:" +
                        ((HttpServletRequest) request).getServletPath());
                rejectRequest(response, ResponseErrorEnum.ILLEGAL_REQUEST, null);
                return;
            }

            StringBuilder sb = new StringBuilder();
            String line = null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpServletRequest.getInputStream()));
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            String data = sb.toString();

            SymmetricEncryptProcessor processor = new Chacha20Processor(StringUtils.decodeHexStr(client.getKey())); // 使用共享密钥进行解密
            byte[] plainText = processor.decrypt(StringUtils.decodeHexStr(data));
            String requestBody = new String(plainText, "UTF-8");
            if (!requestBody.contains("timestampSeq")) { // 防重放，必定包含"timestampSeq"
                rejectRequest(response, ResponseErrorEnum.BAD_REQUEST, "Request body must contain timestampSeq");
                return;
            } else {
                long curTimestampSeq = extraTimestampSeq(requestBody);
                if (curTimestampSeq <= client.getTimestampSeq()) {
                    rejectRequest(response, ResponseErrorEnum.ILLEGAL_REQUEST, null);
                    return;
                } else {
                    // 更新时间戳序号
                    client.setTimestampSeq(curTimestampSeq);
                    symmetricEncryptionService.updateSymmetricEncryption(client);
                }
            }

            DecryptHttpServletRequestWrapper requestWrapper = new DecryptHttpServletRequestWrapper(httpServletRequest, requestBody);
            try {
            chain.doFilter(requestWrapper, responseWrapper);
            } catch (Exception e) {
                rejectRequest(response, ResponseErrorEnum.INTERNAL_SERVER_ERROR, null);
                return;
            }
            // 请求结束对结果进行加密
            byte[] dataStream = responseWrapper.getDataStream();
            byte[] encrypt = processor.encrypt(dataStream);
            String hex = StringUtils.encodeHexStr(encrypt);
            response.getOutputStream().write(hex.getBytes());
        } else {
            chain.doFilter(request, response);
        }
    }

    /**
     * 根据请求的URL判断是否进行密钥协商
     *
     * @param request
     * @return
     */
    private static boolean isKeyAgreement(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        if (servletPath == null || !servletPath.trim().equals("/keyAgreement")) {
            return false;
        }
        return true;
    }

    /**
     * 根据请求的JSON数据获取时间戳序号
     *
     * @param content 请求的JSON数据
     * @return 时间戳序号
     */
    private static long extraTimestampSeq(String content) {
        Map<String, String> requestMap = JsonMapper.json2Map(content);
        String timestampSeq = requestMap.get("timestampSeq");
        return Long.parseLong(timestampSeq);
    }

    /**
     * 因不满足安全机制要求，拒绝请求
     *
     * @param response 请求的响应对象response
     * @param error    错误类型
     * @param message  返回的信息
     * @throws IOException
     */
    private static void rejectRequest(ServletResponse response, ResponseErrorEnum error, String message) throws IOException {
        ResponseResult<Object> result = ResponseResult.newErrorInstance(error, message);
        response.getOutputStream().write(JsonMapper.obj2Json(result).getBytes());
    }
}
