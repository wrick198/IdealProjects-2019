package com.knowledge_network.knowledge_network.vo;

import com.knowledge_network.knowledge_network.entity.KnowledgePointRelationship;

/**
 * Created with IntelliJ IDEA.
 * User: wjk
 * Date: 17-12-28
 * Time: 下午4:32
 * <p>
 * 绘制知识点地图所需的边
 */
public class KnowledgePointMapEdgeVO {
    private Integer source;
    private Integer target;
    private Integer type;
    private Integer tightness;

    public KnowledgePointMapEdgeVO() {
    }

    public KnowledgePointMapEdgeVO(KnowledgePointRelationship knowledgePointRelationship) {
        this.source = knowledgePointRelationship.getKnowledgePoint1().getId();
        this.target = knowledgePointRelationship.getKnowledgePoint2().getId();
        this.type = knowledgePointRelationship.getKnowledgePointRelationshipType();
        this.tightness = knowledgePointRelationship.getTightness();

    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTightness() {
        return tightness;
    }

    public void setTightness(Integer tightness) {
        this.tightness = tightness;
    }
}
