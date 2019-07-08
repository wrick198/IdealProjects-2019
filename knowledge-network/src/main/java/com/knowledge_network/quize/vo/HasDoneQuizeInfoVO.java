package com.knowledge_network.quize.vo;

import com.knowledge_network.quize.entity.HasDoneQuize;
import com.knowledge_network.user.vo.StudentInfoVO;

/**
 * ** Created by gongjiangtao on 2018/5/2
 **/
public class HasDoneQuizeInfoVO {
    private StudentInfoVO student;

    private QuizeInfoVO quize;

    private Integer practiceTime;

    private Integer rightTime;

    private Integer wrongTime;

    public HasDoneQuizeInfoVO() {
    }

    public HasDoneQuizeInfoVO(HasDoneQuize hasDoneQuize) {
        this.student = new StudentInfoVO(hasDoneQuize.getStudent(), false);
        this.quize = new QuizeInfoVO(hasDoneQuize.getQuize());
        practiceTime = hasDoneQuize.getPracticeTime();
        rightTime = hasDoneQuize.getRightTime();
        wrongTime = hasDoneQuize.getWrongTime();
    }

    public StudentInfoVO getStudent() {
        return student;
    }

    public void setStudent(StudentInfoVO student) {
        this.student = student;
    }

    public QuizeInfoVO getQuize() {
        return quize;
    }

    public void setQuize(QuizeInfoVO quize) {
        this.quize = quize;
    }

    public Integer getPracticeTime() {
        return practiceTime;
    }

    public void setPracticeTime(Integer practiceTime) {
        this.practiceTime = practiceTime;
    }

    public Integer getRightTime() {
        return rightTime;
    }

    public void setRightTime(Integer rightTime) {
        this.rightTime = rightTime;
    }

    public Integer getWrongTime() {
        return wrongTime;
    }

    public void setWrongTime(Integer wrongTime) {
        this.wrongTime = wrongTime;
    }
}
