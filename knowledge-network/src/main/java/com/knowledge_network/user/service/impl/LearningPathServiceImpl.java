package com.knowledge_network.user.service.impl;

import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.dao.LearningPathDAO;
import com.knowledge_network.user.entity.LearningPath;
import com.knowledge_network.user.entity.Student;
import com.knowledge_network.user.service.LearningPathService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by limingcheng on 17-12-7
 * <p>
 * 学习轨迹表接口LearningPathService的具体实现
 */
@Service
public class LearningPathServiceImpl implements LearningPathService {

    @Autowired
    private LearningPathDAO learningPathDAO;

    /**
     * 通过用户id和courseId查找学生的learningPathId
     *
     * @param studentId
     * @param courseId
     * @return
     */
    @Override
    public LearningPath getLearningPath(int studentId, int courseId) {
        return null;
    }

    /**
     * 通过leariningPathId查找学生学习轨迹
     *
     * @param learningPathId
     * @return
     */
    @Override
    public LearningPath getLearningPathById(int learningPathId) {
        return learningPathDAO.findLearningPathById(learningPathId);
    }

    /**
     * 获得学习轨迹的所有学习知识点
     *
     * @param learningPathId
     * @return
     */
    @Override
    public Collection<KnowledgePoint> getLearningPathKnowledgePoints(int learningPathId) {
        LearningPath learningPath = learningPathDAO.findLearningPathById(learningPathId);
        /*Collections.sort(learningPath.getKnowledgePoints(),sort);*/
        return learningPath.getKnowledgePoints();
    }
    /**
     * 学习过的知识点按时间的比较器
     */
    /*public class sortClass implements Comparator {
        public int compare(Object arg0, Object arg1) {
            KnowledgePoint knowledgePoint = (KnowledgePoint) arg0;
            KnowledgePoint knowledgePoint1 = (knowledgePoint) arg1;
            int flag = knowledgePoint.getStartTime().compareTo(knowledgePoint1.getStartTime());
            return flag;
        }
    }*/

    /**
     * 更新课程学习轨迹
     *
     * @param learningPathId
     * @param knowledgePointId
     * @return
     */
    @Override
    public boolean updateLearningPathKnowledgePoints(int learningPathId, int knowledgePointId) {
        return false;
    }

    /**
     * 删除学习轨迹
     *
     * @param learningPathId
     * @return
     */
    @Override
    public boolean deleteLearningPath(int learningPathId) {
        return false;
    }

    @Override
    public List<LearningPath> getLearningPathByOrderConditionsPage(int start, int rows, Order order, List<BaseHibernateDAO.Condition> conditions) {
        return learningPathDAO.findLearningPathByConditions(start, rows, order, conditions);
    }

    @Override
    public List<LearningPath> getRecentLearningPath(Student student, int start, int rows) {
        Order order = Order.asc("lastLearningTime");
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        conditions.add(new BaseHibernateDAO.Condition(
                BaseHibernateDAO.ConditionType.EQ, "student", student));
        return getLearningPathByOrderConditionsPage(start, rows, order, conditions);
    }
}
