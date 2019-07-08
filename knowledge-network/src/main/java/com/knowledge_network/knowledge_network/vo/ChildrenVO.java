package com.knowledge_network.knowledge_network.vo;

import com.knowledge_network.knowledge_network.entity.KnowledgePoint;

/**
 * Created with IntelliJ IDEA.
 * User: wjk
 * Date: 18-1-17
 * Time: 下午4:09
 * 此VO用于KnowledgePointInfoVO中的子知识点
 */
public class ChildrenVO {
    private int id;
    private String name;

    public ChildrenVO() {

    }

    public ChildrenVO(KnowledgePoint knowledgePoint) {
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
