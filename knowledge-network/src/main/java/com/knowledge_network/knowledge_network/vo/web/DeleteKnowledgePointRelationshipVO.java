package com.knowledge_network.knowledge_network.vo.web;

/**
 * Created with IntelliJ IDEA.
 * User: wjk
 * Date: 18-1-21
 * Time: 下午9:57
 */
public class DeleteKnowledgePointRelationshipVO {
    private int knowledgePointid;
    private int knowledgePointRealtionshipType;


    public DeleteKnowledgePointRelationshipVO() {
    }

    public DeleteKnowledgePointRelationshipVO(int knowledgePointid, int knowledgePointRealtionshipType) {
        this.knowledgePointid = knowledgePointid;
        this.knowledgePointRealtionshipType = knowledgePointRealtionshipType;
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

}
