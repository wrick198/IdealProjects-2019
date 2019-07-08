package com.knowledge_network.knowledge_network.dao.impl;

import com.knowledge_network.knowledge_network.dao.CourseDAO;
import com.knowledge_network.knowledge_network.entity.*;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.Student;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wshish000 on 17-12-9
 */
@Repository
public class CourseDAOImpl extends BaseHibernateDAO<Course> implements CourseDAO {
    @Override
    public Course findCourseById(Integer id) {
        if (id == null) {
            return null;
        }
        return findById(id);
    }

    @Override
    public Course findCoursesByName(String name) {
        return findByUnique("name", name);
    }

    @Override
    public void addCourse(Course course) {
        updateCourse(course);
    }

    @Override
    public void updateCourse(Course course) {
        save(course);
    }

    @Override
    public void removeCourse(Course course) {
        delete(course);
    }

    @Override
    public void updateCourseList(List<Course> courseList) {
        save(courseList);
    }

    @Override
    public List<Course> findCoursesBySubject(Subject subject) {
        return null;
    }

    @Override
    public List<Course> findCoursesByGrade(int grade) {
        return null;
    }

    @Override
    public List<Course> findCoursesByOrderPage(int start, int row, Order order) {
        return findByOrderPage(order,start,row);
    }

    @Override
    public List<Course> findCoursesByConditionsOrderPage(int start, int row, Order order, List<Condition> conditions) {
        return findByOrderConditionsPage(start, row, order, conditions);
    }

    @Override
    public long getCoursesCountByConditions(List<Condition> conditions) {
        return 0;
    }

    @Override
    public List<KnowledgePoint> getKnowledgePointByCourse(Course course) {
        return (List<KnowledgePoint>) course.getKnowledgePoints();
    }

    @Override
    public List<Student> getStudentByCourse(Course course) {
        return (List<Student>) course.getAllStudents();
    }

    @Override
    public List<CourseQuestion> getCourseQuestionByCourse(Course course) {
        return (List<CourseQuestion>) course.getQuestions();
    }

    @Override
    public List<CourseEvaluate> getCourseEvaluateByCourse(Course course) {
        return (List<CourseEvaluate>) course.getEvaluates();
    }
}
