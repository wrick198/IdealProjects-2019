package com.knowledge_network.knowledge_network.entity;

import com.knowledge_network.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by pentonbin on 17-12-1
 */
@Entity
@Table(name = "resource_clicks")
public class ResourceClicks implements Serializable {

    /**
     * 用户点击资源列表主键
     */
    private ResourceClicksPK primaryKey;
    /**
     * 用户点击资源的次数
     */
    private long count;
    /**
     * 用户
     */
    private User user;
    /**
     * 资源
     */
    private Resource resource;

    public ResourceClicks() {
    }

    @EmbeddedId
    public ResourceClicksPK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(ResourceClicksPK primaryKey) {
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
    @JoinColumn(name = "resource_id",
            insertable = false, updatable = false)
    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceClicks that = (ResourceClicks) o;

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
    public static class ResourceClicksPK implements Serializable {
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
        public int getResourceId() {
            return resourceId;
        }

        public void setResourceId(int resourceId) {
            this.resourceId = resourceId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ResourceClicksPK that = (ResourceClicksPK) o;

            if (!userId.equals(that.userId)) return false;
            if (!resourceId.equals(that.resourceId)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = userId;
            result = 31 * result + resourceId;
            return result;
        }
    }
}
