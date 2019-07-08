package com.knowledge_network.user.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by pentonbin on 17-12-1
 */
@Entity
@Table(name = "user_permission")
public class UserPermission implements Serializable {
    /**
     * 用户权限id
     * 与业务无关，自动生成（递增）
     */
    private Integer id;
    /**
     * 用户权限字段
     */
    private String permission;
    /**
     * 包含该用户权限的用户角色
     */
    private Collection<UserRole> userRoles = new ArrayList<>();

    public UserPermission() {
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "permission", nullable = false, unique = true)
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @ManyToMany(mappedBy = "userPermissions")
    public Collection<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Collection<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPermission that = (UserPermission) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }
}
