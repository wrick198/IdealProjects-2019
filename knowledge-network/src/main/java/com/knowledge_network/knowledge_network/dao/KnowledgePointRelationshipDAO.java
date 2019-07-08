package com.knowledge_network.knowledge_network.dao;


import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.knowledge_network.entity.KnowledgePointRelationship;

import java.util.List;

/**
 * # Created by HeartUnderBlade on 2017/12/8
 * DAO层知识点关系管理功能接口
 * TODO:更多读取知识点关系的接口？（父、前）
 */
public interface KnowledgePointRelationshipDAO {
    /**
     * 保存知识点关系
     *
     * @param relationship 新知识点关系
     */
    void saveKnowledgePointRelationShip(KnowledgePointRelationship relationship);

    /**
     * 删除知识点关系
     *
     * @param relationship 旧知识点关系
     */
    void deleteKnowledgePointRelationShip(KnowledgePointRelationship relationship);

    /**
     * 查找当前知识点的所有关系
     *
     * @param kp 当前知识点
     * @return 当前知识点所有关系
     */
    List<KnowledgePointRelationship> findAllRelationshipsByPoint(KnowledgePoint kp);

    /**
     * 查找当前知识点的所有子关系
     *
     * @param kp 当前知识点
     * @return 当前知识点所有关系
     */
    List<KnowledgePointRelationship> findSonRelationshipByPoint(KnowledgePoint kp);

    /**
     * 查找当前知识点的所有父关系
     *
     * @param kp 当前知识点
     * @return 当前知识点所有关系
     */
    List<KnowledgePointRelationship> findParentRelationshipByPoint(KnowledgePoint kp);

    /**
     * 查找当前知识点的所有前驱关系
     *
     * @param kp 当前知识点
     * @return 当前知识点所有关系
     */
    List<KnowledgePointRelationship> findForwardRelationshipByPoint(KnowledgePoint kp);

    /**
     * 查找当前知识点的所有后继关系
     *
     * @param kp 当前知识点
     * @return 当前知识点所有关系
     */
    List<KnowledgePointRelationship> findBackwardRelationshipByPoint(KnowledgePoint kp);

    /**
     * 根据详细知识点信息查询单一关系
     *
     * @param kp_1             第一个知识点
     * @param kp_2             第二个知识点
     * @return 知识点关系实体
     */
    KnowledgePointRelationship findAccurateRelationship(KnowledgePoint kp_1, KnowledgePoint kp_2);
}
