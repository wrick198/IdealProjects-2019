package com.knowledge_network.circle.dao.impl;

import com.knowledge_network.circle.dao.CollectDAO;
import com.knowledge_network.circle.entity.Collect;
import com.knowledge_network.circle.entity.Topic;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.User;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wshish000 on 18-3-4
 */
@Repository
public class CollectDAOImpl extends BaseHibernateDAO<Collect> implements CollectDAO {

    @Override
    public void updateCollect(Collect collect){
        save(collect);
    }

    @Override
    public void deleteCollect(Collect collect) {
        delete(collect);
    }

    @Override
    public List<Collect> findByUser(User user, int start, int row, Order order) {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        BaseHibernateDAO.Condition condition = new BaseHibernateDAO.Condition(ConditionType.EQ, "user", user);
        conditions.add(condition);
        return findByOrderConditionsPage(start, row, order, conditions);
    }

    @Override
    public long countByUser(User user) {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        BaseHibernateDAO.Condition condition = new BaseHibernateDAO.Condition(ConditionType.EQ, "user", user);
        conditions.add(condition);
        return findCountByConditions(conditions);
    }

    @Override
    public long countByTopic(Topic topic) {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        BaseHibernateDAO.Condition condition = new BaseHibernateDAO.Condition(ConditionType.EQ, "topic", topic);
        conditions.add(condition);
        return findCountByConditions(conditions);
    }

    @Override
    public Collect findByUserAndTopic(User user, Topic topic) {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        BaseHibernateDAO.Condition condition1 = new BaseHibernateDAO.Condition(ConditionType.EQ, "user", user);
        conditions.add(condition1);
        BaseHibernateDAO.Condition condition2 = new BaseHibernateDAO.Condition(ConditionType.EQ, "topic", topic);
        conditions.add(condition2);
        List<Collect> collects = findByConditionsPage(0, 1, conditions);
        if(collects.size() == 0){
            return null;
        }
        return collects.get(0);
    }

    @Override
    public void deleteById(Integer id) {
        Collect collect = findById(id);
        delete(collect);
    }

    @Override
    public void deleteByUser(User user) {
        List<Collect> collects = findBy("user", user);
        for(Collect collect: collects){
            delete(collect);
        }
    }

    @Override
    public void deleteByTopic(Topic topic) {
        List<Collect> collects = findBy("topic", topic);
        for(Collect collect: collects){
            delete(collect);
        }
    }
}
