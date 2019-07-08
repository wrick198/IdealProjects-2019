package com.knowledge_network.knowledge_network.dao.impl;

import com.knowledge_network.knowledge_network.dao.KnowledgePointDiffcultDAO;
import com.knowledge_network.knowledge_network.entity.KnowledgePointDifficult;
import com.knowledge_network.support.base.BaseHibernateDAO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wjk on 17-12-6.
 */
@Repository
public class KnowledgePointDifficultDAOImpl extends BaseHibernateDAO<KnowledgePointDifficult> implements KnowledgePointDiffcultDAO {

    @Override
    public KnowledgePointDifficult findKnowledgePointDifficultById(int id) {
        return findByUnique("id", id);
    }

    @Override
    public KnowledgePointDifficult findKnowledgePointDifficultByLevel(int level) {
        return findByUnique("level", level);
    }

    @Override
    public KnowledgePointDifficult findKnowledgePointDifficultByName(String name) {
        return findByUnique("name", name);
    }

    @Override
    public void updateKnowledgePointDifficultByLevel(int level, Map<String, String> map) {
        KnowledgePointDifficult knowledgePointDifficult= findKnowledgePointDifficultByLevel(level);
        if (map.get("name") != null)
            knowledgePointDifficult.setName((String) map.get("name"));
        if (map.get("level") != null)
            knowledgePointDifficult.setLevel(Integer.parseInt(map.get("level")));
    }

    @Override
    public void updateKnowledgePointDifficultByName(String name, Map<String, Object> map) {
        KnowledgePointDifficult knowledgePointDifficult = findKnowledgePointDifficultByName(name);
        if (map.get("name") != null)
            knowledgePointDifficult.setName((String) map.get("name"));
        if (map.get("level") != null)
            knowledgePointDifficult.setLevel((Integer) map.get("level"));
    }

    @Override
    public void deleteKnowledgePointDifficultById(int id) {
        delete(findKnowledgePointDifficultById(id));
    }

    @Override
    public void saveKnowledgePointDiffcult(KnowledgePointDifficult knowledgePointDifficult) {

        save(knowledgePointDifficult);
    }
}
