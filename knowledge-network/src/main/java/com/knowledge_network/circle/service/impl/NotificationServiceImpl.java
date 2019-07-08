package com.knowledge_network.circle.service.impl;

import com.knowledge_network.circle.dao.NotificationDAO;
import com.knowledge_network.circle.entity.Notification;
import com.knowledge_network.circle.entity.NotificationEnum;
import com.knowledge_network.circle.entity.Topic;
import com.knowledge_network.circle.service.NotificationService;
import com.knowledge_network.user.entity.User;
import com.knowledge_network.user.service.UserService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

/**
 * Created by wshish000 on 18-3-4
 */
@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private NotificationDAO notificationDAO;

    @Autowired
    private UserService userService;

    @Override
    public void save(Notification notification) {
        notificationDAO.updateNotification(notification);
    }

    @Override
    public void sendNotification(User user, Topic topic, String content) {
        //给话题作者发送通知
        if(user.getId() != topic.getUser().getId()){
            this.sendNotification(user, topic.getUser(), NotificationEnum.REPLY.name(), topic, content);
        }
        //TODO
        //给AT用户发送通知
    }

    @Override
    public void sendNotification(User user, User targetUser, String action, Topic topic, String content) {
        new Thread(() -> {
            Notification notification = new Notification();
            notification.setUser(user);
            notification.setTargetUser(targetUser);
            notification.setInTime(new Date());
            notification.setTopic(topic);
            notification.setAction(action);
            notification.setContent(content);
            notification.setScan(false);
            save(notification);
        }).start();
    }

    @Override
    public List<Notification> findByTargetUserAndIsRead(int p, int size, User targetUser, boolean scan) {
        Order order = Order.asc("scan").desc("inTime");
//        if(isRead == null){
//            return notificationDAO.findByTargetUser(targetUser, p, size, order);
//        }
        return notificationDAO.findByTargetUserAndIsRead(targetUser, scan, p, size, order);
    }

    @Override
    public long countByTargetUserAndIsRead(User targetUser, boolean scan) {
        return notificationDAO.countByTargetUserAndIsRead(targetUser, scan);
    }

    @Override
    public List<Notification> findByTargetUserAndIsRead(User targetUser, boolean scan) {
        return notificationDAO.findByTargetUserAndIsRead(targetUser, scan);
    }

    @Override
    public void updateByIsRead(User targetUser) {
        notificationDAO.updateByIsRead(targetUser);
    }

    @Override
    public void deleteByUser(User targetUser) {
        notificationDAO.deleteByTargetUser(targetUser);
    }

    @Override
    public void deleteByTargetUser(User user) {
        notificationDAO.deleteByUser(user);
    }

    @Override
    public void deleteByTopic(Topic topic) {
        notificationDAO.deleteByTopic(topic);
    }
}
