package com.knowledge_network.knowledge_network.dao.impl;

import com.knowledge_network.knowledge_network.dao.ResourceTypeDAO;
import com.knowledge_network.knowledge_network.entity.ResourceType;
import com.knowledge_network.support.base.BaseHibernateDAO;
import org.springframework.stereotype.Repository;

/**
 * Created by wshish000 on 17-12-9
 */
@Repository
public class ResourceTypeDAOImpl extends BaseHibernateDAO<ResourceType> implements ResourceTypeDAO {
    @Override
    public ResourceType findResourceTypeById(Integer id) {
        return findByUnique("id", id);
    }

    @Override
    public ResourceType findResourceTypeByType(String name) {
        return findByUnique("type", name);
    }

    @Override
    public void addResourceType(ResourceType resourceType) {
        save(resourceType);
    }

    @Override
    public void updateCourse(ResourceType resourceType) {
        save(resourceType);
    }

    @Override
    public void deleteCourse(ResourceType resourceType) {
        delete(resourceType);
    }
}
