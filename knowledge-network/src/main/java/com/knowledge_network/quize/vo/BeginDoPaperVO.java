package com.knowledge_network.quize.vo;

import java.util.Date;

/**
 * ** Created by gongjiangtao on 2018/5/3
 **/
public class BeginDoPaperVO {
    private Integer studentId;

    private Integer paperId;

    private Date startTime;

    public BeginDoPaperVO() {
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
