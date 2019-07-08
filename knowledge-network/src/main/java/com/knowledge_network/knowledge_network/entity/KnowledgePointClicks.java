package com.knowledge_network.knowledge_network.entity;

import com.knowledge_network.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by pentonbin on 17-12-1
 */
@Entity
@Table(name = "knowledge_point_clicks")
public class KnowledgePointClicks implements Serializable {

    /**
     * 用户点击知识点列表主键
     */
    private KnowledgePointClicksPK primaryKey;
    /**
     * 用户点击知识点的次数
     */
    private long count;
    /**
     * 用户
     */
    private User user;
    /**
     * 知识点
     */
    private KnowledgePoint knowledgePoint;

    public KnowledgePointClicks() {
    }

    @EmbeddedId
    public KnowledgePointClicksPK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(KnowledgePointClicksPK primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Basic
    @Column(name = "count", nullable = false)
    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @ManyToOne
    @JoinColumn(name = "user_id",
            insertable = false, updatable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "knowledge_point_id",
            insertable = false, updatable = false)
    public KnowledgePoint getKnowledgePoint() {
        return knowledgePoint;
    }

    public void setKnowledgePoint(KnowledgePoint knowledgePoint) {
        this.knowledgePoint = knowledgePoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KnowledgePointClicks that = (KnowledgePointClicks) o;

        if (!getPrimaryKey().equals(that.getPrimaryKey())) {
            return false;
        }

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
    public static class KnowledgePointClicksPK implements Serializable {

        private Integer userId;
        private Integer knowledgePointId;

        @Column(name = "user_id", nullable = false)
        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        @Column(name = "knowledge_point_id", nullable = false)
        public Integer getKnowledgePointId() {
            return knowledgePointId;
        }

        public void setKnowledgePointId(Integer knowledgePointId) {
            this.knowledgePointId = knowledgePointId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            KnowledgePointClicksPK that = (KnowledgePointClicksPK) o;

            if (!userId.equals(that.userId)) return false;
            if (!knowledgePointId.equals(that.knowledgePointId)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = userId;
            result = 31 * result + knowledgePointId;
            return result;
        }
    }
}
