package com.knowledge_network.quize.vo;

import com.knowledge_network.quize.entity.QuestionType;

/**
 * ** Created by gongjiangtao on 2018/4/24
 **/
public class QuestionTypeInfoVO {
    private int id;

    private int type;

    private String name;

    public QuestionTypeInfoVO() {
    }

    public QuestionTypeInfoVO(QuestionType questionType) {
        id = questionType.getId();
        type = questionType.getQuestionType();
        name = questionType.getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
