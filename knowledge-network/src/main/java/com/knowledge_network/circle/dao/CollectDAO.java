package com.knowledge_network.circle.dao;

import com.knowledge_network.circle.entity.Collect;
import com.knowledge_network.circle.entity.Topic;
import com.knowledge_network.user.entity.User;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by wshish000 on 18-3-4
 */
public interface CollectDAO {

    void updateCollect(Collect collect);

    void deleteCollect(Collect collect);

    List<Collect> findByUser(User user, int start, int row, Order order);

    long countByUser(User user);

    long countByTopic(Topic topic);

    Collect findByUserAndTopic(User user, Topic topic);

    void deleteById(Integer id);

    void deleteByUser(User user);

    void deleteByTopic(Topic topic);
}
