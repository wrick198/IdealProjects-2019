package com.knowledge_network.quize.dao.Impl;

import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.knowledge_network.entity.Subject;
import com.knowledge_network.knowledge_network.entity.Tag;
import com.knowledge_network.quize.dao.QuizeDAO;
import com.knowledge_network.quize.entity.Difficulty;
import com.knowledge_network.quize.entity.QuestionType;
import com.knowledge_network.quize.entity.Quize;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.Teacher;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/4/13
 * 提供访问Quize的DAO
 **/
@Repository
public class QuizeDaoImpl extends BaseHibernateDAO<Quize> implements QuizeDAO {
    /**
     * 通过id查找试题
     *
     * @param id
     * @return
     */
    @Override
    public Quize findQuizeById(int id) {
        return findById(id);
    }

    /**
     * 通过name查找试题
     *
     * @param name
     * @return
     */
    public List<Quize> findQuizesByName(String name) {
        return (List<Quize>) findBy("quizeName", name);
    }

    /**
     * 通过creator查找试题
     *
     * @param creator
     * @return
     */
    @Override
    public List<Quize> findQuizesByCreator(Teacher creator) {
        return findBy("creator", creator);
    }

    /**
     * 通过createTime查找试题
     *
     * @param date
     * @return
     */
    @Override
    public List<Quize> findQuizesByCreateTime(Date date) {
        return findBy("createTime", date);
    }

    /**
     * 通过difficulty查找试题
     *
     * @param difficulty
     * @return
     */
    @Override
    public List<Quize> findQuizesByDifficulty(Difficulty difficulty) {
        return findBy("difficulty", difficulty);
    }

    /**
     * 通过subject查找试题
     *
     * @param subject
     * @return
     */
    @Override
    public List<Quize> findQuizesBySubject(Subject subject) {
        return findBy("subject", subject);
    }

    /**
     * 通过questionType查找试题
     *
     * @param questionType
     * @return
     */
    @Override
    public List<Quize> findQuizesByQuestionType(QuestionType questionType) {
        return findBy("questionType", questionType);
    }

    /**
     * 通过knowledgePoint查找试题
     *
     * @param knowledgePoint
     * @return
     */
    @Override
    public List<Quize> findQuizesByKnowledgePoint(KnowledgePoint knowledgePoint) {
        return findBy("knowledgePoints", knowledgePoint);
    }

    /**
     * 查找可用试题
     *
     * @return
     */
    @Override
    public List<Quize> findAvailableQuizes() {
        return findBy("isAvaliable", 1);
    }

    /**
     * 通过tag查找试题
     *
     * @param tag
     * @return
     */
    @Override
    public List<Quize> findQuizesByTag(Tag tag) {
        return findBy("tags", tag);
    }

    /**
     * 通过分值查找试题
     *
     * @param examingPoint
     * @return
     */
    @Override
    public List<Quize> findQuizesByExamingPoint(float examingPoint) {
        return findBy("examingPoint", examingPoint);
    }

    /**
     * 查找做题次数最多的试题
     *
     * @return
     */
    @Override
    public Quize findMostExpose() {
        return null;
    }

    /**
     * 查找做对次数最多的试题
     *
     * @return
     */
    @Override
    public Quize findMostRight() {
        return null;
    }

    /**
     * 查找做错次数最多的试题
     *
     * @return
     */
    @Override
    public Quize findMostWrong() {
        return null;
    }

    /**
     * 根据条件获取Quize
     *
     * @param start      开始行号
     * @param rows       每页行数
     * @param conditions 条件
     * @param order      顺序
     * @return
     */
    @Override
    public List<Quize> findQuizesByConditionsOrderPage(Integer start, Integer rows, List<Condition> conditions, Order order) {
        return findByOrderConditionsPage(start, rows, order, conditions);
    }

    /**
     * 获取分页Quize
     *
     * @param start 开始行号
     * @param rows  每页行数
     * @param order 顺序
     * @return
     */
    @Override
    public List<Quize> findQuizesByOrderPage(Integer start, Integer rows, Order order) {
        return findByOrderPage(order, start, rows);
    }

    /**
     * 根据条件获取Quize数量
     *
     * @param conditions 条件
     * @return
     */
    @Override
    public long findQuizeCountByConditions(List<Condition> conditions) {
        return findCountByConditions(conditions);
    }

    /**
     * 根据条件获取Quize
     *
     * @param conditions 条件列表
     * @return
     */
    @Override
    public List<Quize> findQuizeByConditions(List<Condition> conditions) {
        Criteria criteria1 = createCriteriaByConditions(conditions);
        List<Criterion> criteria = criteria1.list();
        return find(criteria);
    }

    /**
     * 获取所有Quize
     *
     * @return
     */
    @Override
    public List<Quize> findAll() {
        return findAll();
    }

    /**
     * 更新试题
     *
     * @param quize
     */
    @Override
    public void updateQuize(Quize quize) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(format.format(new Date()));
            save(quize);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    /**
     * 删除试题
     *
     * @param quize
     * @return
     */
    @Override
    public boolean deleteQuize(Quize quize) {
        Quize quizeInDB = findQuizeById(quize.getId());

        if (quizeInDB == null) return false;
        delete(quize);
        return true;
    }

    @Override
    public long findQuizeCount() {
        return findCount();
    }
}
