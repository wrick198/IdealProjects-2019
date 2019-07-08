package com.knowledge_network.circle.entity;

import com.knowledge_network.user.entity.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wshish000 on 18-3-8
 */
@Entity
@Table(name = "notification")
public class Notification {

    private Integer id;

    private boolean scan;

    private User user;

    private User targetUser;

    private Date inTime;

    private String action;

    private Topic topic;

    private String content;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "scan", nullable = false)
    public boolean isScan() {
        return scan;
    }

    public void setScan(boolean scan) {
        this.scan = scan;
    }


    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "target_user_id", referencedColumnName = "id")
    public User getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(User targetUser) {
        this.targetUser = targetUser;
    }

    @Basic
    @Column(name = "in_time")
    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    @Basic
    @Column(name = "action")
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @ManyToOne(targetEntity = Topic.class)
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
