package com.knowledge_network.user.service;

import com.knowledge_network.knowledge_network.entity.Course;
import com.knowledge_network.user.entity.LearningPlan;

import java.util.Map;

/**
 * Created by limingcheng on 17-12-7
 * <p>
 * 提供学习计划接口LearningPlanService
 */
public interface LearningPlanService {

    /**
     * 获得学习计划的最后时间
     *
     * @param learningPlan
     * @return
     */
    Integer getLearningPlanLastHour(LearningPlan learningPlan);

    /**
     * 学习计划的课程
     *
     * @param learningPlan
     * @return
     */
    Course getLearningPlanCourse(LearningPlan learningPlan);

    /**
     * 创建学习计划
     *
     * @param learningPlan
     * @return
     */
    boolean createLearningPlan(LearningPlan learningPlan);

    /**
     * 删除学习计划
     *
     * @param learningPlanId
     * @return
     */
    boolean deleteLearnningPlanById(int learningPlanId);

    /**
     * 更新学习计划
     *
     * @param map
     * @return
     */
    boolean updateLearnningPlan(Map<String, Object> map);

    /**
     * 获得学习计划
     *
     * @param userId
     * @param courseId
     * @return
     */
    LearningPlan getLearningPlan(int userId, int courseId);
}
