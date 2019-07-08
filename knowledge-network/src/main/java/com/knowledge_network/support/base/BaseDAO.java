package com.knowledge_network.support.base;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by pentonbin on 17-12-2.
 * <p>
 * 通用的DAO操作方法
 */
public interface BaseDAO<T> {

    /**
     * 添加或修改对象
     *
     * @param t 需要修改或者增加的对象
     */
    void save(T t);

    /**
     * 持久化对象
     *
     * @param t 需要持久化的对象
     */
    void persist(T t);

    /**
     * 根据主键获取对象
     *
     * @param id 想获取对象的主键id
     * @return 查找到的对象
     */
    T findById(Serializable id);

    /**
     * 获取全部对象
     *
     * @return 所有对象
     */
    List<T> findAll();

    /**
     * 根据分页参数获取对象
     *
     * @param start 起始行数
     * @param rows  每页显示的行数
     * @return 分页查找到的对象集合
     */
    List<T> findByPage(Integer start, Integer rows);

    /**
     * 根据排序，并分页获取对象
     *
     * @param order 排序方法
     * @param start 起始行数
     * @param rows  每页显示的行数
     * @return
     */
    List<T> findByOrderPage(Order order, Integer start, Integer rows);

    /**
     * 添加条件获取分页后的对象列表
     *
     * @param start      分页起始下标
     * @param rows       分页行数
     * @param conditions 分页条件
     * @return
     */
    List<T> findByConditionsPage(Integer start, Integer rows, List<BaseHibernateDAO.Condition> conditions);

    /**
     * 根据排序、条件获取分页后的对象列表
     *
     * @param start      分页起始下标
     * @param rows       分页行数
     * @param order      排序
     * @param conditions 分页条件
     * @return
     */
    List<T> findByOrderConditionsPage(Integer start, Integer rows, Order order, List<BaseHibernateDAO.Condition> conditions);

    /**
     * 根据属性名和值获取唯一的对象
     *
     * @param propertyName 类属性名（非数据库列名）
     * @param value        类属性的值
     * @return 查找到的对象
     */
    T findByUnique(String propertyName, Object value);

    /**
     * 根据属性名和值获取对象集合
     *
     * @param propertyName 类属性名（非数据库列名）
     * @param value        类属性的值
     * @return 查找到的对象集合
     */
    List<T> findBy(String propertyName, Object value);

    /**
     * 根据HQL获取对象集合
     *
     * @param hql  HQL语句
     * @param args HQL语句的参数
     * @return 查找到的对象集合
     */
    List<T> find(String hql, Object... args);

    /**
     * 根据HQL获取对象集合，HQL使用的是引用占位符
     *
     * @param hql HQL语句
     * @param map
     * @return 查找到的对象集合
     */
    List<T> find(String hql, Map<String, Object> map);

    /**
     * 根据HQL获取唯一对象
     *
     * @param hql  HQL语句
     * @param args HQL语句的参数
     * @return 查找到的对象
     */
    T findUnique(String hql, Object... args);

    /**
     * 根据HQL获取唯一对象，HQL使用的是引用占位符
     *
     * @param hql HQL语句
     * @param map
     * @return 查找到的对象
     */
    T findUnique(String hql, Map<String, Object> map);

    /**
     * 根据Criterion列表获取集合对象
     *
     * @param criterions 条件列表
     * @return 查找到的对象
     */
    List<T> find(List<Criterion> criterions);

    /**
     * 批量添加或修改对象
     * 使用iterator方法遍历List
     *
     * @param ts 需要删除或者修改的对象列表
     */
    void save(List<T> ts);

    /**
     * 删除对象
     *
     * @param t 需要删除的对象
     */
    void delete(T t);

    /**
     * 根据Criterion列表获取集合对象，并按照Order对象对取得的集合对象排序
     *
     * @param order      排序方法
     * @param criterions 条件列表
     * @return 已排序的的查找到的对象
     */
    List<T> findByOrder(Order order, List<Criterion> criterions);

    /**
     * 获取所有对象数目
     *
     * @return 数目
     */
    long findCount();

    /**
     * 根据条件获取对象的数目
     *
     * @param conditions 条件
     * @return 数目
     */
    long findCountByConditions(List<BaseHibernateDAO.Condition> conditions);
}
