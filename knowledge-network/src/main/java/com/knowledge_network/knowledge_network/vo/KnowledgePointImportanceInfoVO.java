package com.knowledge_network.knowledge_network.vo;

import com.knowledge_network.knowledge_network.entity.KnowledgePointImportance;

/**
 * Created with IntelliJ IDEA.
 * User: wjk
 * Date: 18-1-24
 * Time: 下午6:00
 */
public class KnowledgePointImportanceInfoVO {

    private Integer id;
    private String name;

    public KnowledgePointImportanceInfoVO() {

    }

    public KnowledgePointImportanceInfoVO(KnowledgePointImportance knowledgePointImportance) {
        id = knowledgePointImportance.getId();
        name = knowledgePointImportance.getName();
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
