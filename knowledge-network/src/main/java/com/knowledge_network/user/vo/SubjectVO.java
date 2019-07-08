package com.knowledge_network.user.vo;

import com.knowledge_network.knowledge_network.entity.Subject;

/**
 * Created by pentonbin on 18-1-22
 * <p>
 * 科目的页面数据对象
 */
public class SubjectVO {

    private Integer id;
    private String name;

    public SubjectVO() {
    }

    public SubjectVO(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
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
