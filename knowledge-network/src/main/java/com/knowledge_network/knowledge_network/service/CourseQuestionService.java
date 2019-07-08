package com.knowledge_network.knowledge_network.service;

import com.knowledge_network.knowledge_network.entity.CourseQuestion;
import com.knowledge_network.knowledge_network.vo.CourseQuestionInfoVO;
import com.knowledge_network.support.base.BaseHibernateDAO;

import java.util.List;

public interface CourseQuestionService {
    /**
     * 根据id查找课程问题
     *
     * @param id
     * @return
     */
    CourseQuestion getCourseQuestionById(Integer id);

    /**
     * 获取显示到页面上的课程信息
     *
     * @param courseQuestion
     * @return
     */
    CourseQuestionInfoVO getCourseQuestionInfo(CourseQuestion courseQuestion);

    /**
     * 更新课程问题信息
     *
     * @param courseQuestion
     * @param courseQuestionInfoVO
     */
    void updateCourseQuestion(CourseQuestion courseQuestion, CourseQuestionInfoVO courseQuestionInfoVO);

    /**
     * 添加课程问题
     *
     * @param courseId
     * @param courseQuestion
     */
    void createCourseQuestion(Integer courseId, CourseQuestion courseQuestion);

    /**
     * 删除课程问题
     *
     * @param courseQuestion
     */
    void removeCourseQuestion(CourseQuestion courseQuestion);

    /**
     * 根据课程来获取课程问题列表
     *
     * @param start
     * @param row
     * @param conditions
     * @return
     */
    List<CourseQuestion> getCourseQuestionByCondition(int start, int row, List<BaseHibernateDAO.Condition> conditions);

    /**
     * 根据课程获取课程问题总数目
     *
     * @param condition ，查询条件：课程ID
     * @return
     */
    long getCourseQuestionCountByCondition(BaseHibernateDAO.Condition condition);

    /**
     * 根据VO初始化课程问题的信息
     *
     * @param courseQuestion
     * @param courseQuestionInfoVO
     */
    void initCourseQuestionInfo(CourseQuestion courseQuestion, CourseQuestionInfoVO courseQuestionInfoVO);

}
