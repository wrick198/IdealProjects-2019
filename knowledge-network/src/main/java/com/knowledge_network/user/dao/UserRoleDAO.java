package com.knowledge_network.user.dao;

import com.knowledge_network.user.entity.UserRole;

import java.util.Collection;

/**
 * Created by pentonbin on 17-12-10
 *
 * 获取系统用户角色的DAO
 */
public interface UserRoleDAO {

    /**
     * 获取所有用户角色
     *
     * @return 所有用户角色
     */
    public Collection<UserRole> findAllUserRoles();

}
