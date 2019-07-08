package com.knowledge_network.knowledge_network.dao;

import com.knowledge_network.knowledge_network.entity.KnowledgePointDifficult;

import java.util.List;
import java.util.Map;

/**
 * Created by tjk on 17-12-6.
 * <p>
 * 知识点难度管理相关功能服务接口
 */
public interface KnowledgePointDiffcultDAO {
    /**
     * 根据知识点困难程度Level查找知识点困难程度
     *
     * @param id 知识点困难程度ID
     * @return 查找到的知识点困难程度对象
     */
    KnowledgePointDifficult findKnowledgePointDifficultById(int id);

    /**
     * 根据知识点困难程度Level查找知识点困难程度
     *
     * @param level 知识点困难程度Level
     * @return 查找到的知识点困难程度对象
     */

    KnowledgePointDifficult findKnowledgePointDifficultByLevel(int level);


    /**
     * 根据知识点困难程度名称查找知识点困难程度
     *
     * @param name 知识点困难程度
     * @return 查找到的知识点困难程度对象
     */
    KnowledgePointDifficult findKnowledgePointDifficultByName(String name);

    /**
     * 根据知识点困难程度Level更新知识点困难程度
     *
     * @param level 知识点困难程度Level
     */

    void updateKnowledgePointDifficultByLevel(int level, Map<String, String> map);

    /**
     * 根据知识点困难程度名称更新知识点困难程度
     *
     * @param name 知识点困难程度
     */

    void updateKnowledgePointDifficultByName(String name, Map<String, Object> map);

    /**
     * 根据知识点困难程度ID删除知识点困难程度
     *
     * @param id 知识点困难程度ID
     */
    void deleteKnowledgePointDifficultById(int id);

    /**
     * 保存知识点困难程度
     *
     * @param knowledgePointDifficult 要保存的知识点困难程度
     */
    void saveKnowledgePointDiffcult(KnowledgePointDifficult knowledgePointDifficult);
}
