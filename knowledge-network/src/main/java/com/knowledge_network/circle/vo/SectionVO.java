package com.knowledge_network.circle.vo;

import com.knowledge_network.circle.entity.Section;

/**
 * Created by wshish000 on 18-3-8
 */
public class SectionVO {

    private int id;

    private String name;

    public SectionVO(){}

    public SectionVO(Section section){
        if(section != null){
            id = section.getId();
            name = section.getName();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
