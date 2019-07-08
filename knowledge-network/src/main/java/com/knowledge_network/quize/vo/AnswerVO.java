package com.knowledge_network.quize.vo;

/**
 * ** Created by gongjiangtao on 2018/4/25
 **/
public class AnswerVO {
    private Integer quizeId;

    private Integer studentId;

    private String answer;

    public AnswerVO(Integer quizeId, String answer) {
        this.quizeId = quizeId;
        this.answer = answer;
    }

    public Integer getQuizeId() {
        return quizeId;
    }

    public void setQuizeId(Integer quizeId) {
        this.quizeId = quizeId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
