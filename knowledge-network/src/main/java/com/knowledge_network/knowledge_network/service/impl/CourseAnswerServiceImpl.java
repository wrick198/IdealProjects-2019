package com.knowledge_network.knowledge_network.service.impl;

import com.knowledge_network.knowledge_network.entity.CourseAnswer;
import com.knowledge_network.knowledge_network.service.CourseAnswerService;
import com.knowledge_network.knowledge_network.vo.CourseAnswerInfoVO;
import com.knowledge_network.support.base.BaseHibernateDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseAnswerServiceImpl implements CourseAnswerService {
    @Override
    public CourseAnswer getCourseAnswerById(Integer id) {
        return null;
    }

    @Override
    public List<CourseAnswer> getCourseAnswersByCondition(int start, int rows, BaseHibernateDAO.Condition condition) {
        return null;
    }

    @Override
    public CourseAnswerInfoVO getCourseAnswerInfo(CourseAnswer courseAnswer) {
        return null;
    }
}
