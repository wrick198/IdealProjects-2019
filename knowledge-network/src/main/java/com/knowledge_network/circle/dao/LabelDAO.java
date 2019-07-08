package com.knowledge_network.circle.dao;

import com.knowledge_network.circle.entity.Label;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by wshish000 on 18-3-4
 */
public interface LabelDAO {

    void deleteLabel(Label label);

    Label findLabelById(Integer id);

    void updateLabel(Label label);

    List<Label> findLabelByOrderPage(int start, int row, Order order);

    Label findByName(String name);

    List<Label> findByNameLike(String name);
}
