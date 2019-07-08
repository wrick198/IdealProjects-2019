package com.knowledge_network.auth.oauth2;

import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.common.ResponseResult;
import org.apache.log4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Created by pentonbin on 18-1-9
 * <p>
 * 修改OAuth2 返回的数据格式
 */
@ControllerAdvice
public class OAuth2ResponseAdvice implements ResponseBodyAdvice<Object> {

    private static final Logger logger = Logger.getLogger(OAuth2ResponseAdvice.class);

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (returnType.getNestedParameterType().equals(OAuth2Exception.class) ||
                returnType.getNestedParameterType().equals(OAuth2AccessToken.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {

        if (returnType.getNestedParameterType().equals(OAuth2AccessToken.class)) {
            return ResponseResult.newSucceedInstance(null, body);
        } else if (returnType.getNestedParameterType().equals(OAuth2Exception.class)) {
            return ResponseResult.newErrorInstance(ResponseErrorEnum.USERNAME_OR_PASSWORD_ERROR, body);
        }
        return body;
    }
}
