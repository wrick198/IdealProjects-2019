package com.knowledge_network.auth.user;

import com.knowledge_network.user.entity.UserRole;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by pentonbin on 17-12-2.
 * <p>
 * 获取当前系统登录账户的接口
 */
public interface KnowledgeNetworkUserDetails extends UserDetails {

    /**
     * 获取当前登录用户的id
     *
     * @return 用户id
     */
    public Integer getUserId();

    /**
     * 获取当前登录用户的角色
     *
     * @return 用户的角色列表
     */
    public Collection<UserRole> getUserRoles();


    /**
     * 获取当前登录用户的邮箱
     *
     * @return 用户的邮箱
     */
    public String getUserEmail();

    /**
     * 获取当前登录用户的手机号
     *
     * @return 用户的手机号
     */
    public String getUserPhone();

    /**
     * 获取当前登录用户的上一次登录时间
     *
     * @return 用户的上一次登录时间
     */
    public Timestamp getLastLoginDatetime();
}
