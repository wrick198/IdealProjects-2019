package com.knowledge_network.user.dao.impl;

import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.dao.LearningPlanDAO;
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
public class LearningPlanDAOImpl extends BaseHibernateDAO<LearningPlan> implements LearningPlanDAO {
    /**
     * 通过Id查找学习计划
     *
     * @param learningPlanId
     * @return
     */
    @Override
    public LearningPlan findLearningPlanById(int learningPlanId) {
        return null;
    }

    /**
     * 通过课程Id查找学习计划
     *
     * @param courseId
     * @return
     */
    @Override
    public List<LearningPlan> findLearningPlaneByCourseId(int courseId) {
        return null;
    }

    /**
     * 通过姓名查找学习计划
     *
     * @param name
     * @return
     */
    @Override
    public List<LearningPlan> findLearningPlansByName(String name) {
        return null;
    }

    /**
     * 查找学习计划的开始时间
     *
     * @param learningPlanId
     * @return
     */
    @Override
    public Timestamp findLearningPlanStartTime(int learningPlanId) {
        return null;
    }

    /**
     * 查找学习计划的结束时间
     *
     * @param learningPlanId
     * @return
     */
    @Override
    public Timestamp findLearningPlanEndTime(int learningPlanId) {
        return null;
    }

    /**
     * 通过学习计划Id查找知识点
     *
     * @param learningPlanId
     * @return
     */
    @Override
    public List<KnowledgePoint> findKnowledgePointsByLearningPlanId(int learningPlanId) {
        return null;
    }

    /**
     * 更新学习计划
     *
     * @param learningPlanId
     * @param map
     * @return
     */
    @Override
    public boolean updateLearningPlanById(int learningPlanId, Map map) {
        return false;
    }

    /**
     * 删除学习计划
     *
     * @param learningPlanId
     * @return
     */
    @Override
    public boolean deleteLearningPlanById(int learningPlanId) {
        return false;
    }
}
