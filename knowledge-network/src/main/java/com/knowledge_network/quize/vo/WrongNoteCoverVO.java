package com.knowledge_network.quize.vo;

import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/4/27
 **/
public class WrongNoteCoverVO {
    private String studentName;

    private List<String> subjectName;

    private List<String> WrongNoteName;

    private List<String> coverUrl;


    public WrongNoteCoverVO() {

    }

    public WrongNoteCoverVO(String studentName, List<String> subjectName, List<String> wrongNoteName, List<String> coverUrl) {
        this.studentName = studentName;
        this.subjectName = subjectName;
        WrongNoteName = wrongNoteName;
        this.coverUrl = coverUrl;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<String> getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(List<String> subjectName) {
        this.subjectName = subjectName;
    }

    public List<String> getWrongNoteName() {
        return WrongNoteName;
    }

    public void setWrongNoteName(List<String> wrongNoteName) {
        WrongNoteName = wrongNoteName;
    }

    public List<String> getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(List<String> coverUrl) {
        this.coverUrl = coverUrl;
    }
}
