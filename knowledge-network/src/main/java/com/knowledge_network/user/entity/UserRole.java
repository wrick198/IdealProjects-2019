package com.knowledge_network.user.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by pentonbin on 17-12-1
 */
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {
    /**
     * 用户角色id
     * 与业务无关，自动生成（递增）
     */
    private Integer id;
    /**
     * 用户角色字段
     */
    private String role;
    /**
     * 用户角色名称
     */
    private String name;
    /**
     * 用户与用户角色的关联关系
     */
    private Collection<UserRoleRelationship> userRoleRelationships = new ArrayList<>();
    /**
     * 当前用户角色包含的用户权限
     */
    private Collection<UserPermission> userPermissions = new ArrayList<>();

    public static final String STUDENT = "STUDENT";
    public static final String PARENT = "PARENT";
    public static final String TEACHER = "TEACHER";
    public static final String KNOWLEDGE_NETWORK_MANAGER = "KNOWLEDGE_NETWORK_MANAGER";
    public static final String SYSTEM_MANAGER = "SYSTEM_MANAGER";

    public UserRole() {
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
    @Column(name = "role", nullable = false, unique = true)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "userRole", cascade = CascadeType.ALL)
    public Collection<UserRoleRelationship> getUserRoleRelationships() {
        return userRoleRelationships;
    }

    public void setUserRoleRelationships(Collection<UserRoleRelationship> userRoleRelationships) {
        this.userRoleRelationships = userRoleRelationships;
    }

    @ManyToMany(targetEntity = UserPermission.class, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role_has_user_permission",
            joinColumns = @JoinColumn(name = "user_role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_permission_id", referencedColumnName = "id")
    )
    public Collection<UserPermission> getUserPermissions() {
        return userPermissions;
    }


    public void setUserPermissions(Collection<UserPermission> userPermissions) {
        this.userPermissions = userPermissions;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        if (id != userRole.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }
}
