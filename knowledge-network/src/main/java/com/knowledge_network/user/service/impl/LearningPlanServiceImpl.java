package com.knowledge_network.user.service.impl;

import com.knowledge_network.knowledge_network.entity.Course;
import com.knowledge_network.knowledge_network.service.CourseService;
import com.knowledge_network.user.dao.LearningPlanDAO;
import com.knowledge_network.user.dao.StudentDAO;
import com.knowledge_network.user.entity.LearningPlan;
import com.knowledge_network.user.entity.Student;
import com.knowledge_network.user.service.LearningPlanService;
import com.knowledge_network.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

/**
 * Created by limingcheng on 17-12-7
 * <p>
 * 学习计划表接口LearningPlanService的具体实现
 */
@Service
public class LearningPlanServiceImpl implements LearningPlanService {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private CourseService courseService;

    @Autowired
    private LearningPlanDAO learningPlanDAO;

    @Autowired
    private UserService userService;

    /**
     * 获得学习计划
     *
     * @param userId
     * @param courseId
     * @return
     */
    @Override
    public LearningPlan getLearningPlan(int userId, int courseId) {
        Student student = (Student) userService.getUserById(userId);
        Collection<LearningPlan> learningPlans = student.getLearningPlans();
        LearningPlan userLearningPlan = null;
        for (LearningPlan learningPlan : learningPlans) {
            if (learningPlan.getCourse().getId() != null && learningPlan.getCourse().getId() == courseId) {
                userLearningPlan = learningPlan;
            }
        }
        return userLearningPlan;
    }

    /**
     * 获得学习计划的最后时间
     *
     * @param learningPlan
     * @return
     */
    @Override
    public Integer getLearningPlanLastHour(LearningPlan learningPlan) {
        return null;
    }

    /**
     * 获得学习计划的课程
     *
     * @param learningPlan
     * @return
     */
    @Override
    public Course getLearningPlanCourse(LearningPlan learningPlan) {
        return learningPlan.getCourse();
    }

    /**
     * 创建学习计划
     *
     * @param learningPlan
     * @return
     */
    @Override
    public boolean createLearningPlan(LearningPlan learningPlan) {
        return false;
    }

    /**
     * 删除学习计划
     *
     * @param learningPlanId
     * @return
     */
    @Override
    public boolean deleteLearnningPlanById(int learningPlanId) {
        return false;
    }

    /**
     * 更新学习计划
     *
     * @param map
     * @return
     */
    @Override
    public boolean updateLearnningPlan(Map<String, Object> map) {
        return false;
    }
}
