package com.knowledge_network.user.dao;

import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.user.entity.LearningPlan;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by limingcheng on 17-12-7
 * <p>
 * 提供访问学习计划表LearningPlan的DAO
 */
@Repository
public interface LearningPlanDAO {

    /**
     * 通过Id查找学习计划
     *
     * @param learningPlanId
     * @return
     */
    LearningPlan findLearningPlanById(int learningPlanId);

    /**
     * 通过姓名查找学习计划
     *
     * @param name
     * @return
     */
    List<LearningPlan> findLearningPlansByName(String name);

    /**
     * 通过课程Id查找学习计划
     *
     * @param courseId
     * @return
     */
    List<LearningPlan> findLearningPlaneByCourseId(int courseId);

    /**
     * 查找学习计划的开始时间
     *
     * @param learningPlanId
     * @return
     */
    Timestamp findLearningPlanStartTime(int learningPlanId);

    /**
     * 查找学习计划的结束时间
     *
     * @param learningPlanId
     * @return
     */
    Timestamp findLearningPlanEndTime(int learningPlanId);

    /**
     * 通过学习计划Id查找知识点
     *
     * @param learningPlanId
     * @return
     */
    List<KnowledgePoint> findKnowledgePointsByLearningPlanId(int learningPlanId);

    /**
     * 更新学习计划
     *
     * @param learningPlanId
     * @param map
     * @return
     */
    boolean updateLearningPlanById(int learningPlanId, Map map);

    /**
     * 删除学习计划
     *
     * @param learningPlanId
     * @return
     */
    boolean deleteLearningPlanById(int learningPlanId);

}
