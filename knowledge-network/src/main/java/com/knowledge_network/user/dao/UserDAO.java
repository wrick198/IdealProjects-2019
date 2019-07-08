package com.knowledge_network.user.dao;

import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.User;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by pentonbin on 17-12-2
 * <p>
 * 提供访问用户表user的DAO
 */
public interface UserDAO {

    /**
     * 根据用户id获取用户
     *
     * @param id 用户id
     * @return 获取到的用户
     */
    User findUserById(Integer id);

    /**
     * 根据用户名username获取用户
     *
     * @param username 用户名
     * @return 获取的用户
     */
    User findUserByUsername(String username);

    /**
     * 更新用户
     *
     * @param user 更新的用户
     */
    void updateUser(User user);

    /**
     * 分页获取用户对象
     *
     * @param order 排序
     * @param start 起始下标
     * @param row   每一页行数
     * @return 符合条件的用户
     */
    List<User> findUserByOrderPage(int start, int row, Order order);

    /**
     * 根据条件获取分页用户对象
     *
     * @param start      起始下标
     * @param rows       每一页行数
     * @param conditions 条件
     * @return 符合条件的用户
     */
    List<User> findUserByConditionsOrderPage(int start, int rows, Order order, List<BaseHibernateDAO.Condition> conditions);

    /**
     * 获取筛选条件后用户的总数
     *
     * @param conditions 筛选条件，如果为null则返回所有用户数量
     * @return 用户总数
     */
    long getUserCountByConditions(List<BaseHibernateDAO.Condition> conditions);

    /**
     * 删除某个用户（真实删除）
     * 如果将某个用户置为删除状态，请调用 {@link com.knowledge_network.user.service.UserService#deleteUser(Integer)}
     *
     * @param user
     */
    void removeUser(User user);
}
