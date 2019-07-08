package com.knowledge_network.user.dao.impl;

import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.dao.UserRoleRelationshipDAO;
import com.knowledge_network.user.entity.UserRoleRelationship;
import org.springframework.stereotype.Repository;

/**
 * Created by pentonbin on 17-12-26
 */
@Repository
public class UserRoleRelationshipDAOImpl extends BaseHibernateDAO<UserRoleRelationship> implements UserRoleRelationshipDAO {

    @Override
    public void persist(UserRoleRelationship pk) {
        super.persist(pk);
    }

    @Override
    public void delete(UserRoleRelationship pk) {
        super.delete(pk);
    }
}
