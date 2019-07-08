package com.knowledge_network.user.dao.impl;

import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.support.utils.StringUtils;
import com.knowledge_network.user.dao.UserDAO;
import com.knowledge_network.user.entity.User;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pentonbin on 17-12-2
 */
@Repository
public class UserDAOImpl extends BaseHibernateDAO<User> implements UserDAO {

    @Override
    public User findUserById(Integer id) {
        if (id == null) {
            return null;
        }
        return findById(id);
    }

    @Override
    public User findUserByUsername(String username) {
        if (StringUtils.isNullOrEmpty(username)) {
            return null;
        }
        return findByUnique("username", username);
    }

    @Override
    public void updateUser(User user) {
        save(user);
    }

    @Override
    public List<User> findUserByOrderPage(int start, int row, Order order) {
        return findByOrderPage(order, start, row);
    }

    @Override
    public List<User> findUserByConditionsOrderPage(int start, int rows, Order order, List<Condition> conditions) {
        return findByOrderConditionsPage(start, rows, order, conditions);
    }

    @Override
    public long getUserCountByConditions(List<Condition> conditions) {
        return findCountByConditions(conditions);
    }

    @Override
    public void removeUser(User user) {
        delete(user);
    }
}
