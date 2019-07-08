package com.knowledge_network.user.dao.impl;

import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.dao.KnowledgeNetworkManagerDAO;
import com.knowledge_network.user.entity.KnowledgeNetworkManager;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pentonbin on 17-12-14
 */
@Repository
public class KnowledgeNetworkManagerDAOImpl extends BaseHibernateDAO<KnowledgeNetworkManager> implements KnowledgeNetworkManagerDAO {

    @Override
    public KnowledgeNetworkManager findKnowledgeNetworkManagerById(Integer id) {
        return findById(id);
    }

    @Override
    public void createKnowledgeNetworkManager(KnowledgeNetworkManager knowledgeNetworkManager) {
        save(knowledgeNetworkManager);
    }

    @Override
    public List<KnowledgeNetworkManager> findKnowledgeNetworkManagerByConditionsOrderPage(int start, int rows, Order order, List<Condition> conditions) {
        return findByOrderConditionsPage(start, rows, order, conditions);
    }

    @Override
    public List<KnowledgeNetworkManager> findKnowledgeNetworkManagerByOrderPage(int start, int rows, Order order) {
        return findKnowledgeNetworkManagerByConditionsOrderPage(start, rows, order, null);
    }

    @Override
    public long getKnowledgeNetworkManagerCountByConditions(List<Condition> conditions) {
        return findCountByConditions(conditions);
    }

    @Override
    public void updateKnowledgeNetworkManager(KnowledgeNetworkManager knowledgeNetworkManager) {
        save(knowledgeNetworkManager);
    }
}
