package com.knowledge_network.mobile.security;

import com.knowledge_network.mobile.request.MobileRequest;
import com.knowledge_network.mobile.request.MobileServerResponse;
import com.knowledge_network.support.utils.JsonMapper;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by pentonbin on 17-12-6.
 */
public class MobileRequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String method = httpServletRequest.getMethod();
        MobileRequest mobileRequest = MobileRequest.build(httpServletRequest, method.equals("GET"));

        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.setCharacterEncoding("utf-8");

        if (mobileRequest.isBadRequest()) { // 错误请求
            httpServletResponse.setStatus(MobileServerResponse.BAD_REQUEST);
            httpServletResponse.getWriter().write(JsonMapper.obj2Json(MobileServerResponse.BAD_REQUEST_RESPONSE));
        } else {
            // 合法请求
            if (mobileRequest.isLegalRequest()) {
                httpServletRequest.setAttribute("mobileRequest", mobileRequest);
            } else {
                // 非法请求
                httpServletResponse.setStatus(MobileServerResponse.FORBIDDEN);
                httpServletResponse.getWriter().write(JsonMapper.obj2Json(MobileServerResponse.FORBIDDEN_RESPONSE));
            }
        }
        return mobileRequest.isLegalRequest();
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
