package com.knowledge_network.user.dao;

import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.LearningPath;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by limingcheng on 17-12-7
 * <p>
 * 提供访问学习轨迹表LearningPath的DAO
 */
public interface LearningPathDAO {

    /**
     * 查找学习轨迹
     *
     * @param learningPathId
     * @return
     */
    LearningPath findLearningPathById(int learningPathId);

    /**
     * 更新学习轨迹
     *
     * @param studentId
     * @param courseId
     * @return
     */
    boolean updateLearningPath(int studentId, int courseId);

    /**
     * 删除学习轨迹
     *
     * @param learningPathId
     * @return
     */
    boolean delLearningPath(int learningPathId);

    /**
     * 根据条件获取学习轨迹
     *
     *
     * @param start
     * @param rows
     *@param order
     * @param conditions 条件  @return
     * @author pentonbin
     */
    List<LearningPath> findLearningPathByConditions(int start, int rows, Order order, List<BaseHibernateDAO.Condition> conditions);
}
