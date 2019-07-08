package com.knowledge_network.knowledge_network.dao;

import com.knowledge_network.knowledge_network.entity.*;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.Student;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by wshish000 on 17-12-9
 * 提供访问课程表course的DAO
 */
// TODO: 17-12-9 还有一些功能待写，此处只作测试用
public interface CourseDAO {
    /**
     * 根据课程ID获取对应的课程
     *
     * @param id 课程的ID
     * @return 查找的课程
     */
    Course findCourseById(Integer id);

    /**
     * 根据课程名字获取对应的课程
     *
     * @param name 课程的名字
     * @return 查找的课程
     */
    Course findCoursesByName(String name);

    /**
     * 添加一个新的课程
     *
     * @param course 新增的课程对象
     */
    void addCourse(Course course);

    /**
     * 更新某个资源
     *
     * @param course 想要更改的课程对象
     */
    void updateCourse(Course course);

    /**
     * 删除某个课程
     *
     * @param course 想要删除的课程对象
     */
    void removeCourse(Course course);

    /**
     * 更新列表中的课程
     *
     * @param courseList 想要更新的课程列表
     */
    void updateCourseList(List<Course> courseList);

    /**
     * 通过科目查找课程
     * @param subject
     * @return
     */
    List<Course> findCoursesBySubject(Subject subject);

    /**
     * 通过年级查找课程
     * @param grade
     * @return
     */
    List<Course> findCoursesByGrade(int grade);

    /**
     * 分页获取课程
     * @param start
     * @param row
     * @param order
     * @return 符合条件的课程列表
     */
    List<Course> findCoursesByOrderPage(int start, int row, Order order);

    /**
     * 根据条件分页获取课程
     * @param start
     * @param row
     * @param order
     * @param conditions
     * @return 符合条件的课程列表
     */
    List<Course> findCoursesByConditionsOrderPage(int start, int row, Order order, List<BaseHibernateDAO.Condition> conditions);

    /**
     * 根据条件获取筛选后课程总数
     * @param conditions
     * @return 课程总数目
     */
    long getCoursesCountByConditions(List<BaseHibernateDAO.Condition> conditions);
    /**
     * 获取课程下的知识点
     *
     * @param course
     * @Author wjk
     * @return  查询的的到的知识点列表
     */
    List<KnowledgePoint> getKnowledgePointByCourse(Course course);

    /**
     * 获取参加课程的学生
     *
     * @param course
     * @Author lwh
     * @return 参加课程的学生列表
     */
    List<Student> getStudentByCourse(Course course);

    /**
     * 获取该课程讨论区的问题列表
     * @Author lwh
     * @param course
     * @return 问题列表
     */
    List<CourseQuestion> getCourseQuestionByCourse(Course course);

    /**
     * 获取当前课程评价列表
     * @Author lwh
     * @param course
     * @return 评价列表
     */
    List<CourseEvaluate> getCourseEvaluateByCourse(Course course);

}
