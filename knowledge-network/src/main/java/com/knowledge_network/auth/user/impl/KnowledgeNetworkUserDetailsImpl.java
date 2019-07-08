package com.knowledge_network.auth.user.impl;

import com.knowledge_network.auth.user.KnowledgeNetworkUserDetails;
import com.knowledge_network.user.entity.User;
import com.knowledge_network.user.entity.UserRole;
import com.knowledge_network.user.entity.UserRoleRelationship;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pentonbin on 17-12-2.
 * <p>
 * KnowledgeNetworkUserDetail实现UserDetails
 * UserDetails为Spring Security中获取用户名、密码、权限等认证相关的信息接口。
 */
public class KnowledgeNetworkUserDetailsImpl implements KnowledgeNetworkUserDetails {

    private User user;
    private String rolePrefix = "";

    public KnowledgeNetworkUserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Collection<UserRoleRelationship> userRoleRelationships = user.getUserRoleRelationships();
        Collection<UserRole> userRoles = new ArrayList<>();
        for (UserRoleRelationship relationship : userRoleRelationships) {
            userRoles.add(relationship.getUserRole());
        }
        if (userRoles.size() == 0) {
            return null;
        }
        for (UserRole userRole : userRoles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(rolePrefix + userRole.getRole()));
        }
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getLogoff() == User.USER_NON_LOGOFF;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getEnable() == User.USER_ENABLE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getEnable() == User.USER_ENABLE;
    }

    @Override
    public boolean isEnabled() {
        return user.getEnable() == User.USER_ENABLE;
    }

    public String getRolePrefix() {
        return rolePrefix;
    }

    public void setRolePrefix(String rolePrefix) {
        this.rolePrefix = rolePrefix;
    }

    @Override
    public Integer getUserId() {
        return user.getId();
    }

    @Override
    public Collection<UserRole> getUserRoles() {
        Collection<UserRoleRelationship> relationships = user.getUserRoleRelationships();
        Collection<UserRole> userRoles = new ArrayList<>();
        for (UserRoleRelationship relationship : relationships) {
            userRoles.add(relationship.getUserRole());
        }
        return userRoles;
    }

    @Override
    public String getUserEmail() {
        return user.getEmail();
    }

    @Override
    public String getUserPhone() {
        return user.getPhone();
    }

    @Override
    public Timestamp getLastLoginDatetime() {
        return user.getLastLoginDatetime();
    }
}