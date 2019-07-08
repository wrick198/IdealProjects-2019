package com.knowledge_network.knowledge_network.vo;

/**
 * # Created by HeartUnderBlade on 2017/12/23
 */
public class KnowledgePointRelationshipVO {

    private Integer knowledgePointId1;
    private Integer knowledgePointId2;
    private Integer knowledgePointRelationshipType;
    private Integer tightness;

    public Integer getKnowledgePointId1() {
        return knowledgePointId1;
    }

    public void setKnowledgePointId1(Integer knowledgePointId1) {
        this.knowledgePointId1 = knowledgePointId1;
    }


    public Integer getKnowledgePointId2() {
        return knowledgePointId2;
    }

    public void setKnowledgePointId2(Integer getKnowledgePointId2) {
        this.knowledgePointId2 = getKnowledgePointId2;
    }


    public Integer getKnowledgePointRelationshipType() {
        return knowledgePointRelationshipType;
    }

    public void setKnowledgePointRelationshipType(int knowledgePointRelationshipType) {
        this.knowledgePointRelationshipType = knowledgePointRelationshipType;
    }

    public Integer getTightness() {
        return tightness;
    }

    public void setTightness(Integer tightness) {
        this.tightness = tightness;
    }
}
