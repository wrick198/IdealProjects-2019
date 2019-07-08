package com.knowledge_network.quize.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * ** Created by gongjiangtao on 2018/5/2
 * 学生提交的答案实体类
 **/
@Entity
@Table(name = "student_answer")
public class StudentAnswer implements Serializable {
    /**
     * id主键
     */
    private Integer id;
    /**
     * 答案
     */
    private String answer;
    /**
     * 答案得分
     */
    private Double earnedScore = 0.0;
    /**
     * 与答题卡的多对一关联
     */
    private AnswerSheet answerSheet;
    /**
     * 与题目的多对一关联
     */
    private Quize quize;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "answer", nullable = false)
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Column(name = "earned_score", nullable = false)
    public Double getEarnedScore() {
        return earnedScore;
    }

    public void setEarnedScore(Double earnedScore) {
        this.earnedScore = earnedScore;
    }

    @ManyToOne
    @JoinColumn(name = "answer_sheet_id", nullable = false)
    public AnswerSheet getAnswerSheet() {
        return answerSheet;
    }

    public void setAnswerSheet(AnswerSheet answerSheet) {
        this.answerSheet = answerSheet;
    }

    @ManyToOne
    @JoinColumn(name = "quize_id", nullable = false)
    public Quize getQuize() {
        return quize;
    }

    public void setQuize(Quize quize) {
        this.quize = quize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentAnswer that = (StudentAnswer) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(answer, that.answer) &&
                Objects.equals(earnedScore, that.earnedScore);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, answer, earnedScore);
    }
}
