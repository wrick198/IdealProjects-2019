package com.knowledge_network.knowledge_network.dao.impl;

import com.knowledge_network.knowledge_network.dao.TagDAO;
import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.knowledge_network.entity.Tag;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.support.utils.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by wshish000 on 17-12-6
 */
@Repository
public class TagDAOImpl extends BaseHibernateDAO<Tag> implements TagDAO {
    @Override
    public Tag findTagById(Integer id) {
        if (id == null) {
            return null;
        }
        return findById(id);
    }

    @Override
    public Tag findTagByName(String name) {
        if (StringUtils.isNullOrEmpty(name)) {
            return null;
        }
        return findByUnique("name", name);
    }

    @Override
    public void addTag(Tag tag) {
        save(tag);
    }

    @Override
    public void updateTag(Tag tag) {
        save(tag);
    }

    @Override
    public void deleteTag(Tag tag) {
        delete(tag);
    }

    @Override
    public Collection<Tag> findTagsByName(String name) {
        List<Criterion> criterions = new ArrayList<>();
        criterions.add(createCriterionByCondition(ConditionType.LIKE, "name", name));
        Order order = Order.asc("name");
        return findByOrder(order, criterions);
    }


}
