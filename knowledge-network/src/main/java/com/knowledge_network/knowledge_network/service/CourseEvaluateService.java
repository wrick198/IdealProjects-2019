package com.knowledge_network.knowledge_network.service;

import com.knowledge_network.knowledge_network.entity.CourseEvaluate;
import com.knowledge_network.knowledge_network.vo.CourseEvaluateInfoVO;
import com.knowledge_network.support.base.BaseHibernateDAO;

import java.util.List;

public interface CourseEvaluateService {

    /**
     * 根据id获取课程评价
     *
     * @param id
     * @return
     */
    CourseEvaluate getCourseEvaluateById(Integer id);

//    /**
//     * 根据课程ID获取对应课程的评价列表
//     * @param courseId
//     * @return
//     */
//    List<CourseEvaluate> getCourseEvaluateByCourseId(String courseId);

    /**
     * 根据条件筛选课程列表
     *
     * @param start
     * @param row
     * @param condition, 目前只有课程id一个查询条件
     * @return
     */
    List<CourseEvaluate> getCourseEvaluateByCondition(int start, int row, List<BaseHibernateDAO.Condition> condition);

    /**
     * 根据id获取课程评价的信息，封装到对应的VO中
     *
     * @param courseEvaluateId
     * @return
     */
    CourseEvaluateInfoVO getCourseEvaluateInfo(String courseEvaluateId);

    /**
     * 添加课程评价到当前课程
     * // TODO: 2018/4/17 添加后需要重新计算课程的评分
     *
     * @param courseId
     * @param courseEvaluate
     */
    void createCourseEvaluate(Integer courseId, CourseEvaluate courseEvaluate);

    /**
     * 删除该课程评价
     * // TODO: 2018/4/17 删除后需要重新计算课程的评分
     *
     * @param courseEvaluate
     */
    void deleteCourseEvaluate(CourseEvaluate courseEvaluate);

    /**
     * 更新课程评价
     *
     * @param courseEvaluate
     */
    void updateCourseEvaluate(CourseEvaluate courseEvaluate);

    /**
     * VO转换
     *
     * @param courseEvaluate
     * @param courseEvaluateInfoVO
     */
    void initCourseEvaluate(CourseEvaluate courseEvaluate, CourseEvaluateInfoVO courseEvaluateInfoVO);

}
