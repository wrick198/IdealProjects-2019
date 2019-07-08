package com.knowledge_network.quize.dao.Impl;

import com.knowledge_network.quize.dao.WrongNoteDAO;
import com.knowledge_network.quize.entity.Quize;
import com.knowledge_network.quize.entity.WrongNote;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.Student;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/4/17
 * 提供访问WrongNote的DAO
 **/
@Repository
public class WrongNoteDAOImpl extends BaseHibernateDAO<WrongNote> implements WrongNoteDAO {
    /**
     * 通过id查找错题记录
     * @param id
     * @return
     */
    @Override
    public WrongNote findWrongNoteById(int id) {
        return findById(id);
    }

    /**
     * 通过quize查找错题记录
     * @param quize
     * @return
     */
    @Override
    public List<WrongNote> findWrongNotesByQuize(Quize quize) {
        return findBy("quize", quize);
    }

    /**
     * 通过student查找错题记录
     * @param student
     * @return
     */
    @Override
    public List<WrongNote> findWrongNotesByStudent(Student student) {
        return findBy("student", student);
    }

    /**
     * 获取符合条件的分页WrongNote记录
     * @param start 开始行号
     * @param rows 每页行数
     * @param order 顺序
     * @param conditions 条件
     * @return
     */
    @Override
    public List<WrongNote> findWrongNotesByConditionOrderPage(Integer start, Integer rows, Order order, List<Condition> conditions) {
        return findByOrderConditionsPage(start, rows, order, conditions);
    }

    /**
     * 获取分页顺序WrongNote记录
     * @param start 开始行号
     * @param rows 每页行数
     * @param order 顺序
     * @return
     */
    @Override
    public List<WrongNote> findWrongNotesByOrderPage(Integer start, Integer rows, Order order) {
        return findByOrderPage(order, start, rows);
    }

    /**
     * 获取符合条件的WrongNote记录的数量
     * @param conditions 条件
     * @return
     */
    @Override
    public long findWrongNoteCountByCondition(List<Condition> conditions) {
        return findCountByConditions(conditions);
    }

    @Override
    public List<WrongNote> findWrongNoteByConditions(List<Condition> conditions) {
        List<Criterion> criteria = new ArrayList<>();
        for (Condition c : conditions) criteria.add(createCriterionByCondition(c));
        return find(criteria);
    }

    /**
     * 更新错题记录
     * @param wrongNote
     */
    @Override
    public void updateWrongNote(WrongNote wrongNote) {
        save(wrongNote);
    }

    /**
     * 删除做题记录
     * @param wrongNote
     */
    @Override
    public void deleteWrongNote(WrongNote wrongNote) {
        delete(wrongNote);
    }
}
