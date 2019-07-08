package com.knowledge_network.knowledge_network.service;

import com.knowledge_network.knowledge_network.entity.CourseAnswer;
import com.knowledge_network.knowledge_network.vo.CourseAnswerInfoVO;
import com.knowledge_network.support.base.BaseHibernateDAO;

import java.util.List;

public interface CourseAnswerService {

    /**
     * 根据id获取课程回答
     *
     * @param id
     * @return
     */
    CourseAnswer getCourseAnswerById(Integer id);

    /**
     * 课程回答的分页显示
     *
     * @param start
     * @param rows
     * @param condition——根据问题ID去查找对应回答(前端每一个页面只详细显示一个问题及其回答)
     * @return
     */
    List<CourseAnswer> getCourseAnswersByCondition(int start, int rows, BaseHibernateDAO.Condition condition);

    /**
     * 课程回答封装成为VO类，传输给前端使用
     *
     * @param courseAnswer
     * @return
     */
    CourseAnswerInfoVO getCourseAnswerInfo(CourseAnswer courseAnswer);


}
