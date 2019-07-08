package com.knowledge_network.user.dao;

import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.Parent;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by pentonbin on 17-12-14
 */
public interface ParentDAO {

    /**
     * 根据id获取家长对象
     *
     * @param id 家长id
     * @return 家长对象
     */
    Parent findParentById(Integer id);

    /**
     * 根据家长用户名查找家长对象
     *
     * @param username 家长用户名
     * @return 家长对象
     */
    Parent findParentByUsername(String username);

    /**
     * 创建新的家长用户
     *
     * @param parent 新的家长用户
     */
    void createParent(Parent parent);

    /**
     * 根据条件获取家长分页
     *
     * @param start      起始下标
     * @param rows       每一页行数
     * @param conditions 条件
     * @return
     */
    List<Parent> findParentByConditionsOrderPage(int start, int rows, Order order, List<BaseHibernateDAO.Condition> conditions);

    /**
     * 分页获取学生对象
     *
     * @param order 排序
     * @param start 起始下标
     * @param rows  每一页行数
     * @return 符合条件的用户
     */
    List<Parent> findParentByOrderPage(int start, int rows, Order order);

    /**
     * 获取筛选条件后学生的总数
     *
     * @param conditions 筛选条件，如果为null则返回所有用户数量
     * @return 用户总数
     */
    long getParentCountByConditions(List<BaseHibernateDAO.Condition> conditions);

    /**
     * 保存家长对象
     *
     * @param parent
     */
    void updateParent(Parent parent);
}
