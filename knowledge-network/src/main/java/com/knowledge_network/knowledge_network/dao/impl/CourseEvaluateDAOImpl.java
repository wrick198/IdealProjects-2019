package com.knowledge_network.knowledge_network.dao.impl;

import com.knowledge_network.knowledge_network.dao.CourseEvaluateDAO;
import com.knowledge_network.knowledge_network.entity.CourseEvaluate;
import com.knowledge_network.support.base.BaseHibernateDAO;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseEvaluateDAOImpl extends BaseHibernateDAO<CourseEvaluate> implements CourseEvaluateDAO {
    @Override
    public CourseEvaluate findCourseEvaluateById(Integer id) {
        return findById(id);
    }

    @Override
    public void updateCourseEvaluate(CourseEvaluate courseEvaluate) {
        save(courseEvaluate);
    }

    @Override
    public void deleteCourseEvaluate(CourseEvaluate courseEvaluate) {
        delete(courseEvaluate);
    }

    @Override
    public List<CourseEvaluate> findCourseEvaluatesByOrderPage(int start, int row, Order order) {
        return findByOrderPage(order, start, row);
    }

    @Override
    public List<CourseEvaluate> findCourseEvaluatesByConditionOrderPage(int start, int row, Order order, List<BaseHibernateDAO.Condition> condition) {
        return findByOrderConditionsPage(start, row, order, condition);
    }
}
