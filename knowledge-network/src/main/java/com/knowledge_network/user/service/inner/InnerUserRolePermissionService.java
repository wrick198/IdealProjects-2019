package com.knowledge_network.user.service.inner;

import com.knowledge_network.user.entity.UserPermission;
import com.knowledge_network.user.entity.UserRole;

import java.util.Collection;

/**
 * Created by pentonbin on 17-12-4
 * <p>
 * 内部服务接口：用户角色权限服务接口
 */
public interface InnerUserRolePermissionService {

    /**
     * 判断用户角色UserRole是否包含某个权限UserPermission
     *
     * @param role       用户角色UserRole
     * @param permission 用户权限permission字段
     * @return true则包含，否则为false
     */
    boolean userRoleHasPermission(UserRole role, String permission);

    /**
     * 获取系统所有的用户角色
     *
     * @return 系统所有用户角色
     */
    public Collection<UserRole> getAllUserRoles();

    /**
     * 获取系统所有的权限
     *
     * @return 系统所有的权限
     */
    public Collection<UserPermission> getAllUserPermissions();

    /**
     * 判断系统是否存在某个权限
     *
     * @param permission 判断的权限
     * @return true表示存在该权限
     */
    public boolean containPermission(String permission);

}
