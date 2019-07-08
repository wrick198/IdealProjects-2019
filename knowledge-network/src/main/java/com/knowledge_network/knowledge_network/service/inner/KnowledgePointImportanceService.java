package com.knowledge_network.knowledge_network.service.inner;

import com.knowledge_network.knowledge_network.entity.KnowledgePointImportance;

/**
 * Created with IntelliJ IDEA.
 * User: wjk
 * Date: 18-1-21
 * Time: 下午12:03
 */
public interface KnowledgePointImportanceService {
    /**
     * 通过ID查找知识点重要程度
     * @param id
     * @return
     */
    KnowledgePointImportance getImportanceByID(int id);

}
