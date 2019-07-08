package com.knowledge_network.knowledge_network.dao.impl;

import com.knowledge_network.knowledge_network.dao.CourseQuestionDAO;
import com.knowledge_network.knowledge_network.entity.CourseQuestion;
import com.knowledge_network.support.base.BaseHibernateDAO;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseQuestionDAOImpl extends BaseHibernateDAO<CourseQuestion> implements CourseQuestionDAO {

    @Override
    public void updateCourseQuestion(CourseQuestion courseQuestion) {
        save(courseQuestion);
    }

    @Override
    public List<CourseQuestion> findCourseQuestionsByOrderCondition(int start, int row, Order order, List<BaseHibernateDAO.Condition> conditions) {
        return findByOrderConditionsPage(start, row, order, conditions);
    }

    @Override
    public List<CourseQuestion> findCourseQuestionsByOrder(Order order, int start, int row) {
        return findByOrderPage(order, start, row);
    }

    @Override
    public CourseQuestion findCourseQuestionById(Integer id) {
        return findById(id);
    }

    @Override
    public void deleteCourseQuestion(CourseQuestion courseQuestion) {
        delete(courseQuestion);
    }


}
