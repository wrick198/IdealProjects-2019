package com.knowledge_network.knowledge_network.service;

import com.knowledge_network.knowledge_network.entity.ResourceType;

/**
 * Created by pentonbin on 18-4-18
 */
public interface ResourceTypeService {
    /**
     * 根据id获取资源类型
     *
     * @param id
     * @return
     */
    ResourceType getResourceTypeById(Integer id);

    /**
     * 根据类型名称获取资源类型
     *
     * @param type 类型名称
     * @return
     */
    ResourceType getResourceTypeByType(String type);
}
