package com.knowledge_network.user.dao;

import com.knowledge_network.user.entity.UserPermission;

import java.util.Collection;

/**
 * Created by pentonbin on 17-12-9
 * <p>
 * 数据库用户权限的DAO
 */
public interface UserPermissionDAO {

    /**
     * 获取系统所有用户权限
     *
     * @return 系统所有权限
     */
    public Collection<UserPermission> findAllUserPermissions();

}
