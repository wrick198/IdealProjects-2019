package com.knowledge_network.knowledge_network.vo;

import com.knowledge_network.knowledge_network.entity.KnowledgePoint;

/**
 * Created with IntelliJ IDEA.
 * User: wjk
 * Date: 18-1-17
 * Time: 下午4:07
 * 此VO用于KNowledgePointInfoVO中的父知识点
 */


public class ParentVO {
    private int id;
    private String name;


    public ParentVO() {

    }

    public ParentVO(KnowledgePoint knowledgePoint) {
        id = knowledgePoint.getId();
        name = knowledgePoint.getName();
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
