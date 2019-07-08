package com.knowledge_network.knowledge_network.entity;

import com.knowledge_network.user.entity.*;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "course_question")
public class CourseQuestion implements Serializable{

    /**
     * 问题id
     */
    private Integer id;
    /**
     * 创建者id
     */
    private User asker;
    /**
     * 问题对应课程
     */
    private Course course;
    /**
     * 问题主题
     */
    private String title;
    /**
     * 问题内容(富文本)
     */
    private String content;
    /**
     * 创建时间
     */
    private Timestamp publishTime;
    /**
     * 回答列表
     */
    private Collection<CourseAnswer> answer;

    public CourseQuestion() {
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "asker_id", referencedColumnName = "id")
    public User getAsker() {
        return asker;
    }

    public void setAsker(User asker) {
        this.asker = asker;
    }

    @ManyToOne(targetEntity = Course.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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
    @Column(name = "content", nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "publish_time")
    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp pubtime) {
        this.publishTime = pubtime;
    }

    @OneToMany(targetEntity = CourseAnswer.class, cascade = CascadeType.ALL, mappedBy = "answer")
    public Collection<CourseAnswer> getAnswer() {
        return answer;
    }

    public void setAnswer(Collection<CourseAnswer> answer) {
        this.answer = answer;
    }
}
