package com.knowledge_network.circle.dao.impl;

import com.knowledge_network.circle.dao.ReplyDAO;
import com.knowledge_network.circle.entity.Reply;
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
public class ReplyDAOImpl extends BaseHibernateDAO<Reply> implements ReplyDAO {

    @Override
    public Reply findReplyById(Integer id) {
        return findById(id);
    }

    @Override
    public void updateReply(Reply reply) {
        save(reply);
    }

    @Override
    public void deleteReply(Reply reply) {
        delete(reply);
    }

    @Override
    public List<Reply> findByTopicOrderByUpDownDescDownAscInTimeAsc(Topic topic) {
        Order order = Order.desc("upDown").asc("down").asc("inTime");
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        BaseHibernateDAO.Condition condition = new BaseHibernateDAO.Condition(ConditionType.EQ, "topic", topic);
        conditions.add(condition);
        //不想写函数了，先拿1000扛着
        return findByOrderConditionsPage(0, 1000, order, conditions);
    }

    @Override
    public void deleteByTopic(Topic topic) {
        List<Reply> replies = findBy("topic", topic);
        for(Reply reply: replies){
            delete(reply);
        }
    }

    @Override
    public void deleteByUser(User user) {
        List<Reply> replies = findBy("user", user);
        for(Reply reply: replies){
            delete(reply);
        }
    }

    @Override
    public List<Reply> findByUser(User user, int start, int row, Order order) {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        BaseHibernateDAO.Condition condition = new BaseHibernateDAO.Condition(ConditionType.EQ, "user", user);
        conditions.add(condition);
        return findByOrderConditionsPage(start, row, order, conditions);
    }

    @Override
    public List<Reply> findAll(int start, int row, Order order) {
        return findByOrderPage(order, start, row);
    }

    @Override
    public void deleteById(int id) {
        Reply reply = findById(id);
        delete(reply);
    }
}
