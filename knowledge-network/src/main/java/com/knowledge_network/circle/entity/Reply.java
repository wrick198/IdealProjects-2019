package com.knowledge_network.circle.entity;

import com.knowledge_network.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by wshish000 on 18-3-4
 */
@Entity
@Table(name = "reply")
public class Reply implements Serializable {

    //回复的id
    private Integer id;

    //回复的内容
    private String content;

    //回复时间
    private Date inTime;

    //点赞个数
    private int up;

    //踩的个数
    private int down;

    private int upDown;

    //与话题的关联关系
    private Topic topic;

    //与用户的关联关系
    private User user;

    //对回复点赞的用户id，逗号隔开(英文逗号)
    private String upIds;

    //对回复点踩的用户id，逗号隔开(英文逗号)
    private String downIds;


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
    @Column(name = "content", nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "in_time", nullable = false)
    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    @Basic
    @Column(name = "up", nullable = false)
    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    @Basic
    @Column(name = "down", nullable = false)
    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    @Basic
    @Column(name = "updown", nullable = false)
    public int getUpDown() {
        return upDown;
    }

    public void setUpDown(int upDown) {
        this.upDown = upDown;
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
    @Column(name = "up_ids")
    public String getUpIds() {
        return upIds;
    }

    public void setUpIds(String upIds) {
        this.upIds = upIds;
    }

    @Basic
    @Column(name = "down_ids")
    public String getDownIds() {
        return downIds;
    }

    public void setDownIds(String downIds) {
        this.downIds = downIds;
    }

}
