package com.knowledge_network.user.dao;

import com.knowledge_network.user.entity.UserRoleRelationship;

/**
 * Created by pentonbin on 17-12-26
 */
public interface UserRoleRelationshipDAO {

    /**
     * 保存用户与角色的关联关系
     *
     * @param relationship 关联关系
     */
    void persist(UserRoleRelationship relationship);

    /**
     * 删除用户与角色的关联主键
     *
     * @param relationship 关联关系
     */
    void delete(UserRoleRelationship relationship);
}
