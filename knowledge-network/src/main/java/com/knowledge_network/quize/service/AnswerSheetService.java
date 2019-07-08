package com.knowledge_network.quize.service;

import com.knowledge_network.quize.entity.AnswerSheet;
import com.knowledge_network.quize.entity.Paper;
import com.knowledge_network.quize.entity.StudentAnswer;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.Student;

import java.util.Date;
import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/5/3
 * 答题卡Service业务层
 **/
public interface AnswerSheetService {
    /**
     * 通过id获取答题卡
     *
     * @param id
     * @return
     */
    AnswerSheet getAnswerSheetById(Integer id);

    /**
     * 通过学生获取答题卡
     *
     * @param student
     * @return
     */
    List<AnswerSheet> getAnswerSheetByStudent(Student student);

    /**
     * 通过试卷获取答题卡
     *
     * @param paper
     * @return
     */
    List<AnswerSheet> getAnswerSheetByPaper(Paper paper);

    /**
     * 通过条件获取答题卡
     *
     * @param conditions
     * @return
     */
    List<AnswerSheet> getAnswerSheetByConditions(List<BaseHibernateDAO.Condition> conditions);

    /**
     * 获取某份答题卡的全部答案
     *
     * @param answerSheet
     * @return
     */
    List<StudentAnswer> getStudentAnswer(AnswerSheet answerSheet);

    /**
     * 更新开始答题时间
     *
     * @param answerSheet
     * @param startTime
     */
    void updateAnswerSheetStartTime(AnswerSheet answerSheet, Date startTime);

    /**
     * 更新结束答题时间
     *
     * @param answerSheet
     * @param endTime
     */
    void updateAnswerSheetEndTime(AnswerSheet answerSheet, Date endTime);

    /**
     * 更新得分
     *
     * @param answerSheet
     */
    Double updateAnswerSheetScore(AnswerSheet answerSheet);

    /**
     * 设置是否通过试卷
     *
     * @param answerSheet
     * @param isPassed
     */
    void updateSetPassed(AnswerSheet answerSheet, boolean isPassed);

    /**
     * 更新答题卡的答案
     *
     * @param answerSheet
     * @param studentAnswers
     */
    void updateStudentAnswer(AnswerSheet answerSheet, List<StudentAnswer> studentAnswers);

    /**
     * 删除答题卡
     *
     * @param answerSheet
     */
    void deleteAnswerSheet(AnswerSheet answerSheet);

    /**
     * 判断答题卡是否合格
     *
     * @param answerSheet
     * @return
     */
    boolean judgePassed(AnswerSheet answerSheet);

    /**
     * 开始测试时创建一张空的答题卡
     *
     * @param student
     * @param paper
     * @param startTime
     */
    AnswerSheet createEmptyAnswerSheet(Student student, Paper paper, Date startTime);

    /**
     * 保存或更新答题卡
     *
     * @param answerSheet
     */
    void saveOrUpdate(AnswerSheet answerSheet);

    /**
     * 检查答题卡是否合法
     *
     * @param answerSheet
     * @return
     */
    boolean isValid(AnswerSheet answerSheet);

    /**
     * 判断数据库中是否有这份答题卡
     *
     * @param answerSheetId
     * @return
     */
    boolean hasAnswerSheet(Integer answerSheetId);
}
