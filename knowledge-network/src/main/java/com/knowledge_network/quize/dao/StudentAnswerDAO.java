package com.knowledge_network.quize.dao;

import com.knowledge_network.quize.entity.AnswerSheet;
import com.knowledge_network.quize.entity.StudentAnswer;
import com.knowledge_network.support.base.BaseHibernateDAO;

import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/5/3
 **/
public interface StudentAnswerDAO {
    /**
     * 通过id获取学生提交的答案
     *
     * @param id
     * @return
     */
    StudentAnswer findStudentAnswerById(Integer id);

    /**
     * 查找答题卡对应的答案
     *
     * @param answerSheet
     * @return
     */
    List<StudentAnswer> findStudentAnswerByAnswerSheet(AnswerSheet answerSheet);

    /**
     * 根据条件获取学生答案
     *
     * @param conditions
     * @return
     */
    List<StudentAnswer> findStudentAnswerByConditions(List<BaseHibernateDAO.Condition> conditions);

    /**
     * 更新或添加学生答案
     *
     * @param studentAnswer
     */
    void updateStudentAnswer(StudentAnswer studentAnswer);

    /**
     * 删除学生答案
     *
     * @param studentAnswer
     */
    void deleteStudentAnswer(StudentAnswer studentAnswer);
}
