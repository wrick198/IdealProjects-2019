package com.knowledge_network.auth.user;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by pentonbin on 17-12-2.
 * <p>
 * 获取当前登录用户的工具类
 */
public class LoginUserHolder {

    private static LoginUserHolder instance;

    public static LoginUserHolder getInstance() {
        if (instance == null) {
            synchronized (LoginUserHolder.class) {
                if (instance == null) {
                    instance = new LoginUserHolder();
                }
            }
        }
        return instance;
    }

    private LoginUserHolder() {
    }

    /**
     * 获取当前登录用户的方法
     *
     * @return 表示用户的接口KnowledgeNetworkUserDetail
     */
    public KnowledgeNetworkUserDetails getCurrentLoginUser() {
        KnowledgeNetworkUserDetails user =
                (KnowledgeNetworkUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }

}
