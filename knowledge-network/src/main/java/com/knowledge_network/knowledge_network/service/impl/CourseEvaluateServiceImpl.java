package com.knowledge_network.knowledge_network.service.impl;

import com.knowledge_network.auth.user.LoginUserHolder;
import com.knowledge_network.knowledge_network.dao.CourseEvaluateDAO;
import com.knowledge_network.knowledge_network.entity.Course;
import com.knowledge_network.knowledge_network.entity.CourseEvaluate;
import com.knowledge_network.knowledge_network.service.CourseEvaluateService;
import com.knowledge_network.knowledge_network.service.CourseService;
import com.knowledge_network.knowledge_network.vo.CourseEvaluateInfoVO;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.service.UserService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseEvaluateServiceImpl implements CourseEvaluateService {

    @Autowired
    private CourseEvaluateDAO courseEvaluateDAO;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;

    @Override
    public CourseEvaluate getCourseEvaluateById(Integer id) {
        if (id == null) {
            return null;
        }
        return courseEvaluateDAO.findCourseEvaluateById(id);
    }

    @Override
    public List<CourseEvaluate> getCourseEvaluateByCondition(int start, int row, List<BaseHibernateDAO.Condition> condition) {
        if (start < 0 || row <= 0) {
            return new ArrayList<>();
        }
        Order order = Order.desc("publishTime");
        if (condition != null) {
            return courseEvaluateDAO.findCourseEvaluatesByConditionOrderPage(start, row, order, condition);
        }
        return courseEvaluateDAO.findCourseEvaluatesByOrderPage(start, row, order);
    }

    @Override
    public CourseEvaluateInfoVO getCourseEvaluateInfo(String courseEvaluateId) {
        return null;
    }

    @Override
    public void createCourseEvaluate(Integer courseId, CourseEvaluate courseEvaluate) {
        if (courseEvaluate != null) {
            courseEvaluate.setCourse(courseService.getCourseById(courseId));
            courseEvaluate.setPublishTime(new Timestamp(System.currentTimeMillis()));
            Integer userId = LoginUserHolder.getInstance().getCurrentLoginUser().getUserId();
            courseEvaluate.setUser(userService.getUserById(userId));
            courseEvaluate.setThumbCount(0);
            //重新计算课程评分
            Course course = courseService.getCourseById(courseId);
            long rateCount = course.getEvaluates().size();
            if (rateCount > 0) {
                Float newRate = (course.getRate() * rateCount + courseEvaluate.getRate()) / (rateCount + 1);
                course.setRate(newRate);
            } else {
                course.setRate(courseEvaluate.getRate());
            }
            course.getEvaluates().add(courseEvaluate);
            courseService.updateCourse(course);
            courseEvaluateDAO.updateCourseEvaluate(courseEvaluate);
        }
    }

    @Override
    public void deleteCourseEvaluate(CourseEvaluate courseEvaluate) {
        if (courseEvaluate != null) {
            Course course = courseEvaluate.getCourse();
            Float oldRate = courseEvaluateDAO.findCourseEvaluateById(courseEvaluate.getId()).getRate();
            int evaluateSize = course.getEvaluates().size();
            if (evaluateSize == 1) {
                course.setRate(0F);
            } else {
                course.setRate((course.getRate() * evaluateSize - oldRate) / (evaluateSize - 1));
            }
            course.getEvaluates().remove(courseEvaluate);
            courseService.updateCourse(course);
            courseEvaluateDAO.deleteCourseEvaluate(courseEvaluate);
        }
    }

    @Override
    public void updateCourseEvaluate(CourseEvaluate courseEvaluate) {
        if (courseEvaluate != null) {
            Float oldRate = courseEvaluateDAO.findCourseEvaluateById(courseEvaluate.getId()).getRate();
            Float newRate = courseEvaluate.getRate();
            Course course = courseEvaluate.getCourse();
            course.setRate(oldRate + (newRate - oldRate) / (course.getEvaluates().size()));
            course.getEvaluates().add(courseEvaluate);
            courseService.updateCourse(course);
            courseEvaluateDAO.updateCourseEvaluate(courseEvaluate);
        }
    }

    @Override
    public void initCourseEvaluate(CourseEvaluate courseEvaluate, CourseEvaluateInfoVO courseEvaluateInfoVO) {
        courseEvaluate.setContent(courseEvaluateInfoVO.getContent());
        courseEvaluate.setRate(courseEvaluateInfoVO.getRate());
    }
}
