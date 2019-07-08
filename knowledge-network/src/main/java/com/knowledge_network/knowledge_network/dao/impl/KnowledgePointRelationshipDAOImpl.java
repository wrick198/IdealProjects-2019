package com.knowledge_network.knowledge_network.dao.impl;

import com.knowledge_network.knowledge_network.dao.KnowledgePointRelationshipDAO;
import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.knowledge_network.entity.KnowledgePointRelationship;
import com.knowledge_network.support.base.BaseHibernateDAO;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * # Created by HeartUnderBlade on 2017/12/8
 * 实现知识点关系DAO层的接口
 */
@Repository
public class KnowledgePointRelationshipDAOImpl extends BaseHibernateDAO<KnowledgePointRelationship>
        implements KnowledgePointRelationshipDAO {
    @Override
    public void saveKnowledgePointRelationShip(KnowledgePointRelationship relationship) {
        save(relationship);
    }


    @Override
    public void deleteKnowledgePointRelationShip(KnowledgePointRelationship relationship) {
        delete(relationship);
    }

    @Override
    public List<KnowledgePointRelationship> findAllRelationshipsByPoint(KnowledgePoint kp) {
        List<KnowledgePointRelationship> result;
        result = findBy("knowledge_point_id1", kp);
        result.addAll(findBy("knowledge_point_id2", kp));
        return result;
    }

    @Override
    public List<KnowledgePointRelationship> findSonRelationshipByPoint(KnowledgePoint kp) {
        return find("select kpr from KnowledgePointRelationship kpr " +
                "where kpr.knowledgePointRelationshipType = 0 " +
                "and kpr.knowledgePoint1 = ?0", kp);
    }

    @Override
    public List<KnowledgePointRelationship> findParentRelationshipByPoint(KnowledgePoint kp) {

        return find("select kpr from KnowledgePointRelationship kpr " +
                "where kpr.knowledgePointRelationshipType = 0 " +
                "and kpr.knowledgePoint2 = ?0", kp);
    }

    @Override
    public List<KnowledgePointRelationship> findForwardRelationshipByPoint(KnowledgePoint kp) {
        return find("select kpr from KnowledgePointRelationship kpr " +
                "where kpr.knowledgePointRelationshipType = 1 " +
                "and kpr.knowledgePoint2 = ?0", kp);
    }

    @Override
    public List<KnowledgePointRelationship> findBackwardRelationshipByPoint(KnowledgePoint kp) {
        return find("select kpr from KnowledgePointRelationship kpr " +
                "where kpr.knowledgePointRelationshipType = 1 " +
                "and kpr.knowledgePoint1 = ?0", kp);
    }

    @Override
    public KnowledgePointRelationship findAccurateRelationship(KnowledgePoint kp_1, KnowledgePoint kp_2) {
        if (kp_1 != null && kp_2 != null) {
            KnowledgePointRelationship.KnowledgePointRelationshipPK pk = new KnowledgePointRelationship.KnowledgePointRelationshipPK();
            pk.setKnowledgePointId1(kp_1.getId());
            pk.setKnowledgePointId2(kp_2.getId());
            return findById(pk);
        } else
            return null;
    }
}
