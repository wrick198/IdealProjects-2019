package com.knowledge_network.user.dao.impl;

import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.dao.SystemManagerDAO;
import com.knowledge_network.user.entity.SystemManager;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pentonbin on 17-12-14
 */
@Repository
public class SystemManagerDAOImpl extends BaseHibernateDAO<SystemManager> implements SystemManagerDAO {

    @Override
    public SystemManager findSystemManagerById(Integer id) {
        return findById(id);
    }

    @Override
    public void createSystemManager(SystemManager systemManager) {
        save(systemManager);
    }

    @Override
    public List<SystemManager> findSystemManagerByConditionsOrderPage(int start, int rows, Order order, List<Condition> conditions) {
        return findByOrderConditionsPage(start, rows, order, conditions);
    }

    @Override
    public List<SystemManager> findSystemManagerByOrderPage(int start, int rows, Order order) {
        return findSystemManagerByConditionsOrderPage(start, rows, order, null);
    }

    @Override
    public long getSystemManagerCountByConditions(List<Condition> conditions) {
        return findCountByConditions(conditions);
    }

    @Override
    public void updateSystemManager(SystemManager systemManager) {
        save(systemManager);
    }
}
