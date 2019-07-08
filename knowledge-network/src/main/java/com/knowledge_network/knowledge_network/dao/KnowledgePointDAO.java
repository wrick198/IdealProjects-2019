package com.knowledge_network.knowledge_network.dao;

import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.knowledge_network.entity.Subject;
import com.knowledge_network.support.base.BaseHibernateDAO;
import org.hibernate.criterion.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by wjk on 17-12-4.
 * <p>
 * 知识点管理相关功能服务接口
 */
public interface KnowledgePointDAO {
    /**
     * 根据知识点名称查找知识点
     * <p>
     * 支持模糊查询
     *
     * @param name 知识点名称
     * @return 查找到的知识点
     */
    List<KnowledgePoint> findKnowledgePointsByName(String name);

    /**
     * 根据知识点名称查找知识点
     *
     * @param name 知识点名称
     * @return 查找到的知识点
     */
    KnowledgePoint findKnowledgePointByName(String name);

    /**
     * 根据知识点ID查找知识点
     *
     * @param id 知识点ID
     * @return 查找到的知识点对象
     */
    KnowledgePoint findKnowledgePointByID(int id);

    /**
     * 根据知识点简介查找知识点
     *
     * @param statement 知识点简介
     * @return 查号到的知识点列表
     */

    List<KnowledgePoint> findKnowledgePointByStatement(String statement);

    /**
     * 根据年级查找知识点
     *
     * @param grade 年级
     * @return 查找到的知识点列表
     */
    List<KnowledgePoint> findKnowledgePointByGrade(int grade);

    /**
     * 根据父知识点查找知识点
     *
     * @param parent_id 父知识点的ID
     * @return 查找到的知识点列表
     */
    List<KnowledgePoint> findKnowledgePointByParent(int parent_id);

    /**
     * 更新知识点内容
     *
     * @param map 要更新的知识点信息
     * @return
     */
    void updateKnowledgePoint(int id, Map map);

    /**
     * 删除知识点
     *
     * @param knowledgePoint 要刪除的知识点对象
     * @return 删除是否成功 true代表成功
     */

    void deleteKnowledgePoint(KnowledgePoint knowledgePoint);

    /**
     * 通过subject查找知识点
     *
     * @param subject
     * @return
     */

    List<KnowledgePoint> findKnowledgePointBySubject(Subject subject);

    /**
     * 保存知识点
     *
     * @param knowledgePoint 要保存的知识点对象
     */
    void saveKnowledgePoint(KnowledgePoint knowledgePoint);

    /**
     * @param knowledgePointList
     */

    void updateKnowledgePointList(List<KnowledgePoint> knowledgePointList);

    /**
     * 根据查询条件查询知识点并分页
     *
     * @param start
     * @param rows
     * @param order
     * @param condition
     * @return
     */
    List<KnowledgePoint> getKnowledgePointPageByCondition(int start, int rows, Order order, List<BaseHibernateDAO.Condition> condition);

    /**
     * 获取当
     *
     * @param conditions
     * @return
     */
    long getKnowledgePointCountByCondition(List<BaseHibernateDAO.Condition> conditions);

    /**
     * 通过名称或者tag查找知识点
     *
     * @param name
     * @return
     */
    List<KnowledgePoint> findKnowledgePointsByTag(String name);

}
