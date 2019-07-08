package com.knowledge_network.knowledge_network.service.inner;

import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.knowledge_network.entity.KnowledgePointDifficult;

/**
 * Created with IntelliJ IDEA.
 * User: wjk
 * Date: 18-1-21
 * Time: 上午11:57
 */
public interface KnowledgePointDifficultService {
    /**
     *通过ID查找KnowledgePointDifficult
     * @param id
     * @return
     */
    KnowledgePointDifficult getKnowledePointDiffcultByID(int id);
}
