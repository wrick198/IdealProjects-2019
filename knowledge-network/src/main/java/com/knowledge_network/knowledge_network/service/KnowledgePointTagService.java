package com.knowledge_network.knowledge_network.service;

import com.knowledge_network.knowledge_network.entity.Tag;
import com.knowledge_network.knowledge_network.vo.TagInfoVO;

import java.util.List;
import java.util.Set;

/**
 * Created by wshish000 on 17-12-6
 * 提供知识点标签管理模块相关的服务接口
 */
public interface KnowledgePointTagService {
    /**
     * 获取某个知识点的所有标签
     *
     * @param knowledgePointId 知识点id
     * @return 知识点对应的所有标签
     */
    List<Tag> getKnowledgePointTags(int knowledgePointId);

    /**
     * 为知识点添加标签
     *
     * @param knowledgePointId 想要添加标签的知识点id
     * @param tag              添加的标签集合
     */
    void addKnowledgePointTags(int knowledgePointId, Set<Tag> tag);

    /**
     * 删除指定知识点的标签
     *
     * @param knowledgePointId 指定的知识点
     * @param tag              指定的标签集合
     */
    void deleteKnowledgePointTags(int knowledgePointId, Set<Tag> tag);

    /**
     * 添加标签
     *
     * @param tagInfo 添加标签的信息
     */
    void createTag(TagInfoVO tagInfo);

    /**
     * 根据标签名获取标签
     *
     * @param names 标签名
     * @return 标签实体
     */
    List<Tag> getTagByNames(Set<String> names);

    /**
     * 根据标签名获取标签
     *
     * @param name 标签名
     * @return 标签实体
     */
    Tag getTagByName(String name);

    /**
     * 更新标签
     *
     * @param oldTag 要更新的tag实体
     * @param tagInfo 更新后的标签信息
     */
    void updateTag(Tag oldTag,TagInfoVO tagInfo);

    /**
     * 删除标签
     *
     * @param tagId 想要删除的标签对象
     */
    void deleteTag(int tagId);
}
