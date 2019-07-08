package com.knowledge_network.knowledge_network.service.impl;

import com.knowledge_network.knowledge_network.dao.KnowledgePointImportanceDAO;
import com.knowledge_network.knowledge_network.entity.KnowledgePointImportance;
import com.knowledge_network.knowledge_network.service.inner.KnowledgePointImportanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: wjk
 * Date: 18-1-21
 * Time: 下午12:05
 */
@Service
public class KnowledgePointImportanceServiceImpl implements KnowledgePointImportanceService {
    @Autowired
    private KnowledgePointImportanceDAO knowledgePointImportanceDAO;

    @Override
    public KnowledgePointImportance getImportanceByID(int id) {
        if (id > 0)
            return knowledgePointImportanceDAO.findKnowledgePointImportanceById(id);
        else return null;
    }
}
