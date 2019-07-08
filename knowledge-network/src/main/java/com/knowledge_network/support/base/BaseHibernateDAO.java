package com.knowledge_network.support.base;

import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by pentonbin on 17-12-2.
 * <p>
 * BaseDAO的实现类，包含了通用的DAO操作方法
 */
public class BaseHibernateDAO<T> implements BaseDAO<T> {

    private final static Logger logger = Logger.getLogger(BaseHibernateDAO.class);

    @Autowired
    protected SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    private Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void delete(T t) {
        if (t != null) {
            getCurrentSession().delete(t);
        }

    }

    @Override
    public void save(T t) {
        if (t != null) {
            getCurrentSession().saveOrUpdate(t);
            getCurrentSession().flush();
        }

    }

    @Override
    public void save(List<T> ts) {
        if (ts != null) {
            Iterator it = ts.iterator();
            while (it.hasNext()) {  //warning
                getCurrentSession().saveOrUpdate(it.next());
            }
        }

    }

    @Override
    public void persist(T t) {
        if (t != null) {
            getCurrentSession().persist(t);
        }
    }

    @Override
    public T findById(Serializable id) {
        if (id == null) {
            return null;
        }
        return getCurrentSession().get(getEntityClass(), id);
    }


    @Override
    public List<T> findAll() {
        Criteria c = getCurrentSession().createCriteria(getEntityClass());
        List<T> all = null;
        try {
            all = c.list();
        } catch (Exception e) {
            logger.error("BaseHibernateDAO#findAll() throw a exception", e);
            return null;
        }
        return all;
    }

    @Override
    public List<T> findByPage(Integer start, Integer rows) {
        return createCriteriaByOrderConditionsPage(start, rows, null, null).list();
    }

    @Override
    public List<T> findByOrderPage(Order order, Integer start, Integer rows) {
        return createCriteriaByOrderConditionsPage(start, rows, order, null).list();
    }

    @Override
    public List<T> findByConditionsPage(Integer start, Integer rows, List<Condition> conditions) {
        return createCriteriaByOrderConditionsPage(start, rows, null, conditions).list();
    }

    @Override
    public List<T> findByOrderConditionsPage(Integer start, Integer rows, Order order, List<Condition> conditions) {
        return createCriteriaByOrderConditionsPage(start, rows, order, conditions).list();
    }

    @Override
    public T findByUnique(String propertyName, Object value) {
        return (T) createCriteriaBy(propertyName, value).uniqueResult();
    }

    @Override
    public List<T> findBy(String propertyName, Object value) {
        return createCriteriaBy(propertyName, value).list();
    }

    @Override
    public List<T> find(String hql, Object... args) {
        return createQuery(hql, args).list();
    }

    @Override
    public List<T> find(String hql, Map<String, Object> map) {
        return createQuery(hql, map).list();
    }

    @Override
    public T findUnique(String hql, Object... args) {
        return (T) createQuery(hql, args).uniqueResult();
    }

    @Override
    public T findUnique(String hql, Map<String, Object> map) {
        return (T) createQuery(hql, map).uniqueResult();
    }

    @Override
    public List<T> find(List<Criterion> criterions) {
        return createCriteriaByCriterions(criterions).list();
    }

    @Override
    public List<T> findByOrder(Order order, List<Criterion> criterions) {
        return createCriteriaByCriterions(criterions).addOrder(order).list();
    }

    /**
     * 根据Criterion列表获取唯一对象
     *
     * @param criterions
     * @return
     */
    protected T findUnique(List<Criterion> criterions) {
        return (T) createCriteriaByCriterions(criterions).uniqueResult();
    }

    @Override
    public long findCount() {
        return findCountByCriteria(createCriteria());
    }

    /**
     * 获取当前结果的记录数(工具方法)
     *
     * @param c
     * @return
     */
    protected Long findCountByCriteria(Criteria c) {

        @SuppressWarnings("static-access")
        ResultTransformer t = c.ROOT_ENTITY;

        c.setProjection(Projections.rowCount());
        Long result = (Long) c.uniqueResult();

        c.setProjection(null);
        c.setResultTransformer(t);

        return result != null ? result : 0;
    }

    @Override
    public long findCountByConditions(List<Condition> conditions) {
        if (conditions == null) {
            return findCount();
        }
        Criteria criteria = null;
        if (conditions != null || conditions.size() > 0) {
            criteria = createCriteriaByConditions(conditions);
        } else {
            criteria = createCriteria();
        }
        return findCountByCriteria(criteria);
    }

    protected Criteria createCriteria() {
        Criteria c = getCurrentSession().createCriteria(getEntityClass());
        // 二级缓存
        c.setCacheable(true);
        return c;
    }

    protected Criteria createCriteriaBy(String propertyName, Object value) {
        Criteria c = getCurrentSession().createCriteria(getEntityClass());
        c.add(Restrictions.eq(propertyName, value));
        // 二级缓存
        c.setCacheable(true);
        return c;
    }

    protected Criteria createCriteriaByOrderConditionsPage(Integer start, Integer rows, Order order, List<Condition> conditions) {
        // 条件
        Criteria c = createCriteriaByConditions(conditions);
        // 二级缓存
        c.setCacheable(true);
        // 顺序
        if (order != null) {
            c.addOrder(order);
        }
        // 分页
        c.setFirstResult(start);
        c.setMaxResults(rows);
        return c;
    }

    /**
     * 根据Condition对象构建Criterion对象(工具方法)
     *
     * @param condition
     * @return
     */
    protected static Criterion createCriterionByCondition(Condition condition) {
        if (condition != null) {
            ConditionType conditionType = condition.getConditionType();
            String property = condition.getProperty();
            Object value = condition.getValue();
            return createCriterionByCondition(conditionType, property, value);
        }
        return null;
    }


    /**
     * 根据比较类型、属性名和值构建Criterion对象(工具方法)
     *
     * @param conditionType 比较类型
     * @param property
     * @param value
     * @return
     */
    public static Criterion createCriterionByCondition(ConditionType conditionType, String property, Object value) {
        if (conditionType.getOp().equalsIgnoreCase("eq")) {
            return Restrictions.eq(property, value);
        } else if (conditionType.getOp().equalsIgnoreCase("ge")) {
            return Restrictions.ge(property, value);
        } else if (conditionType.getOp().equalsIgnoreCase("gt")) {
            return Restrictions.gt(property, value);
        } else if (conditionType.getOp().equalsIgnoreCase("le")) {
            return Restrictions.le(property, value);
        } else if (conditionType.getOp().equalsIgnoreCase("lt")) {
            return Restrictions.lt(property, value);
        } else if (conditionType.getOp().equalsIgnoreCase("like")) {
            return Restrictions.like(property, value.toString(), MatchMode.ANYWHERE);
        }
        return null;
    }


    /**
     * 根据Criterion列表构建Criteria对象(工具方法)
     *
     * @param criterions
     * @return
     */
    protected Criteria createCriteriaByCriterions(List<Criterion> criterions) {
        Criteria c = getCurrentSession().createCriteria(getEntityClass());
        for (Criterion cri : criterions) {
            c.add(cri);
        }
        // 二级缓存
        c.setCacheable(true);
        return c;
    }

    protected Criteria createCriteriaByConditions(List<Condition> conditions) {
        List<Criterion> criterions = null;
        Criteria c = null;
        if (conditions != null) {
            criterions = new ArrayList<>();
            for (int i = 0; i < conditions.size(); ++i) {
                criterions.add(createCriterionByCondition(conditions.get(i)));
            }
            c = createCriteriaByCriterions(criterions);
        } else {
            c = createCriteria();
        }
        return c;
    }

    /**
     * 根据HQL及参数构建Query对象(工具方法)
     *
     * @param hql
     * @param args
     * @return
     */
    protected Query createQuery(String hql, Object... args) {
        Query query = getCurrentSession().createQuery(hql);
        for (int i = 0; i < args.length; i++) {
            query.setParameter(i, args[i]);
        }
        return query;
    }

    /**
     * 根据HQL及参数构建Query对象(工具方法)
     *
     * @param hql
     * @param map
     * @return
     */
    protected Query createQuery(String hql, Map<String, Object> map) {
        Query query = getCurrentSession().createQuery(hql);
        query.setProperties(map);
        return query;
    }

    /**
     * 根据sql语句创建SQLQuery对象(工具方法)
     *
     * @param sql
     * @return
     */
    protected SQLQuery createSQLQuery(String sql) {
        return getCurrentSession().createSQLQuery(sql);
    }


    /**
     * 比较类型
     */
    public static enum ConditionType {

        EQ("eq"),
        LIKE("like"),
        GT("gt"),
        GE("ge"),
        LT("lt"),
        LE("le");

        private String op;

        ConditionType(String op) {
            this.op = op;
        }

        public String getOp() {
            return op;
        }
    }


    public static class Condition {
        private ConditionType conditionType;
        private String property;
        private Object value;

        public Condition() {
        }

        public Condition(ConditionType conditionType, String property, Object value) {
            this.conditionType = conditionType;
            this.property = property;
            this.value = value;
        }

        public ConditionType getConditionType() {
            return conditionType;
        }

        public void setConditionType(ConditionType conditionType) {
            this.conditionType = conditionType;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }
}