package com.knowledge_network.user.dao;

import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.KnowledgeNetworkManager;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by pentonbin on 17-12-14
 */
public interface KnowledgeNetworkManagerDAO {

    /**
     * 根据知识网络管理员用户id获取知识网络管理员对象
     *
     * @param id 知识网络管理员用户id
     * @return 知识网络管理员对象
     */
    KnowledgeNetworkManager findKnowledgeNetworkManagerById(Integer id);

    /**
     * 创建新的知识网络管理员用户
     *
     * @param knowledgeNetworkManager 知识网络管理员用户
     */
    void createKnowledgeNetworkManager(KnowledgeNetworkManager knowledgeNetworkManager);

    /**
     * 根据条件获取知识网络专家分页
     *
     * @param start      起始下标
     * @param rows       每一页行数
     * @param conditions 条件
     * @return
     */
    List<KnowledgeNetworkManager> findKnowledgeNetworkManagerByConditionsOrderPage(int start,
                                                                                   int rows,
                                                                                   Order order,
                                                                                   List<BaseHibernateDAO.Condition> conditions);

    /**
     * 分页获取知识网络专家对象
     *
     * @param order 排序
     * @param start 起始下标
     * @param rows  每一页行数
     * @return 符合条件的用户
     */
    List<KnowledgeNetworkManager> findKnowledgeNetworkManagerByOrderPage(int start, int rows, Order order);

    /**
     * 获取筛选条件后知识网络专家的总数
     *
     * @param conditions 筛选条件，如果为null则返回所有用户数量
     * @return 用户总数
     */
    long getKnowledgeNetworkManagerCountByConditions(List<BaseHibernateDAO.Condition> conditions);

    /**
     * 更新知识网络专家对象
     *
     * @param knowledgeNetworkManager
     */
    void updateKnowledgeNetworkManager(KnowledgeNetworkManager knowledgeNetworkManager);

}
