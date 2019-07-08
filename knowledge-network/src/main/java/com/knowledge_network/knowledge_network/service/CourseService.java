package com.knowledge_network.knowledge_network.service;

import com.knowledge_network.knowledge_network.entity.Course;
import com.knowledge_network.knowledge_network.vo.CourseInfoVO;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.support.exceptions.CourseNotFoundException;

import java.util.List;

public interface CourseService {

    /**
     * 通过id查找课程
     */
    Course getCourseById(Integer id);

    /**
     * 根据id获取组装到页面去的课程的信息
     *
     * @return
     * @throws CourseNotFoundException
     */
    CourseInfoVO getCourseInfo(String id) throws CourseNotFoundException;

    /**
     * 更新课程的信息(不包括学生的信息，也不包括选课的信息)
     * id不允许修改
     *
     * @param course
     * @param courseInfoVO
     */
    void updateCourseInfo(Course course, CourseInfoVO courseInfoVO);

    /**
     * 初始化课程信息，把前端传回来的信息赋给后台
     *
     * @param course
     * @param courseInfoVO
     */
    void initCourseInfo(Course course, CourseInfoVO courseInfoVO);

    /**
     * 不加筛选地获取每一页课程信息
     *
     * @param start
     * @param rows
     * @return 课程列表
     */
    List<Course> getAllCoursesPerPage(int start, int rows);

    /**
     * 根据筛选条件的分页获取课程对象
     *
     * @param start
     * @param rows
     * @param conditions
     * @return 符合条件的课程列表
     */
    List<Course> getCoursesPageByConditions(int start, int rows, List<BaseHibernateDAO.Condition> conditions);

    /**
     * 获取所有课程数量
     *
     * @return
     */
    long getAllCourseCount();

    /**
     * 获取符合筛选条件的用户数量
     *
     * @param conditions
     * @return
     */
    long getCourseCountByConditions(List<BaseHibernateDAO.Condition> conditions);

    /**
     * 添加新课程对象
     *
     * @param course
     */
    void createCourse(Course course);

    /**
     * 移除课程
     *
     * @param course
     */
    void removeCourse(Course course);

    /**
     * 更新课程
     * @param course
     */
    void updateCourse(Course course);

    /**
     * 移除课程by id
     *
     * @param id
     */
    void removeCourse(Integer id);

    /**
     * 根据JSON数据来更新课程信息
     *
     * @param course
     * @param Json
     */
    void updateCourseInfoByJson(Course course, String Json);

    /**
     * 获取推荐课程列表
     *
     * @param userId   当前用户ID
     * @param courseId 当前课程ID
     * @return 课程列表
     * // TODO: 2018/4/13 利用jython执行python代码
     */
    List<Course> getRecommendCourses(String userId, String courseId);

    /**
     * 检查当前用户是否参加了该课程
     *
     * @param course
     * @return
     */
    boolean checkEntryCourse(Course course);

    /**
     * 当前用户参加课程
     *
     * @param course
     */
    boolean entryCourse(Course course);

    /**
     * 当前用户退出课程
     *
     * @param course
     */
    boolean exitCourse(Course course);


}
