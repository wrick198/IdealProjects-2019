package com.knowledge_network.knowledge_network.dao.impl;

import com.knowledge_network.knowledge_network.dao.ResourceDAO;
import com.knowledge_network.knowledge_network.entity.Resource;
import com.knowledge_network.support.base.BaseHibernateDAO;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wshish000 on 17-12-6
 */
@Repository
public class ResourceDAOImpl extends BaseHibernateDAO<Resource> implements ResourceDAO {
    @Override
    public Resource findResourceById(Integer id) {
        return findById(id);
    }

    @Override
    public Resource findResourceByName(String name) {
        return (Resource) findByUnique("name", name);
    }

    @Override
    public List<Resource> findResourceByDescription(String description) {
        return findBy("description", description);
    }

    @Override
    public List<Resource> findResourceByConditions(Map<String, String> conditions) {
        List<Criterion> criterions = new ArrayList<>();
        int i = 0;

        for (String s : conditions.keySet()) {
            if (s.equals("description")) {
                criterions.add(createCriterionByCondition(ConditionType.LIKE, s, conditions.get(s)));
                i++;
            } else {
                criterions.add(createCriterionByCondition(ConditionType.EQ, s, conditions.get(s)));
                i++;
            }
        }
        Order order = Order.asc("rank");
        return findByOrder(order, criterions);
    }


//    @Override
//    public List<Resource> findResourceByUploaderId(Integer uploaderId) {
//        return findBy("uploaderUserId", uploaderId);
//    }

    @Override
    public void addResource(Resource resource) {
        save(resource);
    }

    @Override
    public void updateResource(Resource resource) {
        save(resource);
    }

    @Override
    public void deleteResource(Resource resource) {
        delete(resource);
    }
}
