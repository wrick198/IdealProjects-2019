package com.knowledge_network.knowledge_network.vo;

import com.knowledge_network.knowledge_network.entity.Tag;

/**
 * # Created by HeartUnderBlade on 2017/12/21
 */
public class TagInfoVO {

    private Integer id;
    private String name;


    public TagInfoVO() {
    }

    public TagInfoVO(Tag tag) {
        if (tag != null) {
            id = tag.getId();
            name = tag.getName();
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
