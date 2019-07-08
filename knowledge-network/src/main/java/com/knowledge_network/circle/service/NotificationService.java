package com.knowledge_network.circle.service;

import com.knowledge_network.circle.entity.Notification;
import com.knowledge_network.circle.entity.Topic;
import com.knowledge_network.user.entity.User;

import java.util.List;

/**
 * Created by wshish000 on 18-3-4
 */
public interface NotificationService {

    /**
     * 保存通知
     *
     * @param notification
     */
    void save(Notification notification);

    void sendNotification(User user, Topic topic, String content);

    /**
     * 发送通知
     *
     * @param user
     * @param targetUser
     * @param action
     * @param topic
     * @param content
     */
    void sendNotification(User user, User targetUser, String action, Topic topic, String content);

    /**
     * 根据用户查询通知
     *
     * @param p
     * @param size
     * @param targetUser
     * @param scan
     * @return
     */
    List<Notification> findByTargetUserAndIsRead(int p, int size, User targetUser, boolean scan);

    /**
     * 根据用户查询已读/未读的通知
     *
     * @param targetUser
     * @param scan
     * @return
     */
    long countByTargetUserAndIsRead(User targetUser, boolean scan);

    /**
     * 根据阅读状态查询通知
     *
     * @param targetUser
     * @param scan
     * @return
     */
    List<Notification> findByTargetUserAndIsRead(User targetUser, boolean scan);

    /**
     * 批量更新通知的状态
     *
     * @param targetUser
     */
    void updateByIsRead(User targetUser);

    /**
     * 删除用户的通知
     *
     * @param user
     */
    void deleteByUser(User user);

    /**
     * 删除目标用户的通知
     *
     * @param user
     */
    void deleteByTargetUser(User user);

    /**
     * 删除指定话题下的通知
     * @param topic
     */
    void deleteByTopic(Topic topic);
}
