package com.knowledge_network.knowledge_network.vo;

import com.knowledge_network.knowledge_network.entity.Subject;

/**
 * Created by pentonbin on 17-12-16
 * <p>
 * 科目信息页面数据对象
 */
public class SubjectInfoVO {

    private Integer id;
    private String name;

    public SubjectInfoVO() {
    }

    public SubjectInfoVO(Subject subject) {
        if (subject != null) {
            id = subject.getId();
            name = subject.getName();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
