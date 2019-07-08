package com.knowledge_network.knowledge_network.vo;

import com.knowledge_network.knowledge_network.entity.CourseAnswer;

import java.sql.Timestamp;

public class CourseAnswerInfoVO {
    private Integer id;
    private String content;
    private Timestamp publishTime;
    private Integer toAnswerId;
    private String fromUsername;
    private String toUsername;

    public CourseAnswerInfoVO() {
    }

    public CourseAnswerInfoVO(CourseAnswer courseAnswer) {
        if (courseAnswer != null) {
            id = courseAnswer.getId();
            content = courseAnswer.getContent();
            publishTime = courseAnswer.getPublishTime();
            toAnswerId = (courseAnswer.getAnswer() == null ? null : courseAnswer.getAnswer().getId());
            fromUsername = courseAnswer.getFromUser().getUsername();
            toUsername = courseAnswer.getToUser().getUsername();
        }

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

    public Integer getToAnswerId() {
        return toAnswerId;
    }

    public void setToAnswerId(Integer toAnswerId) {
        this.toAnswerId = toAnswerId;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }
}
