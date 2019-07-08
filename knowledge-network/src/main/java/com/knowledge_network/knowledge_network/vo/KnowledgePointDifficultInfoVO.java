package com.knowledge_network.knowledge_network.vo;

import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.knowledge_network.entity.KnowledgePointDifficult;

/**
 * Created with IntelliJ IDEA.
 * User: wjk
 * Date: 18-1-24
 * Time: 下午5:55
 */
public class KnowledgePointDifficultInfoVO {
    private Integer id;
    private String name;

    public KnowledgePointDifficultInfoVO() {

    }

    public KnowledgePointDifficultInfoVO(KnowledgePointDifficult knowledgePointDifficult) {
        id = knowledgePointDifficult.getId();
        name = knowledgePointDifficult.getName();
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
