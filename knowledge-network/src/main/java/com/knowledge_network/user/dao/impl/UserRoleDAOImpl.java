package com.knowledge_network.user.dao.impl;

import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.dao.UserRoleDAO;
import com.knowledge_network.user.entity.UserRole;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by pentonbin on 17-12-10
 */
@Repository
public class UserRoleDAOImpl extends BaseHibernateDAO<UserRole> implements UserRoleDAO {

    @Override
    public Collection<UserRole> findAllUserRoles() {
        return findAll();
    }
}
