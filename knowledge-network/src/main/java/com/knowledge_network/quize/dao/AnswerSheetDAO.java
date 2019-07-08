package com.knowledge_network.quize.dao;

import com.knowledge_network.quize.entity.AnswerSheet;
import com.knowledge_network.quize.entity.Paper;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.Student;
import com.knowledge_network.user.entity.Teacher;

import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/5/3
 * 答题卡DAO接口
 **/
public interface AnswerSheetDAO {
    /**
     * 通过id获取答题卡
     *
     * @param id 待查id
     * @return id对应的答题卡
     */
    AnswerSheet findAnswerSheetById(Integer id);

    /**
     * 查询合格的答题卡
     *
     * @return 合格的答题卡
     */
    List<AnswerSheet> findPassedAnswerSheet();

    /**
     * 获取某学生的答题卡
     *
     * @param student
     * @return
     */
    List<AnswerSheet> findAnswerSheetByStudent(Student student);

    /**
     * 获取某份试卷的全部答题卡
     *
     * @param paper 待查询的paper
     * @return paper对应的全部答题卡
     */
    List<AnswerSheet> findAnswerSheetByPaper(Paper paper);

    /**
     * 获取某位教师改过的答题卡
     *
     * @param teacher 待查询的教师
     * @return 教师改过的答题卡
     */
    List<AnswerSheet> findAnswerSheetByTeacher(Teacher teacher);

    /**
     * 根据条件获取答题卡
     *
     * @param conditions 条件列表
     * @return 符合条件的答题卡
     */
    List<AnswerSheet> findAnswerSheetByConditions(List<BaseHibernateDAO.Condition> conditions);

    /**
     * 更新答题卡
     *
     * @param answerSheet 待更新的答题卡
     */
    void updateAnswerSheet(AnswerSheet answerSheet);

    /**
     * 删除答题卡
     *
     * @param answerSheet 待删除的答题卡
     */
    void deleteAnswerSheet(AnswerSheet answerSheet);
}
