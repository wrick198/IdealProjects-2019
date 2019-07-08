package com.knowledge_network.knowledge_network.dao.impl;

import com.knowledge_network.knowledge_network.dao.KnowledgePointImportanceDAO;
import com.knowledge_network.knowledge_network.entity.KnowledgePointImportance;
import com.knowledge_network.support.base.BaseHibernateDAO;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by wjk on 17-12-6.
 */
@Repository
public class KnowledgePointImportanceDAOImpl extends BaseHibernateDAO<KnowledgePointImportance> implements KnowledgePointImportanceDAO {

    @Override
    public KnowledgePointImportance findKnowledgePointImportanceById(int id) {
        return findByUnique("id", id);
    }

    @Override
    public KnowledgePointImportance findKnowledgePointImportanceByLevel(int level) {
        return findByUnique("level", level);
    }

    @Override
    public KnowledgePointImportance findKnowledgePointImportanceByName(String name) {
        return findByUnique("name", name);
    }

    @Override
    public void updateKnowledgePointImportanceByLevel(int level, Map<String, Object> map) {
        //其他属性暂未写
        KnowledgePointImportance knowledgePointImportance = findKnowledgePointImportanceByLevel(level);
        if (map.get("level") != null)
            knowledgePointImportance.setLevel((Integer) map.get("level"));
        if (map.get("name") != null)
            knowledgePointImportance.setName((String) map.get("name"));

    }

    @Override
    public void updateKnowledgePointImportanceByName(String name, Map<String, Object> map) {
        KnowledgePointImportance knowledgePointImportance = findKnowledgePointImportanceByName(name);
        if (map.get("level") != null)
            knowledgePointImportance.setLevel((Integer) map.get("level"));
        if (map.get("name") != null)
            knowledgePointImportance.setName((String) map.get("name"));
    }

    @Override
    public void deleteKnowledgePointImportanceById(int id) {
        delete(findKnowledgePointImportanceById(id));
    }

    @Override
    public void saveKnowledgePointImportance(KnowledgePointImportance knowledgePointImportance) {
        save(knowledgePointImportance);
    }
}
