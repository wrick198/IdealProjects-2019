package com.knowledge_network.circle.dao;

import com.knowledge_network.circle.entity.Notification;
import com.knowledge_network.circle.entity.Topic;
import com.knowledge_network.user.entity.User;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by wshish000 on 18-3-4
 */
public interface NotificationDAO {

    void updateNotification(Notification notification);

    void deleteNotification(Notification notification);

    List<Notification> findByTargetUser(User targetUser, int start, int row, Order order);

    List<Notification> findByTargetUserAndIsRead(User targetUser, boolean scan, int start, int row, Order order);

    List<Notification> findByTargetUserAndIsRead(User targetUser, boolean scan);

    long countByTargetUserAndIsRead(User targetUser, boolean scan);

    void updateByIsRead(User targetUser);

    void deleteByTargetUser(User targetUser);

    void deleteByUser(User user);

    void deleteByTopic(Topic topic);
}
