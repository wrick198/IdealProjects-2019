package com.knowledge_network.knowledge_network.entity;

import com.knowledge_network.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by pentonbin on 18-4-18
 * <p>
 * 用户对课程打分的关联表
 */
@Entity
@Table(name = "user_has_mark_resource_score")
public class UserMarkResourceScoreRelationship implements Serializable {

    /**
     * 关联关系主键
     */
    private UserMarkResourceScoreRelationshipPK primaryKey = new UserMarkResourceScoreRelationshipPK();
    /**
     * 用户
     */
    private User user;
    /**
     * 资源
     */
    private Resource resource;
    /**
     * 用户所打的分数
     */
    private Integer score;

    public UserMarkResourceScoreRelationship() {
    }

    public UserMarkResourceScoreRelationship(User user, Resource resource, Integer score) {
        this.user = user;
        this.resource = resource;
        this.score = score;

        primaryKey.userId = user.getId();
        primaryKey.resourceId = resource.getId();

        this.user.getMarkScoreResource().add(this);
        this.resource.getMarkedScoreUser().add(this);
    }

    @EmbeddedId
    public UserMarkResourceScoreRelationshipPK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(UserMarkResourceScoreRelationshipPK primaryKey) {
        this.primaryKey = primaryKey;
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
    @JoinColumn(name = "resource_id",
            insertable = false, updatable = false)
    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Basic
    @Column(name = "score", nullable = false)
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Embeddable
    public static class UserMarkResourceScoreRelationshipPK implements Serializable {

        private Integer userId;
        private Integer resourceId;

        @Column(name = "user_id", nullable = false)
        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        @Column(name = "resource_id", nullable = false)
        public Integer getResourceId() {
            return resourceId;
        }

        public void setResourceId(Integer resourceId) {
            this.resourceId = resourceId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            UserMarkResourceScoreRelationshipPK that = (UserMarkResourceScoreRelationshipPK) o;

            if (!userId.equals(that.userId)) return false;
            return resourceId.equals(that.resourceId);
        }

        @Override
        public int hashCode() {
            int result = userId.hashCode();
            result = 31 * result + resourceId.hashCode();
            return result;
        }
    }
}
