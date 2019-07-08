package com.knowledge_network.knowledge_network.service;

import com.knowledge_network.knowledge_network.entity.Course;
import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.knowledge_network.entity.Resource;
import com.knowledge_network.knowledge_network.entity.ResourceType;
import com.knowledge_network.knowledge_network.vo.ResourceInfoVO;

import java.util.List;
import java.util.Map;

/**
 * Created by wshish000 on 17-12-6
 * 提供资源管理模块相关的服务接口
 */
public interface ResourceService {
    /**
     * 根据资源ID查找资源
     *
     * @param id 资源ID
     * @return 通过资源ID查找获取到的资源
     */
    Resource getResourceById(Integer id);

    /**
     * 根据资源类型查找资源
     *
     * @param resourceType 资源的类型
     * @return 通过类型查找获取到的资源
     */
    List<Resource> getResourceByType(ResourceType resourceType);

    /**
     * 根据资源描述查找资源
     *
     * @param description 资源描述
     * @return 通过描述查找到的资源
     */
    List<Resource> getResourceByDescription(String description);

    /**
     * 根据条件查找资源
     *
     * @param conditions 条件
     * @return 通过条件查找到的资源
     */
    List<Resource> getResourceByConditions(Map<String, String> conditions);

    /**
     * 根据上传人查找资源
     *
     * @param uploader 上传人
     * @return 通过上传人查找到的资源
     */
    List<Resource> getResourceByUploader(String uploader);

    /**
     * 根据知识点查找资源
     *
     * @param knowledgePoint 知识点
     * @return 通过知识点查找到的资源
     */
    List<Resource> getResourceByKnowledgePoint(KnowledgePoint knowledgePoint);

    /**
     * 根据课程查找资源
     *
     * @param course 课程
     * @return 通过课程查找到的资源
     */
    List<Resource> getResourceByCourse(Course course);

    /**
     * 添加资源
     *
     * @param resourceInfoVO 添加资源的信息
     */
    Resource createResource(ResourceInfoVO resourceInfoVO);

    /**
     * 使用ResourceInfoVO对Resource对象进行初始化
     * 其中：downloadUser, markedScoreUser通过另外的接口更新这两个数据
     *
     * @param resource
     * @param resourceInfoVO
     */
    void updateResource(Resource resource, ResourceInfoVO resourceInfoVO);

    /**
     * 修改资源
     *
     * @param resource     想要修改的资源
     * @param resourceInfo 修改后的信息
     */
    void modifyResource(Resource resource, Map<String, String[]> resourceInfo);

    /**
     * 删除资源
     *
     * @param resource 想要删除的资源
     */
    void deleteResource(Resource resource);

    /**
     * 下载资源
     *
     * @param link 资源对应的下载链接
     */
    void downloadResource(String link);
}
