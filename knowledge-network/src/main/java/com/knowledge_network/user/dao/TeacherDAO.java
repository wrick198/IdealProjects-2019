package com.knowledge_network.user.dao;

import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.Teacher;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by pentonbin on 17-12-14
 */
public interface TeacherDAO {

    /**
     * 根据教师用户id获取教师对象
     *
     * @param id 教师用户id
     * @return 教师对象
     */
    Teacher findTeacherById(Integer id);

    /**
     * 创建新的教师用户
     *
     * @param teacher 教师用户
     */
    void createTeacher(Teacher teacher);

    /**
     * 根据条件获取教师分页
     *
     * @param start      起始下标
     * @param rows       每一页行数
     * @param conditions 条件
     * @return
     */
    List<Teacher> findTeacherByConditionsOrderPage(int start, int rows, Order order, List<BaseHibernateDAO.Condition> conditions);

    /**
     * 分页获取教师对象
     *
     * @param order 排序
     * @param start 起始下标
     * @param rows  每一页行数
     * @return 符合条件的用户
     */
    List<Teacher> findTeacherByOrderPage(int start, int rows, Order order);

    /**
     * 获取筛选条件后教师的总数
     *
     * @param conditions 筛选条件，如果为null则返回所有用户数量
     * @return 用户总数
     */
    long getTeacherCountByConditions(List<BaseHibernateDAO.Condition> conditions);

    /**
     * 保存教师对象
     *
     * @param teacher
     */
    void updateTeacher(Teacher teacher);

    /**
     * 根据用户名查找教师
     *
     * @param username
     * @return
     */
    Teacher findTeacherByUsername(String username);
}
