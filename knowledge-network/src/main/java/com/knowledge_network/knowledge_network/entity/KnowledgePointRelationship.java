package com.knowledge_network.knowledge_network.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * # Created by HeartUnderBlade on 2017/12/8
 * 知识点关系的关联表：knowledge_point_has_knowledge_point
 */
@Entity
@Table(name = "knowledge_point_has_knowledge_point")
public class KnowledgePointRelationship implements Serializable {

    public static final int RELATIONSHIP_PARENT_CHILD = 0;
    public static final int RELATIONSHIP_PRE_LATTER_CHILD = 1;

    /**
     * 关联关系的主键
     */
    private KnowledgePointRelationshipPK primaryKey = new KnowledgePointRelationshipPK();

    /**
     * 知识点1
     */
    private KnowledgePoint knowledgePoint1;
    /**
     * 知识点2
     */
    private KnowledgePoint knowledgePoint2;
    /**
     * 知识点1与知识点2的关系：
     * 0：父与子关系
     * 1：前与后的关系
     */
    private Integer knowledgePointRelationshipType;
    /**
     * 知识点1与知识点2的关系紧密度
     */
    private Integer tightness;

    public KnowledgePointRelationship() {
    }

    public KnowledgePointRelationship(KnowledgePoint knowledgePoint1,
                                      KnowledgePoint knowledgePoint2,
                                      Integer knowledgePointRelationshipType,
                                      Integer tightness) {
        this.knowledgePoint1 = knowledgePoint1;
        this.knowledgePoint2 = knowledgePoint2;
        this.knowledgePointRelationshipType = knowledgePointRelationshipType;
        this.tightness = tightness;

        this.primaryKey.knowledgePointId1 = knowledgePoint1.getId();
        this.primaryKey.knowledgePointId2 = knowledgePoint2.getId();

        knowledgePoint1.getKnowledgePointRelationships1().add(this);
        knowledgePoint2.getKnowledgePointRelationships2().add(this);
    }

    @EmbeddedId
    public KnowledgePointRelationshipPK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(KnowledgePointRelationshipPK primaryKey) {
        this.primaryKey = primaryKey;
    }

    @ManyToOne
    @JoinColumn(name = "knowledge_point_id1",
            insertable = false, updatable = false)
    public KnowledgePoint getKnowledgePoint1() {
        return knowledgePoint1;
    }

    public void setKnowledgePoint1(KnowledgePoint knowledgePointId1) {
        this.knowledgePoint1 = knowledgePointId1;
    }

    @ManyToOne
    @JoinColumn(name = "knowledge_point_id2",
            insertable = false, updatable = false)
    public KnowledgePoint getKnowledgePoint2() {
        return knowledgePoint2;
    }

    public void setKnowledgePoint2(KnowledgePoint knowledgePointId2) {
        this.knowledgePoint2 = knowledgePointId2;
    }

    @Basic
    @Column(name = "knowledge_point_relationship_type")
    public Integer getKnowledgePointRelationshipType() {
        return knowledgePointRelationshipType;
    }

    public void setKnowledgePointRelationshipType(Integer type) {
        this.knowledgePointRelationshipType = type;
    }

    @Basic
    @Column(name = "tightness")
    public Integer getTightness() {
        return tightness;
    }

    public void setTightness(Integer tightness) {
        this.tightness = tightness;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KnowledgePointRelationship relation = (KnowledgePointRelationship) o;

        if (!primaryKey.equals(relation.primaryKey)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = primaryKey.hashCode();
        return result;
    }

    /**
     * 主键Primary Key类
     */
    @Embeddable
    public static class KnowledgePointRelationshipPK implements Serializable {

        private Integer knowledgePointId1;
        private Integer knowledgePointId2;

        @Column(name = "knowledge_point_id1", nullable = false)
        public Integer getKnowledgePointId1() {
            return knowledgePointId1;
        }

        public void setKnowledgePointId1(Integer knowledgePointId1) {
            this.knowledgePointId1 = knowledgePointId1;
        }

        @Column(name = "knowledge_point_id2", nullable = false)
        public Integer getKnowledgePointId2() {
            return knowledgePointId2;
        }

        public void setKnowledgePointId2(Integer knowledgePointId2) {
            this.knowledgePointId2 = knowledgePointId2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            KnowledgePointRelationshipPK that = (KnowledgePointRelationshipPK) o;

            if (!knowledgePointId1.equals(that.knowledgePointId1)) return false;
            if (!knowledgePointId2.equals(that.knowledgePointId2)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = knowledgePointId1 * 31 + knowledgePointId2;
            return result;
        }
    }
}