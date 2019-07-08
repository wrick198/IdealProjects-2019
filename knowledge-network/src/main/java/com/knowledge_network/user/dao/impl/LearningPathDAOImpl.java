package com.knowledge_network.user.dao.impl;

import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.dao.LearningPathDAO;
import com.knowledge_network.user.entity.LearningPath;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by limingcheng on 17-12-7
 * <p>
 * 提供访问学习轨迹表LearningPath的DAO
 */
@Repository
public class LearningPathDAOImpl extends BaseHibernateDAO<LearningPath> implements LearningPathDAO {

    /**
     * 查找学习轨迹
     *
     * @param learningPathId
     * @return
     */
    @Override
    public LearningPath findLearningPathById(int learningPathId) {
        return (LearningPath) findById(learningPathId);
    }

    /**
     * 更新学习轨迹
     * 通过学生id和课程id确定学习轨迹
     *
     * @param studentId
     * @param courseId
     * @return
     */
    @Override
    public boolean updateLearningPath(int studentId, int courseId) {
        return false;
    }

    /**
     * 删除学习轨迹
     *
     * @param learningPathId
     * @return
     */
    @Override
    public boolean delLearningPath(int learningPathId) {
        LearningPath learningPath = (LearningPath) findById(learningPathId);
        return false;
    }

    @Override
    public List<LearningPath> findLearningPathByConditions(int start, int rows, Order order, List<Condition> conditions) {
        return findByOrderConditionsPage(start, rows, order, conditions);
    }
}
