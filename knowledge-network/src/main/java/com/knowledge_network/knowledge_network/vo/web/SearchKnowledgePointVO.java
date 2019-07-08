package com.knowledge_network.knowledge_network.vo.web;

import com.knowledge_network.knowledge_network.entity.KnowledgePoint;

/**
 * Created with IntelliJ IDEA.
 * User: wjk
 * Date: 18-1-21
 * Time: 下午3:39
 */
public class SearchKnowledgePointVO {
    private Integer id;
    private String name;

    public SearchKnowledgePointVO() {

    }

    public SearchKnowledgePointVO(KnowledgePoint knowledgePoint) {
        this.id = knowledgePoint.getId();
        this.name = knowledgePoint.getName();
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
