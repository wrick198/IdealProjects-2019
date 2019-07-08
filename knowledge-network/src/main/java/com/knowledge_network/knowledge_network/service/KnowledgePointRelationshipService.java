package com.knowledge_network.knowledge_network.service;

import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.knowledge_network.entity.KnowledgePointRelationship;
import com.knowledge_network.knowledge_network.vo.KnowledgePointRelationshipVO;
import com.knowledge_network.knowledge_network.vo.web.AddKnowledgePointInfoVO;
import com.knowledge_network.knowledge_network.vo.web.AddKnowledgePointRelationshipVO;
import com.knowledge_network.knowledge_network.vo.web.DeleteKnowledgePointRelationshipVO;

import java.util.Collection;
import java.util.Map;

/**
 * Created by wjk on 17-12-6.
 */
public interface KnowledgePointRelationshipService {
    /**
     * 创建知识点之间的关系
     *
     * @param kprInfo 知识点关系信息
     */
    void createKnowledgePointRelationship(KnowledgePointRelationshipVO kprInfo);

    /**
     * 保存知识点关系
     *
     * @param knowledgePointRelationship
     */
    void saveKnowledgePointRelationShip(KnowledgePointRelationship knowledgePointRelationship);

    /**
     * 修改知识点之间的关系
     *
     * @param oldRelation 旧知识点关系实体
     * @param newInfo     新知识点关系
     */
    void modifyKnowledgePointRelationship(KnowledgePointRelationship oldRelation, KnowledgePointRelationshipVO newInfo);

    /**
     * 删除知识点之间的关系
     *
     * @param knowledgePoint_1 第一个知识点
     * @param knowledgePoint_2 第二个知识点
     */
    void deleteKnowledgePointRelationship(int knowledgePoint_1, int knowledgePoint_2);

    /**
     * 根据AddKnowledgePointRelationshipVO 添加知识点关系
     *
     * @param id
     * @param addKnowledgePointInfoVO
     */
    void addKnowledgePointRelationshipByAddKnowledgePointRelationshipVO(int id, AddKnowledgePointInfoVO addKnowledgePointInfoVO);

    /**
     * 根据DeleteKnowledgePointRelationshipVO删除知识点关系
     *
     * @param id
     */
    void deleteKnowledgePointRelationshipByDeleteKnowledgePointRelationshipVOS(int id, Collection<DeleteKnowledgePointRelationshipVO> deleteKnowledgePointRelationshipVOS);
}
