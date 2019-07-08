package com.knowledge_network.knowledge_network.dao;

import com.knowledge_network.knowledge_network.entity.Resource;

import java.util.List;
import java.util.Map;

/**
 * Created by wshish000 on 17-12-6
 * 提供访问资源表resource的DAO
 */
public interface ResourceDAO {
    /**
     * 根据资源ID获取对应的资源
     *
     * @param id 资源的ID
     * @return 查找的资源
     */
    Resource findResourceById(Integer id);

    /**
     * 根据资源名字获取对应的资源
     *
     * @param name 资源的名字
     * @return 查找的资源
     */
    Resource findResourceByName(String name);

    /**
     * 根据资源描述获取对应的资源
     *
     * @param description 资源的描述
     * @return 查找的资源
     */
    List<Resource> findResourceByDescription(String description);

    /**
     * 根据条件查询相应资源并排序
     *
     * @param conditions 条件
     * @return 查找的资源
     */
    List<Resource> findResourceByConditions(Map<String, String> conditions);

    /**
     * 根据上传人ID获取对应的资源
     *
     * @param uploaderId 上传人ID
     * @return 查找的资源
     */
//    List<Resource> findResourceByUploaderId(Integer uploaderId);

    /**
     * 添加一个新的资源
     *
     * @param resource 新增的资源对象
     */
    void addResource(Resource resource);

    /**
     * 更新某个资源
     *
     * @param resource 想要更改的资源对象
     */
    void updateResource(Resource resource);

    /**
     * 删除某个资源
     *
     * @param resource 想要删除的资源对象
     */
    void deleteResource(Resource resource);
}
