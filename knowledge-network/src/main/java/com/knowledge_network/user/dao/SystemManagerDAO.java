package com.knowledge_network.user.dao;

import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.SystemManager;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by pentonbin on 17-12-14
 */
public interface SystemManagerDAO {

    /**
     * 根据系统管理员用户id获取系统管理员用户
     *
     * @param id 系统管理员用户id
     * @return 系统管理员用户
     */
    public SystemManager findSystemManagerById(Integer id);

    /**
     * 创建系统管理员用户
     *
     * @param systemManager 系统管理员用户
     */
    void createSystemManager(SystemManager systemManager);

    /**
     * 根据条件获取系统管理员分页
     *
     * @param start      起始下标
     * @param rows       每一页行数
     * @param conditions 条件
     * @return
     */
    List<SystemManager> findSystemManagerByConditionsOrderPage(int start, int rows, Order order, List<BaseHibernateDAO.Condition> conditions);

    /**
     * 分页获取系统管理员对象
     *
     * @param order 排序
     * @param start 起始下标
     * @param rows  每一页行数
     * @return 符合条件的用户
     */
    List<SystemManager> findSystemManagerByOrderPage(int start, int rows, Order order);

    /**
     * 获取筛选条件后系统管理员的总数
     *
     * @param conditions 筛选条件，如果为null则返回所有用户数量
     * @return 用户总数
     */
    long getSystemManagerCountByConditions(List<BaseHibernateDAO.Condition> conditions);

    /**
     * 更新系统管理员对象
     *
     * @param systemManager
     */
    void updateSystemManager(SystemManager systemManager);
}
