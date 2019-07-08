package com.knowledge_network.user.service;

import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.LearningPath;
import com.knowledge_network.user.entity.Student;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Created by limingcheng on 17-12-7
 * <p>
 * 提供学习轨迹接口LearningPathService
 */
@Service
public interface LearningPathService {

    /**
     * 通过用户id和courseId查找学生的学习轨迹
     *
     * @param studentId
     * @param courseId
     * @return
     */
    LearningPath getLearningPath(int studentId, int courseId);

    /**
     * 通过leariningPathId查找学生学习轨迹
     *
     * @param learningPathId
     * @return
     */
    LearningPath getLearningPathById(int learningPathId);

    /**
     * 获得学习轨迹的所有学习知识点
     *
     * @param learningPathId
     * @return
     */
    Collection<KnowledgePoint> getLearningPathKnowledgePoints(int learningPathId);

    /**
     * 更新课程学习轨迹
     *
     * @param learningPathId
     * @param knowledgePointId
     * @return
     */
    boolean updateLearningPathKnowledgePoints(int learningPathId, int knowledgePointId);

    /**
     * 删除学习轨迹
     *
     * @param learningPathId
     * @return
     */
    boolean deleteLearningPath(int learningPathId);

    /**
     * 根据条件获取学习轨迹
     *
     * @param start      起始下标
     * @param rows       页面数量
     * @param order      顺序
     * @param conditions 条件
     * @return
     * @author pentonbin
     */
    List<LearningPath> getLearningPathByOrderConditionsPage(int start, int rows, Order order, List<BaseHibernateDAO.Condition> conditions);

    /**
     * 分页获取学生最近的学习轨迹列表
     *
     * @param student 学生
     * @param start   分页起始下标
     * @param rows    分页行数
     * @return
     */
    List<LearningPath> getRecentLearningPath(Student student, int start, int rows);
}
