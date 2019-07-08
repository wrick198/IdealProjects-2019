package com.knowledge_network.knowledge_network.vo;

import com.knowledge_network.knowledge_network.entity.CourseEvaluate;
import com.knowledge_network.user.vo.UserInfoVO;

import java.sql.Timestamp;

public class CourseEvaluateInfoVO {
    private Integer id;
    private String content;
    private Timestamp publishTime;
    private Float rate;
    private Integer thumbCount;
    private Integer userId;
    private UserInfoVO user;
    private Integer courseId;

    public CourseEvaluateInfoVO() {
    }

    public CourseEvaluateInfoVO(CourseEvaluate courseEvaluate) {
        if (courseEvaluate != null) {
            id = courseEvaluate.getId();
            content = courseEvaluate.getContent();
            publishTime = courseEvaluate.getPublishTime();
            rate = courseEvaluate.getRate();
            thumbCount = courseEvaluate.getThumbCount();
            userId = courseEvaluate.getUser().getId();
            user = new UserInfoVO(courseEvaluate.getUser());
            courseId = courseEvaluate.getCourse().getId();
        }
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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

    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public Integer getThumbCount() {
        return thumbCount;
    }

    public void setThumbCount(Integer thumbCount) {
        this.thumbCount = thumbCount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer user) {
        this.userId = user;
    }

    public UserInfoVO getUser() {
        return user;
    }

    public void setUser(UserInfoVO user) {
        this.user = user;
    }
}
