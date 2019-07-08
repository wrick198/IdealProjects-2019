package com.knowledge_network.circle.vo;

import com.knowledge_network.circle.entity.Reply;
import com.knowledge_network.user.vo.UserInfoVO;

import java.util.Date;

/**
 * Created by wshish000 on 18-3-9
 */
public class ReplyVO {

    private Integer id;

    private String content;

    private Date inTime;

    private int up;

    private int down;

    private int upDown;

    private TopicVO topicVO;

    private UserInfoVO userInfoVO;

    private String upIds;

    private String downIds;

    public ReplyVO(){}

    public ReplyVO(Reply reply){
        id = reply.getId();
        content = reply.getContent();
        inTime = reply.getInTime();
        up = reply.getUp();
        down = reply.getDown();
        upDown = reply.getUpDown();
        topicVO = new TopicVO(reply.getTopic());
        userInfoVO = new UserInfoVO(reply.getUser());
        upIds = reply.getUpIds();
        downIds = reply.getDownIds();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public int getUpDown() {
        return upDown;
    }

    public void setUpDown(int upDown) {
        this.upDown = upDown;
    }

    public TopicVO getTopicVO() {
        return topicVO;
    }

    public void setTopicVO(TopicVO topicVO) {
        this.topicVO = topicVO;
    }

    public UserInfoVO getUserInfoVO() {
        return userInfoVO;
    }

    public void setUserInfoVO(UserInfoVO userInfoVO) {
        this.userInfoVO = userInfoVO;
    }

    public String getUpIds() {
        return upIds;
    }

    public void setUpIds(String upIds) {
        this.upIds = upIds;
    }

    public String getDownIds() {
        return downIds;
    }

    public void setDownIds(String downIds) {
        this.downIds = downIds;
    }
}
