package com.knowledge_network.circle.vo;

import com.knowledge_network.circle.entity.Collect;
import com.knowledge_network.user.vo.UserInfoVO;

import java.util.Date;

/**
 * Created by wshish000 on 18-3-7
 */
public class CollectVO {

    private Integer id;

    private TopicVO topicVO;

    private UserInfoVO userInfoVO;

    private Date inTime;

    public CollectVO(){}

    public CollectVO(Collect collect){
        if(collect != null) {
            id = collect.getId();
            topicVO = new TopicVO(collect.getTopic());
            userInfoVO = new UserInfoVO(collect.getUser());
            inTime = collect.getInTime();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }
}
