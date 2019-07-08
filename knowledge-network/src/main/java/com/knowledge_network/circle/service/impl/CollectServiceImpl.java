package com.knowledge_network.circle.service.impl;

import com.knowledge_network.circle.dao.CollectDAO;
import com.knowledge_network.circle.entity.Collect;
import com.knowledge_network.circle.entity.Topic;
import com.knowledge_network.circle.service.CollectService;
import com.knowledge_network.user.entity.User;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wshish000 on 18-3-4
 */
@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectDAO collectDAO;
    @Override
    public List<Collect> findByUser(int p, int size, User user) {
        Order order = Order.desc("inTime");
        return collectDAO.findByUser(user, p, size, order);
    }

    @Override
    public long countByUser(User user) {
        return collectDAO.countByUser(user);
    }

    @Override
    public long countByTopic(Topic topic) {
        return collectDAO.countByTopic(topic);
    }

    @Override
    public Collect findByUserAndTopic(User user, Topic topic) {
        return collectDAO.findByUserAndTopic(user, topic);
    }

    @Override
    public void save(Collect collect) {
        collectDAO.updateCollect(collect);
    }

    @Override
    public void deleteById(int id) {
        collectDAO.deleteById(id);
    }

    @Override
    public void deleteByUser(User user) {
        collectDAO.deleteByUser(user);
    }

    @Override
    public void deleteByTopic(Topic topic) {
        collectDAO.deleteByTopic(topic);
    }
}
