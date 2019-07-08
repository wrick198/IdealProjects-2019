package com.knowledge_network.circle.vo;

import com.knowledge_network.circle.entity.Notification;
import com.knowledge_network.user.vo.UserInfoVO;

import java.util.Date;

/**
 * Created by wshish000 on 18-3-8
 */
public class NotificationVO {

    private Integer id;

    private boolean scan;

    private UserInfoVO user;

    private UserInfoVO targetUser;

    private Date inTime;

    private String action;

    private TopicVO topic;

    private String content;

    public NotificationVO(){}

    public NotificationVO(Notification notification){
        id = notification.getId();
        scan = notification.isScan();
        user = new UserInfoVO(notification.getUser());
        targetUser = new UserInfoVO(notification.getTargetUser());
        inTime = notification.getInTime();
        action = notification.getAction();
        topic = new TopicVO(notification.getTopic());
        content = notification.getContent();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isScan() {
        return scan;
    }

    public void setScan(boolean read) {
        this.scan = read;
    }

    public UserInfoVO getUser() {
        return user;
    }

    public void setUser(UserInfoVO user) {
        this.user = user;
    }

    public UserInfoVO getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(UserInfoVO targetUser) {
        this.targetUser = targetUser;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public TopicVO getTopic() {
        return topic;
    }

    public void setTopic(TopicVO topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
