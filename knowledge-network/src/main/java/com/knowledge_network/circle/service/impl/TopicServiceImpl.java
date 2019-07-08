package com.knowledge_network.circle.service.impl;

import com.knowledge_network.circle.dao.TopicDAO;
import com.knowledge_network.circle.entity.Topic;
import com.knowledge_network.circle.service.*;
import com.knowledge_network.support.utils.StringUtils;
import com.knowledge_network.user.entity.User;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by wshish000 on 18-3-5
 */
@Service
public class TopicServiceImpl implements TopicService{

    @Autowired
    private TopicDAO topicDAO;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private CollectService collectService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private LabelService labelService;

    @Override
    public List<Topic> fuzzyTopicByLabel(int labelId) {
        return topicDAO.findByLabelIdLike("" + labelId);
    }

    @Override
    public void save(Topic topic) {
        topicDAO.updateTopic(topic);
    }

    @Override
    public Topic findById(int id) {
        return topicDAO.findTopicById(id);
    }

    @Override
    public void deleteById(int id) {
        Topic topic = findById(id);
        if(topic != null){
            //删除收藏这个话题的记录
            collectService.deleteByTopic(topic);
            //删除通知里提到的话题
            notificationService.deleteByTopic(topic);
            //删除话题下面的回复
            replyService.deleteByTopic(topic);
            //处理标签的话题计数
            if(!StringUtils.isNullOrEmpty(topic.getLabelId())){
                labelService.dealEditTopicOldLabels(topic.getLabelId());
            }
            //删除话题
            topicDAO.deleteTopic(topic);
        }
    }

    @Override
    public void deleteByUser(User user) {
        topicDAO.deleteByUser(user);
    }

    @Override
    public List<Topic> page(int p, int size, String tab, boolean lastest, Integer labelId) {
        Order order;
        if(lastest){
            order = Order.desc("inTime");
        }
        else{
            order = Order.desc("top").desc("inTime").desc("lastReplyTime");
        }
        if(labelId == null){
            switch(tab){
                case "all":
                    return topicDAO.findAll(p, size, order);
                case "good":
                    return topicDAO.findByGood(true, p, size, order);
                case "wait":
                    return topicDAO.findByReplyCount(0, p, size, order);
                default:
                    return topicDAO.findByTab(tab, p, size, order);
            }
        }
        else{
            return topicDAO.findByLabelIdLike("" + labelId, p, size, order);
        }
    }

    @Override
    public List<Topic> search(int p, int size, String q) {
        if(StringUtils.isNullOrEmpty(q)){
            return null;
        }
        Order order = Order.desc("inTime");
        return topicDAO.findByTitleContainingOrContentContaining(q, q, p, size, order);
    }

    @Override
    public void addOneReplyCount(int topicId) {
        Topic topic = findById(topicId);
        if(topic != null){
            topic.setReplyCount(topic.getReplyCount() + 1);
            save(topic);
        }
    }

    @Override
    public void reduceOneReplyCount(int topicId) {
        Topic topic = findById(topicId);
        if(topic != null){
            topic.setReplyCount(topic.getReplyCount() - 1);
            save(topic);
        }
    }

    @Override
    public List<Topic> findByUser(int p, int size, User user) {
        Order order = Order.desc("inTime");
        return topicDAO.findByUser(user, p, size, order);
    }

    @Override
    public long countByInTimeBetween(Date date1, Date date2) {
        return topicDAO.countByInTimeBetween(date1, date2);
    }

    @Override
    public Topic findByTitle(String title) {
        return topicDAO.findByTitle(title);
    }
}
