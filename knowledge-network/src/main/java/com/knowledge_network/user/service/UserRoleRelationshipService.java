package com.knowledge_network.user.service;

import com.knowledge_network.user.entity.UserRoleRelationship;

/**
 * Created by pentonbin on 17-12-26
 * <p>
 * 该接口用来保存、删除用户与角色关系
 */
public interface UserRoleRelationshipService {

    /**
     * 绑定用户与角色的关系
     *
     * @param relationship 关联关系
     */
    void save(UserRoleRelationship relationship);

    /**
     * 删除用户与角色的关系
     *
     * @param relationship 关联关系
     */
    void delete(UserRoleRelationship relationship);
}
