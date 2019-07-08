package com.knowledge_network.circle.dao.impl;

import com.knowledge_network.circle.dao.TopicDAO;
import com.knowledge_network.circle.entity.Topic;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.support.utils.StringUtils;
import com.knowledge_network.user.dao.UserDAO;
import com.knowledge_network.user.entity.User;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wshish000 on 18-3-3
 */
@Repository
public class TopicDAOImpl extends BaseHibernateDAO<Topic> implements TopicDAO {

    @Autowired
    private UserDAO userDAO;

    @Override
    public void updateTopic(Topic topic){
        save(topic);
    }

    @Override
    public Topic findTopicById(Integer id) {
        return findById(id);
    }

    @Override
    public List<Topic> findByTab(String tab, int start, int row, Order order) {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        BaseHibernateDAO.Condition condition = new BaseHibernateDAO.Condition(ConditionType.EQ, "tab", tab);
        conditions.add(condition);
        return findByOrderConditionsPage(start, row, order, conditions);
    }

    @Override
    public List<Topic> findByUser(User user, int start, int row, Order order) {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        BaseHibernateDAO.Condition condition = new BaseHibernateDAO.Condition(ConditionType.EQ, "user", user);
        conditions.add(condition);
        return findByOrderConditionsPage(start, row, order, conditions);
    }

    @Override
    public void deleteByUser(User user) {
        List<Topic> topics = findBy("user", user);
        for(Topic topic : topics){
            delete(topic);
        }
    }

    @Override
    public List<Topic> findByGood(boolean b, int start, int row, Order order) {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        BaseHibernateDAO.Condition condition = new BaseHibernateDAO.Condition(ConditionType.EQ, "good", b);
        conditions.add(condition);
        return findByOrderConditionsPage(start, row, order, conditions);
    }

    @Override
    public List<Topic> findByReplyCount(int i, int start, int row, Order order) {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        BaseHibernateDAO.Condition condition = new BaseHibernateDAO.Condition(ConditionType.EQ, "replyCount", i);
        conditions.add(condition);
        return findByOrderConditionsPage(start, row, order, conditions);
    }

    @Override
    //此处是简单写一下，还没完全实现
    //TODO
    public List<Topic> findByTitleContainingOrContentContaining(String title, String content, int start, int row, Order order) {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        BaseHibernateDAO.Condition condition = new BaseHibernateDAO.Condition(ConditionType.LIKE, "title", title);
        conditions.add(condition);
        return findByOrderConditionsPage(start, row, order, conditions);
    }

    @Override
    public long countByInTimeBetween(Date date1, Date date2) {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        BaseHibernateDAO.Condition condition1 = new BaseHibernateDAO.Condition(ConditionType.GE, "modifyTime", date1);
        conditions.add(condition1);
        BaseHibernateDAO.Condition condition2 = new BaseHibernateDAO.Condition(ConditionType.LE, "modifyTime", date2);
        conditions.add(condition2);
        return findCountByConditions(conditions);
    }

    @Override
    public Topic findByTitle(String title) {
        return findByUnique("title", title);
    }

    @Override
    //TODO
    //此处的labelId应该就是label的name，之后再进行确认
    public List<Topic> findByLabelIdLike(String labelId) {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        BaseHibernateDAO.Condition condition = new BaseHibernateDAO.Condition(ConditionType.LIKE, "labelId", labelId);
        conditions.add(condition);
        //不想再写一个函数了，先用1000扛着
        return findByOrderConditionsPage(0, 1000, null, conditions);
    }

    @Override
    //TODO
    //同上
    public List<Topic> findByLabelIdLike(String labelId, int start, int row, Order order) {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        BaseHibernateDAO.Condition condition = new BaseHibernateDAO.Condition(ConditionType.LIKE, "labelId", labelId);
        conditions.add(condition);
        return findByOrderConditionsPage(start, row, order, conditions);
    }

    @Override
    public void deleteTopic(Topic topic){
//        User user = topic.getUser();
//        user.getTopics().remove(topic);
        delete(topic);
    }

    @Override
    public List<Topic> findAll(int start, int row, Order order) {
        return findByOrderPage(order, start, row);
    }
}
