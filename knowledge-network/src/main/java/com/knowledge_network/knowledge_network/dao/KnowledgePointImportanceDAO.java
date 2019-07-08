package com.knowledge_network.knowledge_network.dao;

import com.knowledge_network.knowledge_network.entity.KnowledgePointImportance;

import java.util.Map;

/**
 * Created by Wjk on 17-12-5.
 * <p>
 * 知识点重要层度管理相关功能服务接口
 */
public interface KnowledgePointImportanceDAO {
    /**
     * 根据知识点重要程度ID查找知识点重要程度
     *
     * @param id 知识点重要程度ID
     * @return 查找到的知识点重要程度对象
     */
    KnowledgePointImportance findKnowledgePointImportanceById(int id);

    /**
     * 根据知识点重要程度Level查找知识点重要程度
     *
     * @param level 知识点重要程度Level
     * @return 查找到的知识点重要程度对象
     */

    KnowledgePointImportance findKnowledgePointImportanceByLevel(int level);


    /**
     * 根据知识点重要程度名称查找知识点重要程度
     *
     * @param name 知识点重要程度
     * @return 查找到的知识点重要程度对象
     */
    KnowledgePointImportance findKnowledgePointImportanceByName(String name);

    /**
     * 根据知识点重要程度Level更新知识点重要程度
     *
     * @param level 知识点重要程度Level
     */

    void updateKnowledgePointImportanceByLevel(int level, Map<String, Object> map);

    /**
     * 根据知识点重要程度名称更新知识点重要程度
     *
     * @param name 知识点重要程度
     */

    void updateKnowledgePointImportanceByName(String name, Map<String, Object> map);

    /**
     * 根据知识点重要程度ID删除知识点重要程度
     *
     * @param id 知识点重要程度ID
     */
    void deleteKnowledgePointImportanceById(int id);

    /**
     * 保存知识点重要程度
     *
     * @param knowledgePointImportance 要保存的知识点重要程度
     */
    void saveKnowledgePointImportance(KnowledgePointImportance knowledgePointImportance);
}
