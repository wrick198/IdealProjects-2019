package com.knowledge_network.quize.dao;

import com.knowledge_network.quize.entity.Quize;
import com.knowledge_network.quize.entity.WrongNote;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.Student;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/4/17
 * 提供访问WrongNote的DAO
 **/
@Repository
public interface WrongNoteDAO {
    /**
     * 通过id查找错题记录
     * @param id
     * @return id对应的错题记录
     */
    WrongNote findWrongNoteById(int id);

    /**
     * 通过学生查找错题记录
     * @param student
     * @return 学生对应的错题记录
     */
    List<WrongNote> findWrongNotesByStudent(Student student);

    /**
     * 通过错题查找错题记录
     * @param quize
     * @return quize对应的错题记录
     */
    List<WrongNote> findWrongNotesByQuize(Quize quize);

    /**
     * 获取符合条件的分页WrongNotes记录
     * @param start 开始行号
     * @param rows 每页行数
     * @param order 顺序
     * @param conditions 条件
     * @return 符合条件的分页WrongNotes记录
     */
    List<WrongNote> findWrongNotesByConditionOrderPage(Integer start, Integer rows, Order order, List<BaseHibernateDAO.Condition> conditions);

    /**
     * 获取分页顺序WrongNote记录
     * @param start 开始行号
     * @param rows 每页行数
     * @param order 顺序
     * @return 分页顺序WrongNote记录
     */
    List<WrongNote> findWrongNotesByOrderPage(Integer start, Integer rows, Order order);

    /**
     * 获取符合条件的WrongNote记录数
     * @param conditions 条件
     * @return 符合条件的WrongNote记录数
     */
    long findWrongNoteCountByCondition(List<BaseHibernateDAO.Condition> conditions);

    /**
     * 根据条件（学生、科目、试题）获取错题记录
     * @param conditions 条件
     * @return 符合条件的错题记录
     */
    List<WrongNote> findWrongNoteByConditions(List<BaseHibernateDAO.Condition> conditions);

    /**
     * 更新错题记录
     * @param wrongNote
     */
    void updateWrongNote(WrongNote wrongNote);

    /**
     * 删除错题记录
     * @param wrongNote
     */
    void deleteWrongNote(WrongNote wrongNote);
}
