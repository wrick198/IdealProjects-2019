package com.knowledge_network.user.service.impl;

import com.knowledge_network.support.utils.StringUtils;
import com.knowledge_network.user.dao.UserPermissionDAO;
import com.knowledge_network.user.dao.UserRoleDAO;
import com.knowledge_network.user.entity.UserPermission;
import com.knowledge_network.user.entity.UserRole;
import com.knowledge_network.user.service.inner.InnerUserRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by pentonbin on 17-12-4
 */
@Service
public class InnerUserRolePermissionServiceImpl implements InnerUserRolePermissionService {

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Autowired
    private UserPermissionDAO userPermissionDAO;

    @Override
    public boolean userRoleHasPermission(UserRole role, String permission) {
        if (role != null && !StringUtils.isNullOrEmpty(permission)) {
            Collection<UserPermission> permissions = role.getUserPermissions();
            for (UserPermission userPermission : permissions) {
                if (userPermission.getPermission().equalsIgnoreCase(permission)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Collection<UserRole> getAllUserRoles() {
        return userRoleDAO.findAllUserRoles();
    }

    @Override
    public Collection<UserPermission> getAllUserPermissions() {
        return userPermissionDAO.findAllUserPermissions();
    }

    @Override
    public boolean containPermission(String permission) {
        Collection<UserPermission> userPermissions = getAllUserPermissions();
        if (userPermissions != null) {
            for (UserPermission userPermission : userPermissions) {
                if (userPermission.getPermission().equalsIgnoreCase(permission)) {
                    return true;
                }
            }
        }
        return false;
    }
}
