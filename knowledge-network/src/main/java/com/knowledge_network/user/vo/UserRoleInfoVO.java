package com.knowledge_network.user.vo;

import com.knowledge_network.user.entity.UserRole;

/**
 * Created by pentonbin on 17-12-17
 */
public class UserRoleInfoVO {

    private String role;

    public UserRoleInfoVO() {
    }

    public UserRoleInfoVO(UserRole userRole) {
        if (userRole != null) {
            role = userRole.getRole();
        }
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
