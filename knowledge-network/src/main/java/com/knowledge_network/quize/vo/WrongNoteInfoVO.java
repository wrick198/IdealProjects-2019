package com.knowledge_network.quize.vo;

import com.knowledge_network.knowledge_network.vo.SubjectInfoVO;
import com.knowledge_network.quize.entity.WrongNote;
import com.knowledge_network.user.vo.StudentInfoVO;

/**
 * ** Created by gongjiangtao on 2018/4/27
 **/
public class WrongNoteInfoVO {
    private StudentInfoVO student;

    private SubjectInfoVO subject;

    private QuizeInfoVO quize;

    private String name;

    public WrongNoteInfoVO() {
    }

    public WrongNoteInfoVO(WrongNote wrongNote, String name) {
        this.student = new StudentInfoVO(wrongNote.getStudent(), false);
        this.subject = new SubjectInfoVO(wrongNote.getSubject());
        this.quize = new QuizeInfoVO(wrongNote.getQuize());
        this.name = name;
    }

    public StudentInfoVO getStudent() {
        return student;
    }

    public void setStudent(StudentInfoVO student) {
        this.student = student;
    }

    public SubjectInfoVO getSubject() {
        return subject;
    }

    public void setSubject(SubjectInfoVO subject) {
        this.subject = subject;
    }

    public QuizeInfoVO getQuize() {
        return quize;
    }

    public void setQuize(QuizeInfoVO quize) {
        this.quize = quize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
