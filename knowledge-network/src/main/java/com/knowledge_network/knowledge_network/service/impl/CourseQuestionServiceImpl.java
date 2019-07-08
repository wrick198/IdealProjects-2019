package com.knowledge_network.knowledge_network.service.impl;

import com.knowledge_network.auth.user.LoginUserHolder;
import com.knowledge_network.knowledge_network.dao.CourseQuestionDAO;
import com.knowledge_network.knowledge_network.entity.CourseQuestion;
import com.knowledge_network.knowledge_network.service.CourseQuestionService;
import com.knowledge_network.knowledge_network.service.CourseService;
import com.knowledge_network.knowledge_network.vo.CourseQuestionInfoVO;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.service.UserService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseQuestionServiceImpl implements CourseQuestionService {
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseQuestionDAO courseQuestionDAO;


    @Override
    public void createCourseQuestion(Integer courseId, CourseQuestion courseQuestion) {
        if (courseQuestion != null) {
            courseQuestion.setPublishTime(new Timestamp(System.currentTimeMillis()));
            Integer userId = LoginUserHolder.getInstance().getCurrentLoginUser().getUserId();
            courseQuestion.setAsker(userService.getUserById(userId));
            courseQuestion.setCourse(courseService.getCourseById(courseId));
            courseQuestionDAO.updateCourseQuestion(courseQuestion);
        }


    }

    @Override
    public void removeCourseQuestion(CourseQuestion courseQuestion) {
        courseQuestionDAO.deleteCourseQuestion(courseQuestion);
    }

    @Override
    public List<CourseQuestion> getCourseQuestionByCondition(int start, int row, List<BaseHibernateDAO.Condition> conditions) {
        if (start < 0 || row <= 0) {
            return new ArrayList<>();
        }
        Order order = Order.desc("publishTime");
        if (conditions.get(0).getProperty().equalsIgnoreCase("course")) {
            if (courseService.getCourseById((Integer) conditions.get(0).getValue()) != null){
                return courseQuestionDAO.findCourseQuestionsByOrderCondition(start, row, order, conditions);
            }
            else {
                //没有这个课程
                return null;
            }
        }
        return courseQuestionDAO.findCourseQuestionsByOrder(order, start, row);
    }

    @Override
    public long getCourseQuestionCountByCondition(BaseHibernateDAO.Condition condition) {
        return 0;
    }

    @Override
    public void initCourseQuestionInfo(CourseQuestion courseQuestion, CourseQuestionInfoVO courseQuestionInfoVO) {
        if (courseQuestion != null && courseQuestionInfoVO != null) {
            courseQuestion.setContent(courseQuestionInfoVO.getContent());
            courseQuestion.setTitle(courseQuestionInfoVO.getTitle());
        }
    }

    @Override
    public CourseQuestion getCourseQuestionById(Integer id) {
        if (id != null){
            return courseQuestionDAO.findCourseQuestionById(id);
        }
        return null;
    }

    @Override
    public CourseQuestionInfoVO getCourseQuestionInfo(CourseQuestion courseQuestion) {
        return new CourseQuestionInfoVO(courseQuestion);
    }

    @Override
    public void updateCourseQuestion(CourseQuestion courseQuestion, CourseQuestionInfoVO courseQuestionInfoVO) {
        initCourseQuestionInfo(courseQuestion, courseQuestionInfoVO);
        courseQuestionDAO.updateCourseQuestion(courseQuestion);
    }


}
