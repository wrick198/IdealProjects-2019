package com.knowledge_network.knowledge_network.dao.impl;

import com.knowledge_network.knowledge_network.dao.KnowledgePointDAO;
import com.knowledge_network.knowledge_network.dao.TagDAO;
import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.knowledge_network.entity.Subject;
import com.knowledge_network.knowledge_network.entity.Tag;
import com.knowledge_network.support.base.BaseHibernateDAO;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by wjk on 17-12-4.
 */
@Repository
public class KnowledgePointDAOImpl extends BaseHibernateDAO<KnowledgePoint> implements KnowledgePointDAO {

    @Autowired
    private TagDAO tagDAO;


    @Override
    public List<KnowledgePoint> findKnowledgePointsByName(String name) {
        List<Criterion> criterions = new ArrayList<>();
        criterions.add(createCriterionByCondition(ConditionType.LIKE, "name", name));
        Order order = Order.asc("name");
        return findByOrder(order, criterions);
    }

    @Override
    public KnowledgePoint findKnowledgePointByName(String name) {
        return (KnowledgePoint) findByUnique("name", name);
    }

    @Override
    public KnowledgePoint findKnowledgePointByID(int id) {
        return findByUnique("id", id);
    }

    @Override
    public List<KnowledgePoint> findKnowledgePointByStatement(String statement) {
        return findBy("statement", statement);
    }

    @Override
    public List<KnowledgePoint> findKnowledgePointByGrade(int grade) {
        return findBy("grade", grade);
    }

    @Override
    public List<KnowledgePoint> findKnowledgePointByParent(int parent_id) {
        return null;
    }

    @Override
    public void updateKnowledgePoint(int id, Map map) {

        //知识点难度和知识点重要程度的更新暂时没写

        KnowledgePoint knowledgePoint = findKnowledgePointByID(id);
        if (map.get("name") != null)
            knowledgePoint.setName((String) map.get("name"));
        if (map.get("statement") != null)
            knowledgePoint.setStatement((String) map.get("statement"));
        if (map.get("content") != null)
            knowledgePoint.setContent((String) map.get("content"));
        if (map.get("grade") != null)
            knowledgePoint.setGrade((Integer) map.get("grade"));

    }

    @Override
    public void deleteKnowledgePoint(KnowledgePoint knowledgePoint) {
        knowledgePoint.setDeleted(1);
        knowledgePoint.setEnable(0);
        save(knowledgePoint);
    }

    @Override
    public List<KnowledgePoint> findKnowledgePointBySubject(Subject subject) {
        return findBy("subject", subject);
    }

    @Override
    public void saveKnowledgePoint(KnowledgePoint knowledgePoint) {
        save(knowledgePoint);

    }

    @Override
    public void updateKnowledgePointList(List<KnowledgePoint> knowledgePointList) {
        save(knowledgePointList);
    }

    @Override
    public List<KnowledgePoint> getKnowledgePointPageByCondition(int start, int rows, Order order, List<Condition> condition) {
        return findByOrderConditionsPage(start, rows, order, condition);
    }

    @Override
    public long getKnowledgePointCountByCondition(List<Condition> conditions) {
        return findCountByConditions(conditions);
    }

    @Override
    public List<KnowledgePoint> findKnowledgePointsByTag(String tagName) {
        List<Tag> tagList = (List<Tag>) tagDAO.findTagsByName(tagName);
        Set<KnowledgePoint> knowledgePointHashSet = new HashSet<>();
        for (Tag tag : tagList) {
            List<KnowledgePoint> knowledgePointList = (List<KnowledgePoint>) tag.getKnowledgePoints();
            for (KnowledgePoint knowledgePoint : knowledgePointList) {
               knowledgePointHashSet.add(knowledgePoint);
            }
        }
        List<KnowledgePoint> result=new ArrayList<>();
        for (KnowledgePoint knowledgePoint:knowledgePointHashSet)
            result.add(knowledgePoint);
        return result;
    }
}
