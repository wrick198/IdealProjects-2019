package com.knowledge_network.circle.dao.impl;

import com.knowledge_network.circle.dao.NotificationDAO;
import com.knowledge_network.circle.entity.Notification;
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
public class NotificationDAOImpl extends BaseHibernateDAO<Notification> implements NotificationDAO {

    @Override
    public void updateNotification(Notification notification){
        save(notification);
    }
    @Override
    public void deleteNotification(Notification notification) {
        delete(notification);
    }

    @Override
    public List<Notification> findByTargetUser(User targetUser, int start, int row, Order order) {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        BaseHibernateDAO.Condition condition = new BaseHibernateDAO.Condition(ConditionType.EQ, "targetUser", targetUser);
        conditions.add(condition);
        return findByOrderConditionsPage(start, row, order, conditions);
    }

    @Override
    public List<Notification> findByTargetUserAndIsRead(User targetUser, boolean scan, int start, int row, Order order) {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        BaseHibernateDAO.Condition condition1 = new BaseHibernateDAO.Condition(ConditionType.EQ, "targetUser", targetUser);
        conditions.add(condition1);
        BaseHibernateDAO.Condition condition2 = new BaseHibernateDAO.Condition(ConditionType.EQ, "scan", scan);
        conditions.add(condition2);
        return findByOrderConditionsPage(start, row, order, conditions);
    }

    @Override
    public List<Notification> findByTargetUserAndIsRead(User targetUser, boolean scan) {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        BaseHibernateDAO.Condition condition1 = new BaseHibernateDAO.Condition(ConditionType.EQ, "targetUser", targetUser);
        conditions.add(condition1);
        BaseHibernateDAO.Condition condition2 = new BaseHibernateDAO.Condition(ConditionType.EQ, "scan", scan);
        conditions.add(condition2);
        //不想再写函数了，先拿个1000扛着
        return findByConditionsPage(0, 1000, conditions);
    }

    @Override
    public long countByTargetUserAndIsRead(User targetUser, boolean scan) {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        BaseHibernateDAO.Condition condition1 = new BaseHibernateDAO.Condition(ConditionType.EQ, "targetUser", targetUser);
        conditions.add(condition1);
        BaseHibernateDAO.Condition condition2 = new BaseHibernateDAO.Condition(ConditionType.EQ, "scan", scan);
        conditions.add(condition2);
        return findCountByConditions(conditions);
    }

    @Override
    public void updateByIsRead(User targetUser) {

    }

    @Override
    public void deleteByTargetUser(User targetUser) {
        List<Notification> notifications = findBy("targetUser", targetUser);
        for(Notification notification: notifications){
            delete(notification);
        }
    }

    @Override
    public void deleteByUser(User user) {
        List<Notification> notifications = findBy("user", user);
        for(Notification notification: notifications){
            delete(notification);
        }
    }

    @Override
    public void deleteByTopic(Topic topic) {
        List<Notification> notifications = findBy("topic", topic);
        for(Notification notification: notifications){
            delete(notification);
        }
    }
}
