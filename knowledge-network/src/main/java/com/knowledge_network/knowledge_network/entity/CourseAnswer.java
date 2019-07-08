package com.knowledge_network.knowledge_network.entity;

import com.knowledge_network.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
/**
 * 注意: 此表包含问题的回答，还有回答的回复
 */
@Entity
@Table(name = "course_answer")
public class CourseAnswer implements Serializable {

    /**
     * 回答id
     */
    private Integer id;
    /**
     * 创建时间
     */
    private Timestamp publishTime;
    /**
     * 被回复的问题id，即在哪个问题下
     */
    private CourseQuestion question;
    /**
     * 被回复的回答id，即回复哪个回答
     */
    private CourseAnswer answer;
    /**
     * 回复者id
     */
    private User fromUser;
    /**
     * 被回复者id
     */
    private User toUser;
    /**
     * 回复内容
     */
    private String content;
    /**
     * 回复被点赞数
     */
    private Integer thumbCount;
    /**
     * 当前回答的回复列表
     */
    private Collection<CourseAnswer> replyList;

    public CourseAnswer() {
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

    @Basic
    @Column(name = "publish_time")
    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp pubtime) {
        this.publishTime = pubtime;
    }

    @ManyToOne(targetEntity = CourseQuestion.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "question", referencedColumnName = "id")
    public CourseQuestion getQuestion() {
        return question;
    }

    public void setQuestion(CourseQuestion question) {
        this.question = question;
    }

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "from_uid", referencedColumnName = "id")
    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "to_uid", referencedColumnName = "id")
    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ManyToOne(targetEntity = CourseAnswer.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "answer_id", referencedColumnName = "id")
    public CourseAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(CourseAnswer answer) {
        this.answer = answer;
    }

    @OneToMany(targetEntity = CourseAnswer.class, cascade = CascadeType.ALL, mappedBy = "answer")
    public Collection<CourseAnswer> getReplyList() {
        return replyList;
    }

    public void setReplyList(Collection<CourseAnswer> replyList) {
        this.replyList = replyList;
    }
}
