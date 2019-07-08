package com.knowledge_network.circle.dao.impl;

import com.knowledge_network.circle.dao.LabelDAO;
import com.knowledge_network.circle.entity.Label;
import com.knowledge_network.support.base.BaseHibernateDAO;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wshish000 on 18-3-4
 */
@Repository
public class LabelDAOImpl extends BaseHibernateDAO<Label> implements LabelDAO {

    @Override
    public void deleteLabel(Label label) {
        delete(label);
    }

    @Override
    public Label findLabelById(Integer id) {
        return findById(id);
    }

    @Override
    public void updateLabel(Label label){
        save(label);
    }

    @Override
    public List<Label> findLabelByOrderPage(int start, int row, Order order) {
        return findByOrderPage(order, start, row);
    }

    @Override
    public Label findByName(String name) {
        return findByUnique("name", name);
    }

    @Override
    public List<Label> findByNameLike(String name) {
        return findBy("name", name);
    }
}
