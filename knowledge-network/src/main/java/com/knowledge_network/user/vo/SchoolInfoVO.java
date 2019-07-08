package com.knowledge_network.user.vo;

import com.knowledge_network.user.entity.School;

/**
 * Created by pentonbin on 17-12-16
 * <p>
 * 学校页面数据对象
 */
public class SchoolInfoVO {

    private Integer id;
    private String name;

    public SchoolInfoVO() {
    }

    public SchoolInfoVO(School school) {
        if(school != null){
            id = school.getId();
            name = school.getName();
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
