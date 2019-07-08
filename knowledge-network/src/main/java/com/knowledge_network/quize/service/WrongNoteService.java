package com.knowledge_network.quize.service;

import com.knowledge_network.knowledge_network.entity.Subject;
import com.knowledge_network.quize.entity.Quize;
import com.knowledge_network.quize.entity.WrongNote;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.Student;

import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/4/24
 * 错题记录业务层接口
 **/
public interface WrongNoteService {
    /**
     * 根据id获取错题记录
     *
     * @param id 待查id
     * @return id对应的错题记录
     */
    WrongNote getWrongNoteById(Integer id);

    /**
     * 获取某学生某一科目的所有错题记录
     *
     * @param conditions 条件（student和subject）
     * @param start      开始行号
     * @param rows       每页行数
     * @return 该学生该科目的所有错题记录
     */
    List<WrongNote> getWrongNotesByStudentSubjectPage(int start, int rows, List<BaseHibernateDAO.Condition> conditions);

    /**
     * 获取某学生某科目错题记录数量
     *
     * @param student 待查学生
     * @param subject 待查科目
     * @return 该学生该科目错题记录数量
     */
    long getWrongNoteCountByStudentSubject(Student student, Subject subject);

    /**
     * 根据条件（学生、科目、习题）获取错题记录
     *
     * @param conditions 条件列表
     * @return 符合条件的错题记录
     */
    List<WrongNote> getWrongNoteByConditions(List<BaseHibernateDAO.Condition> conditions);

    /**
     * 错题记录练习次数加一
     *
     * @param wrongNote 待修改的错题记录对象
     */
    void updatePracticeTimePlusOne(WrongNote wrongNote);

    /**
     * 向某学生的某科目错题本添加错题
     *
     * @param quize   待添加的错题
     * @param student 需要加入错题的学生
     * @param subject 需要加入错题的科目
     */
    void addQuizeToStudentSubject(Quize quize, Student student, Subject subject);

    /**
     * 删除错题记录
     *
     * @param wrongNote 错题记录
     */
    void deleteWrongNote(WrongNote wrongNote);
}
