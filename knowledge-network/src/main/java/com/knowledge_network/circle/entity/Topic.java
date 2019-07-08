package com.knowledge_network.circle.entity;

import com.knowledge_network.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by wshish000 on 18-3-3
 */
@Entity
@Table(name = "topic")
public class Topic implements Serializable{
    /*
    文章ID
     */
    private Integer id;
    /*
    文章所属版块
     */
    private String tab;
    /*
    文章标签
     */
    private String labelId;
    /*
    文章标题
     */
    private String title;
    /*
    文章内容
     */
    private String content;
    /*
    发布时间
     */
    private Date inTime;
    /*
    修改时间
     */
    private Date modifyTime;
    /*
    最后回复时间
     */
    private Date lastReplyTime;
    /*
    是否置顶
     */
    private boolean top;
    /*
    是否精华
     */
    private boolean good;
    /*
    浏览数
     */
    private int view;
    /*
    与用户的关联关系
     */
    private User user;
    /*
    回复数
     */
    private int replyCount;
    /*
    点赞用户id，英文逗号隔开
     */
    private String upIds;
    /*
    问题是否被锁定
     */
    private boolean lock;

    private Collection<Collect> Collects = new ArrayList<>();

    private Collection<Notification> notifications = new ArrayList<>();

    private Collection<Reply> replies = new ArrayList<>();

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
    @Column(name = "tab", nullable = false)
    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    @Basic
    @Column(name = "label_id")
    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    @Basic
    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "content")
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
    @Column(name = "modify_time")
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Basic
    @Column(name = "last_reply_time")
    public Date getLastReplyTime() {
        return lastReplyTime;
    }

    public void setLastReplyTime(Date lastReplyTime) {
        this.lastReplyTime = lastReplyTime;
    }

    @Basic
    @Column(name = "top")
    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    @Basic
    @Column(name = "good")
    public boolean isGood() {
        return good;
    }

    public void setGood(boolean good) {
        this.good = good;
    }

    @Basic
    @Column(name = "view", nullable = false)
    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
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
    @Column(name = "reply_count")
    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
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
    @Column(name = "topic_lock")
    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    @OneToMany(targetEntity = Collect.class, cascade = CascadeType.ALL, mappedBy = "topic")
    public Collection<Collect> getCollects() {
        return Collects;
    }

    public void setCollects(Collection<Collect> collects) {
        Collects = collects;
    }

    @OneToMany(targetEntity = Notification.class, cascade = CascadeType.ALL, mappedBy = "topic")
    public Collection<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Collection<Notification> notifications) {
        this.notifications = notifications;
    }

    @OneToMany(targetEntity = Reply.class, cascade = CascadeType.ALL, mappedBy = "user")
    public Collection<Reply> getReplies() {
        return replies;
    }

    public void setReplies(Collection<Reply> replies) {
        this.replies = replies;
    }
}
