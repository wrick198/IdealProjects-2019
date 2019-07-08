package com.knowledge_network.knowledge_network.vo.web;

/**
 * Created with IntelliJ IDEA.
 * User: wjk
 * Date: 18-1-21
 * Time: 上午10:44
 */
public class AddKnowledgePointRelationshipVO {
    private int knowledgePointid;
    private int knowledgePointRealtionshipType;
    private int tightness;


    public AddKnowledgePointRelationshipVO() {
    }

    public AddKnowledgePointRelationshipVO(int knowledgePointid, int knowledgePointRealtionshipType, int tightness) {
        this.knowledgePointid = knowledgePointid;
        this.knowledgePointRealtionshipType = knowledgePointRealtionshipType;
        this.tightness = tightness;
    }

    public int getKnowledgePointid() {
        return knowledgePointid;
    }

    public void setKnowledgePointid(int knowledgePointid) {
        this.knowledgePointid = knowledgePointid;
    }

    public int getKnowledgePointRealtionshipType() {
        return knowledgePointRealtionshipType;
    }

    public void setKnowledgePointRealtionshipType(int knowledgePointRealtionshipType) {
        this.knowledgePointRealtionshipType = knowledgePointRealtionshipType;
    }

    public int getTightness() {
        return tightness;
    }

    public void setTightness(int tightness) {
        this.tightness = tightness;
    }
}
