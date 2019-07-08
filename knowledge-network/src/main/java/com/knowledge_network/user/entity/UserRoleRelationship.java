package com.knowledge_network.user.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by pentonbin on 17-12-13
 * <p>
 * 用户与用户角色关联表：user_has_user_role
 */
@Entity
@Table(name = "user_has_user_role")
public class UserRoleRelationship implements Serializable {

    /**
     * 非初始化用户角色（额外用户角色）
     */
    public static final int USER_ROLE_NON_INITIAL_ROLE = 0;
    /**
     * 初始化用户角色
     */
    public static final int USER_ROLE_INITIAL_ROLE = 1;

    /**
     * 用户与用户角色的关联关系主键
     */
    private UserRoleRelationshipPK primaryKey = new UserRoleRelationshipPK();
    /**
     * 用户
     */
    private User user;
    /**
     * 用户角色
     */
    private UserRole userRole;
    /**
     * 是否为初始化角色
     * 0：非初始化角色。见{@link UserRoleRelationship#USER_ROLE_NON_INITIAL_ROLE}
     * 1：初始化角色。见{@link UserRoleRelationship#USER_ROLE_INITIAL_ROLE}
     */
    private Integer initialRole;

    public UserRoleRelationship() {
    }

    public UserRoleRelationship(User user, UserRole userRole, Integer initialRole) {
        this.user = user;
        this.userRole = userRole;
        this.initialRole = initialRole;

        this.primaryKey.userId = user.getId();
        this.primaryKey.userRoleId = userRole.getId();

        user.getUserRoleRelationships().add(this);
        userRole.getUserRoleRelationships().add(this);
    }

    @EmbeddedId
    public UserRoleRelationshipPK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(UserRoleRelationshipPK primaryKey) {
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
    @JoinColumn(name = "user_role_id",
            insertable = false, updatable = false)
    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Basic
    @Column(name = "initial_role", nullable = false)
    public Integer getInitialRole() {
        return initialRole;
    }

    public void setInitialRole(Integer initialRole) {
        this.initialRole = initialRole;
    }

    @Embeddable
    public static class UserRoleRelationshipPK implements Serializable {

        private Integer userId;
        private Integer userRoleId;

        @Column(name = "user_id", nullable = false)
        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        @Column(name = "user_role_id", nullable = false)
        public Integer getUserRoleId() {
            return userRoleId;
        }

        public void setUserRoleId(Integer userRoleId) {
            this.userRoleId = userRoleId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            UserRoleRelationshipPK that = (UserRoleRelationshipPK) o;

            if (!userId.equals(that.userId)) return false;
            return userRoleId.equals(that.userRoleId);
        }

        @Override
        public int hashCode() {
            int result = userId.hashCode();
            result = 31 * result + userRoleId.hashCode();
            return result;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRoleRelationship that = (UserRoleRelationship) o;

        return primaryKey.equals(that.primaryKey);
    }

    @Override
    public int hashCode() {
        return primaryKey.hashCode();
    }
}
