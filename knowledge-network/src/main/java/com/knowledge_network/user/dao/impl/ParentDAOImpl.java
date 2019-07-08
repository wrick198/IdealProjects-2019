package com.knowledge_network.user.dao.impl;

import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.dao.ParentDAO;
import com.knowledge_network.user.entity.Parent;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pentonbin on 17-12-14
 */
@Repository
public class ParentDAOImpl extends BaseHibernateDAO<Parent> implements ParentDAO {

    @Override
    public Parent findParentById(Integer id) {
        return findById(id);
    }

    @Override
    public Parent findParentByUsername(String username) {
        return findByUnique("username", username);
    }

    @Override
    public void createParent(Parent parent) {
        save(parent);
    }

    @Override
    public List<Parent> findParentByConditionsOrderPage(int start, int rows, Order order, List<Condition> conditions) {
        return findByOrderConditionsPage(start, rows, order, conditions);
    }

    @Override
    public List<Parent> findParentByOrderPage(int start, int rows, Order order) {
        return findParentByConditionsOrderPage(start, rows, order, null);
    }

    @Override
    public long getParentCountByConditions(List<Condition> conditions) {
        return findCountByConditions(conditions);
    }

    @Override
    public void updateParent(Parent parent) {
        save(parent);
    }
}
