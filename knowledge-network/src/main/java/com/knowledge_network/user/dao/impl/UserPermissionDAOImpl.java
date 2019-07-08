package com.knowledge_network.user.dao.impl;

import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.dao.UserPermissionDAO;
import com.knowledge_network.user.entity.UserPermission;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by pentonbin on 17-12-9
 */
@Repository
public class UserPermissionDAOImpl extends BaseHibernateDAO<UserPermission> implements UserPermissionDAO {

    @Override
    public Collection<UserPermission> findAllUserPermissions() {
        return findAll();
    }
}
