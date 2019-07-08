package com.knowledge_network.knowledge_network.service.impl;

import com.knowledge_network.knowledge_network.dao.KnowledgePointDiffcultDAO;
import com.knowledge_network.knowledge_network.entity.KnowledgePointDifficult;
import com.knowledge_network.knowledge_network.service.inner.KnowledgePointDifficultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: wjk
 * Date: 18-1-21
 * Time: 下午12:00
 */
@Service
public class KnowledgPointDifficultServiceImpl implements KnowledgePointDifficultService {
    @Autowired
    private KnowledgePointDiffcultDAO knowledgePointDiffcultDAO;

    @Override
    public KnowledgePointDifficult getKnowledePointDiffcultByID(int id) {
        if (id > 0)
            return knowledgePointDiffcultDAO.findKnowledgePointDifficultById(id);
        else return null;
    }
}
