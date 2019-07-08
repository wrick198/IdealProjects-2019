package com.knowledge_network.quize.vo;

import com.knowledge_network.quize.entity.StudentAnswer;

/**
 * ** Created by gongjiangtao on 2018/5/3
 **/
public class StudentAnswerInfoVO {
    private Integer id;

    private String answer;

    private Double earnedScore;

    private QuizeInfoVO quize;

    public StudentAnswerInfoVO() {

    }

    public StudentAnswerInfoVO(StudentAnswer studentAnswer) {
        this.id = studentAnswer.getId();
        this.answer = studentAnswer.getAnswer();
        this.quize = new QuizeInfoVO(studentAnswer.getQuize());
        this.earnedScore = studentAnswer.getEarnedScore();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Double getEarnedScore() {
        return earnedScore;
    }

    public void setEarnedScore(Double earnedScore) {
        this.earnedScore = earnedScore;
    }

    public QuizeInfoVO getQuize() {
        return quize;
    }

    public void setQuize(QuizeInfoVO quize) {
        this.quize = quize;
    }
}
