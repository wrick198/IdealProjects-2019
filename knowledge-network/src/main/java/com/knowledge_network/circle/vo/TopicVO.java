package com.knowledge_network.circle.vo;

import com.knowledge_network.circle.entity.Collect;
import com.knowledge_network.circle.entity.Topic;
import com.knowledge_network.user.entity.User;
import com.knowledge_network.user.vo.UserInfoVO;

import java.util.Date;

/**
 * Created by wshish000 on 18-3-6
 */
public class TopicVO {

    private Integer id;

    private String tab;

    private String labelId;

    private String title;

    private String content;

    private Date inTime;

    private Date modifyTime;

    private Date lastReplyTime;

    private boolean top;

    private boolean good;

    private int view;

    private UserInfoVO userInfoVO;

    private int replyCount;

    private String upIds;

    private boolean lock;

    private CollectVO collectVO;

    private int collectCount;

    public TopicVO(){}

    public TopicVO(Topic topic){
        if(topic != null){
            id = topic.getId();
            tab = topic.getTab();
            labelId = topic.getLabelId();
            title = topic.getTitle();
            content = topic.getContent();
            inTime = topic.getInTime();
            modifyTime = topic.getModifyTime();
            lastReplyTime = topic.getLastReplyTime();
            top = topic.isTop();
            good = topic.isGood();
            view = topic.getView();
            userInfoVO = new UserInfoVO(topic.getUser());
            replyCount = topic.getReplyCount();
            upIds = topic.getUpIds();
            lock = topic.isLock();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getLastReplyTime() {
        return lastReplyTime;
    }

    public void setLastReplyTime(Date lastReplyTime) {
        this.lastReplyTime = lastReplyTime;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public boolean isGood() {
        return good;
    }

    public void setGood(boolean good) {
        this.good = good;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public UserInfoVO getUser() {
        return userInfoVO;
    }

    public void setUser(UserInfoVO userInfoVO) {
        this.userInfoVO = userInfoVO;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getUpIds() {
        return upIds;
    }

    public void setUpIds(String upIds) {
        this.upIds = upIds;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public CollectVO getCollectVO() {
        return collectVO;
    }

    public void setCollectVO(CollectVO collectVO) {
        this.collectVO = collectVO;
    }

    public int getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }
}
