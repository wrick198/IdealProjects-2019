package com.knowledge_network.knowledge_network.vo;

import com.knowledge_network.knowledge_network.entity.CourseQuestion;

import java.sql.Timestamp;

public class CourseQuestionInfoVO {
    private Integer id;
    private String title;
    private String content;
    private Integer askerId;
    private Timestamp publishTime;
    private Integer courseId;

    public CourseQuestionInfoVO() {
    }

    public CourseQuestionInfoVO(CourseQuestion courseQuestion) {
        if (courseQuestion != null) {
            id = courseQuestion.getId();
            title = courseQuestion.getTitle();
            content = courseQuestion.getContent();
            askerId = courseQuestion.getAsker().getId();
            publishTime = courseQuestion.getPublishTime();
            courseId = courseQuestion.getCourse().getId();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getAskerId() {
        return askerId;
    }

    public void setAskerId(Integer askerId) {
        this.askerId = askerId;
    }

    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
