package com.knowledge_network.user.dao.impl;

import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.dao.TeacherDAO;
import com.knowledge_network.user.entity.Teacher;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pentonbin on 17-12-14
 */
@Repository
public class TeacherDAOImpl extends BaseHibernateDAO<Teacher> implements TeacherDAO {

    @Override
    public Teacher findTeacherById(Integer id) {
        return findById(id);
    }

    @Override
    public void createTeacher(Teacher teacher) {
        save(teacher);
    }

    @Override
    public List<Teacher> findTeacherByConditionsOrderPage(int start, int rows, Order order, List<Condition> conditions) {
        return findByOrderConditionsPage(start, rows, order, conditions);
    }

    @Override
    public List<Teacher> findTeacherByOrderPage(int start, int rows, Order order) {
        return findByOrderConditionsPage(start, rows, order, null);
    }

    @Override
    public long getTeacherCountByConditions(List<Condition> conditions) {
        return findCountByConditions(conditions);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        save(teacher);
    }

    @Override
    public Teacher findTeacherByUsername(String username) {
        return findByUnique("username", username);
    }
}
