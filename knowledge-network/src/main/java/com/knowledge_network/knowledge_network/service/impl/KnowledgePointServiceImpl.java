package com.knowledge_network.knowledge_network.service.impl;

import com.knowledge_network.knowledge_network.dao.KnowledgePointDAO;
import com.knowledge_network.knowledge_network.dao.TagDAO;
import com.knowledge_network.knowledge_network.entity.*;
import com.knowledge_network.knowledge_network.service.KnowledgePointService;
import com.knowledge_network.knowledge_network.service.SubjectService;
import com.knowledge_network.knowledge_network.service.inner.KnowledgePointDifficultService;
import com.knowledge_network.knowledge_network.service.inner.KnowledgePointImportanceService;
import com.knowledge_network.knowledge_network.vo.web.AddKnowledgePointInfoVO;
import com.knowledge_network.support.base.BaseHibernateDAO;
import org.aspectj.weaver.ast.Or;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by wjk on 17-12-7.
 */
@Service
public class KnowledgePointServiceImpl implements KnowledgePointService {

    private static final int MAXGRADE = 12;
    @Autowired
    private KnowledgePointDAO knowledgePointDAO;
    @Autowired
    private TagDAO tagDAO;
    @Autowired
    private KnowledgePointDifficultService knowledgePointDifficultService;
    @Autowired
    private KnowledgePointImportanceService knowledgePointImportanceService;
    @Autowired
    private SubjectService subjectService;

    @Override
    public KnowledgePoint getKnowledgePointByID(int id) {
        if (id < 0)
            return null;
        else
            return knowledgePointDAO.findKnowledgePointByID(id);

    }

    @Override
    public List<KnowledgePoint> getKnowledgePointsByName(String name) {
        if (name == null)
            return null;
        else
            return knowledgePointDAO.findKnowledgePointsByName(name);
    }

    @Override
    public KnowledgePoint getKnowledgePointByName(String name) {

        if (name == null)
            return null;
        else
            return knowledgePointDAO.findKnowledgePointByName(name);

    }

    @Override
    public List<KnowledgePoint> getKnowledgePointByStatement(String statement) {
        if (statement == null)
            return null;
        else
            return knowledgePointDAO.findKnowledgePointByStatement(statement);
    }

    @Override
    public List<KnowledgePoint> getKnowledgePointByTag(String tagname) {
        if (tagname == null)
            return null;
        else
            return (List<KnowledgePoint>) tagDAO.findTagByName(tagname).getKnowledgePoints();


    }

    @Override
    public List<KnowledgePoint> getKnowledgePointByGrade(int grade) {
        if (grade <= 0 || grade > MAXGRADE)
            return null;
        else
            return knowledgePointDAO.findKnowledgePointByGrade(grade);
    }

    @Override
    public List<KnowledgePoint> getSubKnowledgePoints(String name) {
        if (name == null)
            return null;
        else {
            KnowledgePoint knowledgePoint = knowledgePointDAO.findKnowledgePointByName(name);
            List<KnowledgePointRelationship> knowledgePointRelationships = (List<KnowledgePointRelationship>) knowledgePoint.getKnowledgePointRelationships1();
            List<KnowledgePoint> knowledgePoints = new ArrayList<>();
            for (KnowledgePointRelationship knowledgePointRelationship : knowledgePointRelationships) {
                if (knowledgePointRelationship.getKnowledgePointRelationshipType() == 0)
                    knowledgePoints.add(knowledgePointRelationship.getKnowledgePoint2());
            }
            return knowledgePoints;
        }

    }

    @Override
    public KnowledgePoint getSupKnowledgePoints(String name) {
        if (name == null)
            return null;
        else {
            KnowledgePoint knowledgePoint = knowledgePointDAO.findKnowledgePointByName(name);
            List<KnowledgePointRelationship> knowledgePointRelationships = (List<KnowledgePointRelationship>) knowledgePoint.getKnowledgePointRelationships2();
            for (KnowledgePointRelationship knowledgePointRelationship : knowledgePointRelationships) {
                if (knowledgePointRelationship.getKnowledgePointRelationshipType() == 0)
                    return knowledgePointRelationship.getKnowledgePoint1();
            }
        }
        return null;
    }

    @Override
    public List<KnowledgePoint> getPreKnowledgePoints(String name) {
        if (name == null)
            return null;
        else {
            KnowledgePoint knowledgePoint = knowledgePointDAO.findKnowledgePointByName(name);
            List<KnowledgePointRelationship> knowledgePointRelationships = (List<KnowledgePointRelationship>) knowledgePoint.getKnowledgePointRelationships2();
            List<KnowledgePoint> knowledgePoints = new ArrayList<>();
            for (KnowledgePointRelationship knowledgePointRelationship : knowledgePointRelationships) {
                if (knowledgePointRelationship.getKnowledgePointRelationshipType() == 1)
                    knowledgePoints.add(knowledgePointRelationship.getKnowledgePoint1());
            }
            return knowledgePoints;
        }

    }

    @Override
    public List<KnowledgePoint> getPostKnowledgePoints(String name) {
        if (name == null)
            return null;
        else {
            KnowledgePoint knowledgePoint = knowledgePointDAO.findKnowledgePointByName(name);
            List<KnowledgePointRelationship> knowledgePointRelationships = (List<KnowledgePointRelationship>) knowledgePoint.getKnowledgePointRelationships1();
            List<KnowledgePoint> knowledgePoints = new ArrayList<>();
            for (KnowledgePointRelationship knowledgePointRelationship : knowledgePointRelationships) {
                if (knowledgePointRelationship.getKnowledgePointRelationshipType() == 1)
                    knowledgePoints.add(knowledgePointRelationship.getKnowledgePoint2());
            }
            return knowledgePoints;
        }
    }

    @Override
    public void creatKnowledgePoint(Map map) {
        KnowledgePoint knowledgePoint = new KnowledgePoint();
        if (map.get("name") != null)
            knowledgePoint.setName((String) map.get("name"));
        if (map.get("statement") != null)
            knowledgePoint.setStatement((String) map.get("statement"));
        if (map.get("content") != null)
            knowledgePoint.setContent((String) map.get("statement"));
        if (map.get("grade") != null)
            knowledgePoint.setGrade((Integer) map.get("grade"));
    }

    @Override
    public void deleteKnowledgePointName(String name) {
        if (name != null)
            knowledgePointDAO.deleteKnowledgePoint(knowledgePointDAO.findKnowledgePointByName(name));
    }

    @Override
    public void deleteKnowledgePointByID(int id) {
        if (knowledgePointDAO.findKnowledgePointByID(id) != null)
            knowledgePointDAO.deleteKnowledgePoint(knowledgePointDAO.findKnowledgePointByID(id));
    }

    @Override
    public void modifyKnowledgePoint(String name, Map map) {
        KnowledgePoint knowledgePoint = knowledgePointDAO.findKnowledgePointByName(name);
        if (map.get("name") != null)
            knowledgePoint.setName((String) map.get("name"));
        if (map.get("statement") != null)
            knowledgePoint.setStatement((String) map.get("statement"));
        if (map.get("content") != null)
            knowledgePoint.setContent((String) map.get("statement"));
        if (map.get("grade") != null)
            knowledgePoint.setGrade((Integer) map.get("grade"));
        saveKnowledgePoint(knowledgePoint);
    }

    @Override
    public List<Resource> getResoources(String name) {
        if (name != null)
            return (List<Resource>) knowledgePointDAO.findKnowledgePointByName(name).getResources();
        else
            return null;
    }


    @Override

    public void updateKnowledgePointname(String name, String KPname) {
        knowledgePointDAO.findKnowledgePointByName(name).setName(KPname);

    }

    @Override
    public void updateKnowledgePointStatment(String KPname, String KPsatement) {
        knowledgePointDAO.findKnowledgePointByName(KPname).setStatement(KPsatement);

    }

    @Override
    public void updateKnowledgePointContent(String KPname, String KPcontent) {
        knowledgePointDAO.findKnowledgePointByName(KPname).setContent(KPcontent);

    }

    @Override
    public void updateKnowledgePointTag(String KPname, Set<Tag> set) {
        knowledgePointDAO.findKnowledgePointByName(KPname).setTags(set);

    }

    @Override
    public void updateKnowledgePointImportance(String name, KnowledgePointImportance knowledgePointImportance) {
        knowledgePointDAO.findKnowledgePointByName(name).setKnowledgePointImportance(knowledgePointImportance);
    }

    @Override
    public void updateKnowledgePointDiffcult(String name, KnowledgePointDifficult knowledgePointDifficult) {
        knowledgePointDAO.findKnowledgePointByName(name).setKnowledgePointDifficult(knowledgePointDifficult);
    }

    @Override
    public void saveKnowledgePoint(KnowledgePoint knowledgePoint) {
        knowledgePointDAO.saveKnowledgePoint(knowledgePoint);
    }

    @Override
    public List<KnowledgePoint> getKnowledgePointPageByCondition(int start, int rows, List<BaseHibernateDAO.Condition> conditions) {
        if (start < 0 || rows <= 0) {
            return new ArrayList<>();
        } else {
            Order order = Order.desc("name");
            conditions.add(new BaseHibernateDAO.Condition(BaseHibernateDAO.ConditionType.GT, "id", 0));
            return knowledgePointDAO.getKnowledgePointPageByCondition(start, rows, order, conditions);
        }
    }

    @Override
    public long getKnowledgePointCountByCondition(List<BaseHibernateDAO.Condition> conditions) {
        if (conditions.size() > 0) {
            return knowledgePointDAO.getKnowledgePointCountByCondition(conditions);
        } else {
            List<BaseHibernateDAO.Condition> conditionList = new ArrayList<>();
            conditionList.add(new BaseHibernateDAO.Condition(BaseHibernateDAO.ConditionType.GT, "ID", 0));
            return knowledgePointDAO.getKnowledgePointCountByCondition(conditionList);
        }
    }

    @Override
    public void deleteKnowledgePoint(KnowledgePoint knowledgePoint) {
        knowledgePointDAO.deleteKnowledgePoint(knowledgePoint);
    }

    @Override
    public List<KnowledgePoint> getKnowlegePointBySubject(Subject subject) {
        if (subject != null)
            return knowledgePointDAO.findKnowledgePointBySubject(subject);
        else
            return null;
    }

    @Override
    public void setKnowledgePointByAddKnowledgePointInfoVO(KnowledgePoint knowledgePoint, AddKnowledgePointInfoVO addKnowledgePointInfoVO) {
        knowledgePoint.setDeleted(0);
        knowledgePoint.setEnable(1);
        if (addKnowledgePointInfoVO.getSubject() != null && subjectService.getSubjectById(Integer.parseInt(addKnowledgePointInfoVO.getSubject())) != null)
            knowledgePoint.setSubject(subjectService.getSubjectById(Integer.parseInt(addKnowledgePointInfoVO.getSubject())));
        if (addKnowledgePointInfoVO.getName() != null)
            knowledgePoint.setName(addKnowledgePointInfoVO.getName());
        if (addKnowledgePointInfoVO.getStatement() != null)
            knowledgePoint.setStatement(addKnowledgePointInfoVO.getStatement());
        if (addKnowledgePointInfoVO.getContent() != null)
            knowledgePoint.setContent(addKnowledgePointInfoVO.getContent());
        if (addKnowledgePointInfoVO.getGrade() != null)
            knowledgePoint.setGrade(addKnowledgePointInfoVO.getGrade());
        if (addKnowledgePointInfoVO.getDifficult() != null)
            knowledgePoint.setKnowledgePointDifficult(knowledgePointDifficultService.getKnowledePointDiffcultByID(Integer.parseInt(addKnowledgePointInfoVO.getDifficult())));
        if (addKnowledgePointInfoVO.getImportance() != null)
            knowledgePoint.setKnowledgePointImportance(knowledgePointImportanceService.getImportanceByID(Integer.parseInt(addKnowledgePointInfoVO.getImportance())));
        saveKnowledgePoint(knowledgePoint);
    }

    @Override
    public List<KnowledgePoint> getKnowledgePointsByTagOrName(String name) {
        HashSet<KnowledgePoint> knowledgePointHashSet = new HashSet<>();
        List<KnowledgePoint> knowledgePoints1 = knowledgePointDAO.findKnowledgePointsByName(name);
        List<KnowledgePoint> knowledgePoints2 = knowledgePointDAO.findKnowledgePointsByTag(name);
        for (KnowledgePoint knowledgePoint : knowledgePoints1)
            knowledgePointHashSet.add(knowledgePoint);
        for (KnowledgePoint knowledgePoint : knowledgePoints2)
            knowledgePointHashSet.add(knowledgePoint);
        List<KnowledgePoint> result = new ArrayList<>();
        for (KnowledgePoint knowledgePoint : knowledgePointHashSet)
            result.add(knowledgePoint);
        return result;

    }
}
