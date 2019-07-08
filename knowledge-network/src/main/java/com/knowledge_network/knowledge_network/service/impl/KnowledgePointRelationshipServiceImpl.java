package com.knowledge_network.knowledge_network.service.impl;

import com.knowledge_network.knowledge_network.dao.KnowledgePointDAO;
import com.knowledge_network.knowledge_network.dao.KnowledgePointRelationshipDAO;
import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.knowledge_network.entity.KnowledgePointRelationship;
import com.knowledge_network.knowledge_network.service.KnowledgePointRelationshipService;
import com.knowledge_network.knowledge_network.service.KnowledgePointService;
import com.knowledge_network.knowledge_network.vo.KnowledgePointRelationshipVO;
import com.knowledge_network.knowledge_network.vo.web.AddKnowledgePointInfoVO;
import com.knowledge_network.knowledge_network.vo.web.AddKnowledgePointRelationshipVO;
import com.knowledge_network.knowledge_network.vo.web.DeleteKnowledgePointRelationshipVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by tjk on 17-12-6.
 */
@Service
public class KnowledgePointRelationshipServiceImpl implements KnowledgePointRelationshipService {

    @Autowired
    private KnowledgePointRelationshipDAO knowledgePointRelationshipDAO;

    @Autowired
    private KnowledgePointDAO knowledgePointDAO;

    @Autowired
    private KnowledgePointService knowledgePointService;

    @Override
    public void createKnowledgePointRelationship(KnowledgePointRelationshipVO kprInfo) {
        KnowledgePointRelationship newRelation = new KnowledgePointRelationship();
        if (kprInfo.getKnowledgePointRelationshipType() != null) {
            newRelation.setKnowledgePointRelationshipType(kprInfo.getKnowledgePointRelationshipType());
        }
        if (kprInfo.getKnowledgePointId1() != null && kprInfo.getKnowledgePointId2() != null) {
            KnowledgePoint kp1 = knowledgePointDAO.findKnowledgePointByID(kprInfo.getKnowledgePointId1());
            KnowledgePoint kp2 = knowledgePointDAO.findKnowledgePointByID(kprInfo.getKnowledgePointId2());
            if (kp1 != null && kp2 != null) {
                newRelation.setKnowledgePoint1(kp1);
                newRelation.setKnowledgePoint2(kp2);
            }

            KnowledgePointRelationship.KnowledgePointRelationshipPK pk = new KnowledgePointRelationship.KnowledgePointRelationshipPK();
            pk.setKnowledgePointId1(kprInfo.getKnowledgePointId1());
            pk.setKnowledgePointId2(kprInfo.getKnowledgePointId2());
            newRelation.setPrimaryKey(pk);
        }
        if (kprInfo.getTightness() != null) {
            newRelation.setTightness(kprInfo.getTightness());
        }
        knowledgePointRelationshipDAO.saveKnowledgePointRelationShip(newRelation);
    }

    @Override
    public void saveKnowledgePointRelationShip(KnowledgePointRelationship knowledgePointRelationship) {
        knowledgePointRelationshipDAO.saveKnowledgePointRelationShip(knowledgePointRelationship);
    }

    @Override
    public void modifyKnowledgePointRelationship(KnowledgePointRelationship oldRelation, KnowledgePointRelationshipVO newInfo) {
        if (newInfo.getKnowledgePointRelationshipType() != null) {
            oldRelation.setKnowledgePointRelationshipType(newInfo.getKnowledgePointRelationshipType());
        }
        if (newInfo.getTightness() != null) {
            oldRelation.setTightness(newInfo.getTightness());
        }
        knowledgePointRelationshipDAO.saveKnowledgePointRelationShip(oldRelation);
    }

    @Override
    public void deleteKnowledgePointRelationship(int knowledgePoint_1, int knowledgePoint_2) {
        KnowledgePoint kp1 = knowledgePointDAO.findKnowledgePointByID(knowledgePoint_1);
        KnowledgePoint kp2 = knowledgePointDAO.findKnowledgePointByID(knowledgePoint_2);
        KnowledgePointRelationship kpr = knowledgePointRelationshipDAO.findAccurateRelationship(kp1, kp2);
        if (kpr != null) {
            knowledgePointRelationshipDAO.deleteKnowledgePointRelationShip(kpr);
        }
    }


    @Override
    public void addKnowledgePointRelationshipByAddKnowledgePointRelationshipVO(int id, AddKnowledgePointInfoVO addKnowledgePointInfoVO) {
        KnowledgePoint knowledgePoint = knowledgePointDAO.findKnowledgePointByID(id);
        Collection<AddKnowledgePointRelationshipVO> addKnowledgePointRelationshipVOS = addKnowledgePointInfoVO.getAddKnowledgePointRelationshipVOS();
        if (addKnowledgePointRelationshipVOS != null) {
            for (AddKnowledgePointRelationshipVO addKnowledgePointRelationshipVO : addKnowledgePointRelationshipVOS) {
                int type = addKnowledgePointRelationshipVO.getKnowledgePointRealtionshipType();
                KnowledgePointRelationship relationship = null;
                //当前知识点
                KnowledgePoint knowledgePoint1 = knowledgePointService.getKnowledgePointByID(id);
                //要关联的知识点
                KnowledgePoint knowledgePoint2 = knowledgePointService.getKnowledgePointByID(addKnowledgePointRelationshipVO.getKnowledgePointid());

                switch (type) {
                    case 0:
                        if (knowledgePointRelationshipDAO.findAccurateRelationship(knowledgePoint2, knowledgePoint1) == null) {
                            relationship = new KnowledgePointRelationship(knowledgePoint2, knowledgePoint1, 0, addKnowledgePointRelationshipVO.getTightness());
                            break;
                        } else {
                            knowledgePointRelationshipDAO.findAccurateRelationship(knowledgePoint2, knowledgePoint1).setKnowledgePointRelationshipType(0);
                            break;
                        }
                    case 1:
                        if (knowledgePointRelationshipDAO.findAccurateRelationship(knowledgePoint1, knowledgePoint2) == null) {
                            relationship = new KnowledgePointRelationship(knowledgePoint1, knowledgePoint2, 0, addKnowledgePointRelationshipVO.getTightness());
                            break;
                        } else {
                            knowledgePointRelationshipDAO.findAccurateRelationship(knowledgePoint1, knowledgePoint2).setKnowledgePointRelationshipType(0);
                            break;
                        }
                    case 2:
                        if (knowledgePointRelationshipDAO.findAccurateRelationship(knowledgePoint2, knowledgePoint1) == null) {
                            relationship = new KnowledgePointRelationship(knowledgePoint1, knowledgePoint2, 1, addKnowledgePointRelationshipVO.getTightness());
                            break;
                        } else {
                            knowledgePointRelationshipDAO.findAccurateRelationship(knowledgePoint2, knowledgePoint1).setKnowledgePointRelationshipType(1);
                            break;
                        }
                    case 3:
                        if (knowledgePointRelationshipDAO.findAccurateRelationship(knowledgePoint1, knowledgePoint2) == null) {
                            relationship = new KnowledgePointRelationship(knowledgePoint1, knowledgePoint2, 1, addKnowledgePointRelationshipVO.getTightness());
                            break;
                        } else {
                            knowledgePointRelationshipDAO.findAccurateRelationship(knowledgePoint1, knowledgePoint2).setKnowledgePointRelationshipType(1);
                            break;
                        }
                }
                knowledgePointRelationshipDAO.saveKnowledgePointRelationShip(relationship);
            }
        }
    }

    @Override
    public void deleteKnowledgePointRelationshipByDeleteKnowledgePointRelationshipVOS(
            int kp1,
            Collection<DeleteKnowledgePointRelationshipVO> deleteKnowledgePointRelationshipVOS) {
        if (deleteKnowledgePointRelationshipVOS != null)
            for (DeleteKnowledgePointRelationshipVO deleteKnowledgePointRelationshipVO : deleteKnowledgePointRelationshipVOS) {
                int type = deleteKnowledgePointRelationshipVO.getKnowledgePointRealtionshipType();
                int kp2 = deleteKnowledgePointRelationshipVO.getKnowledgePointid();
                if (type == 0 || type == 2)
                    deleteKnowledgePointRelationship(kp2, kp1);
                else
                    deleteKnowledgePointRelationship(kp1, kp2);
            }
    }
}

