package com.knowledge_network.knowledge_network.dao;

import com.knowledge_network.knowledge_network.entity.CourseQuestion;
import com.knowledge_network.support.base.BaseHibernateDAO;
import org.hibernate.criterion.Order;

import java.util.List;

public interface CourseQuestionDAO {
    /**
     * 添加或更新课程问题进数据库
     * @param courseQuestion
     */
    void updateCourseQuestion(CourseQuestion courseQuestion);

    /**
     * 根据条件筛选出问题列表
     * @param start
     * @param row
     * @param order
     * @param condition
     * @return
     */
    List<CourseQuestion> findCourseQuestionsByOrderCondition(int start, int row, Order order, List<BaseHibernateDAO.Condition> condition);

    /**
     * 不加条件筛选出问题列表
     * @param order
     * @param start
     * @param row
     * @return
     */
    List<CourseQuestion> findCourseQuestionsByOrder(Order order, int start, int row);

    /**
     * 根据id查找课程问题
     * @param id
     * @return
     */
    CourseQuestion findCourseQuestionById(Integer id);

    /**
     * 删除课程问题
     * @param courseQuestion
     */
    void deleteCourseQuestion(CourseQuestion courseQuestion);
}
