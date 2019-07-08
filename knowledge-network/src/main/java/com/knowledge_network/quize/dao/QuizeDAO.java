package com.knowledge_network.quize.dao;

import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.knowledge_network.entity.Subject;
import com.knowledge_network.knowledge_network.entity.Tag;
import com.knowledge_network.quize.entity.Difficulty;
import com.knowledge_network.quize.entity.QuestionType;
import com.knowledge_network.quize.entity.Quize;
import com.knowledge_network.support.base.BaseHibernateDAO.Condition;
import com.knowledge_network.user.entity.Teacher;
import org.hibernate.criterion.Order;

import java.util.Date;
import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/4/13
 * 提供访问Quize的DAO
 **/
public interface QuizeDAO {
    /**
     * 通过id查找试题
     * @param id
     * @return id对应的试题
     */
    Quize findQuizeById(int id);

    /**
     * 通过名称查找试题
     * @param name
     * @return name对应的试题
     */
    List<Quize> findQuizesByName(String name);

    /**
     * 通过创建时间查找试题
     * @param date
     * @return 创建时间对应的试题
     */
    List<Quize> findQuizesByCreateTime(Date date);

    /**
     * 通过出题人查找试题
     * @param creator
     * @return creator对应的试题
     */
    List<Quize> findQuizesByCreator(Teacher creator);

    /**
     * 通过难度查找试题
     * @param difficulty
     * @return difficulty对应的试题类型
     */
    List<Quize> findQuizesByDifficulty(Difficulty difficulty);

    /**
     * 通过科目查找试题
     * @param subject
     * @return subject对应的的试题
     */
    List<Quize> findQuizesBySubject(Subject subject);

    /**
     * 通过问题类型查找试题
     * @param questionType
     * @return questionType对应的试题
     */
    List<Quize> findQuizesByQuestionType(QuestionType questionType);

    /**
     * 通过知识点查找试题
     * @param knowledgePoint
     * @return knowledgePoint对应的试题类型
     */
    List<Quize> findQuizesByKnowledgePoint(KnowledgePoint knowledgePoint);

    /**
     * 通过Tag查找试题
     * @param tag
     * @return tag对应的试题类型
     */
    List<Quize> findQuizesByTag(Tag tag);

    /**
     * 通过分值查找试题
     * @param examingPoint
     * @return examingPoint对应的试题
     */
    List<Quize> findQuizesByExamingPoint(float examingPoint);

    /**
     * 查找可用的试题
     * @return
     */
    List<Quize> findAvailableQuizes();

    /**
     * 查询做过次数最多的试题
     * @return
     */
    Quize findMostExpose();

    /**
     * 查询做对最多的试题
     * @return
     */
    Quize findMostRight();

    /**
     * 查询做错次数最多的试题
     * @return
     */
    Quize findMostWrong();

    /**
     * 根据条件获取分页Quize对象
     * @param start 开始行号
     * @param rows 每页行数
     * @param conditions 条件
     * @param order 顺序
     * @return 符合条件的测试题
     */
    List<Quize> findQuizesByConditionsOrderPage(Integer start, Integer rows, List<Condition> conditions, Order order);

    /**
     * 获取分页Quize对象
     * @param start 开始行号
     * @param rows 每页行数
     * @param order 顺序
     * @return 符合条件的测试题
     */
    List<Quize> findQuizesByOrderPage(Integer start , Integer rows, Order order);

    /**
     * 根据条件获取Quize数量
     * @param conditions 条件
     * @return 符合条件的Quize的数量
     */
    long findQuizeCountByConditions(List<Condition> conditions);

    /**
     * 根据条件获取Quize
     * @param conditions 条件列表
     * @return 符合条件的Quize
     */
    List<Quize> findQuizeByConditions(List<Condition> conditions);

    /**
     * 获取所有Quize
     * @return 所有quize
     */
    List<Quize> findAll();

    /**
     * 更新试题
     * @param quize
     */
    void updateQuize(Quize quize);

    /**
     * 删除试题
     * @param quize
     * @return
     */
    boolean deleteQuize(Quize quize);

    /**
     * 获取Quize总数
     * @return
     */
    long findQuizeCount();
}
