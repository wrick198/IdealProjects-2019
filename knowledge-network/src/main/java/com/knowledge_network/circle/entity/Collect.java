package com.knowledge_network.circle.entity;

import com.knowledge_network.user.entity.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wshish000 on 18-3-4
 */
@Entity
@Table(name = "collect")
public class Collect {

    private Integer id;

    //与话题的关联关系
    private Topic topic;

    //与用户的关联关系
    private User user;

    private Date inTime;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(targetEntity = Topic.class)
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Basic
    @Column(name = "in_time")
    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }
}
