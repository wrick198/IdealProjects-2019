package com.knowledge_network.auth.user.impl;

import com.knowledge_network.user.entity.User;
import com.knowledge_network.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by pentonbin on 17-12-2.
 *
 * 实现UserDetailsService接口
 * 登录认证时Spring Security通过UserDetailsService的loadUserByUsername()方法获取对应的UserDetails进行认证
 */
public class KnowledgeNetworkUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    private String rolePrefix = "";

    @Override
    public UserDetails loadUserByUsername(String username) throws RuntimeException {
        User user = null;
        try {
            user = userService.getUserByUsername(username);
        } catch (Exception e) {
            throw new RuntimeException("查询用户数据库出错");
        }

        if (user == null) {
            throw new RuntimeException("账号[" + username + "]不存在！");
        }

        KnowledgeNetworkUserDetailsImpl userDetail = new KnowledgeNetworkUserDetailsImpl(user);
        userDetail.setRolePrefix(rolePrefix);
        return userDetail;
    }

    public String getRolePrefix() {
        return rolePrefix;
    }

    public void setRolePrefix(String rolePrefix) {
        this.rolePrefix = rolePrefix;
    }
}
