package com.knowledge_network.knowledge_network.dao;

import com.knowledge_network.knowledge_network.entity.ResourceType;

/**
 * Created by wshish000 on 17-12-9
 * 提供访问资源类型表resourceType的DAO
 */
public interface ResourceTypeDAO {
    /**
     * 根据资源类型ID获取对应的资源类型
     *
     * @param id 资源类型的ID
     * @return 查找的资源类型
     */
    ResourceType findResourceTypeById(Integer id);

    /**
     * 根据资源类型名字获取对应的资源类型
     *
     * @param name 资源类型的名字
     * @return 查找的资源类型
     */
    ResourceType findResourceTypeByType(String name);

    /**
     * 添加一个新的资源类型
     *
     * @param resourceType 新增的资源类型对象
     */
    void addResourceType(ResourceType resourceType);

    /**
     * 更新某个资源类型
     *
     * @param resourceType 想要更改的资源类型对象
     */
    void updateCourse(ResourceType resourceType);

    /**
     * 删除某个资源类型
     *
     * @param resourceType 想要删除的资源类型对象
     */
    void deleteCourse(ResourceType resourceType);
}
