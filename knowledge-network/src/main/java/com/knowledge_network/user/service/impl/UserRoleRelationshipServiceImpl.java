package com.knowledge_network.user.service.impl;

import com.knowledge_network.user.dao.UserRoleRelationshipDAO;
import com.knowledge_network.user.entity.UserRoleRelationship;
import com.knowledge_network.user.service.UserRoleRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pentonbin on 17-12-26
 */
@Service
public class UserRoleRelationshipServiceImpl implements UserRoleRelationshipService {

    @Autowired
    private UserRoleRelationshipDAO userRoleRelationshipDAO;

    @Override
    public void save(UserRoleRelationship relationship) {
        if (relationship != null) {
            userRoleRelationshipDAO.persist(relationship);
        }
    }

    @Override
    public void delete(UserRoleRelationship relationship) {
        if (relationship != null) {
            userRoleRelationshipDAO.delete(relationship);
        }
    }
}
