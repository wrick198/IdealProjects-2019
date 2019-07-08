package com.knowledge_network.quize.service;

import com.knowledge_network.quize.entity.AnswerSheet;
import com.knowledge_network.quize.entity.StudentAnswer;
import com.knowledge_network.quize.vo.StudentAnswerInfoVO;
import com.knowledge_network.support.base.BaseHibernateDAO;

import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/5/3
 * 学生答案服务类接口
 **/
public interface StudentAnswerService {
    /**
     * 统计分数
     *
     * @param studentAnswers
     * @return
     */
    Double countScore(List<StudentAnswer> studentAnswers);

    /**
     * 通过id获取学生答案
     *
     * @param id
     * @return
     */
    StudentAnswer getStudentAnswerById(Integer id);

    /**
     * 获取某答题卡下的答案
     *
     * @param answerSheet
     * @return
     */
    List<StudentAnswer> getStudentAnswerByAnswerSheet(AnswerSheet answerSheet);

    /**
     * 获取符合条件的学生答案
     *
     * @param conditions
     * @return
     */
    List<StudentAnswer> getStudentAnswerByConditions(List<BaseHibernateDAO.Condition> conditions);

    /**
     * 更新答案得分
     *
     * @param studentAnswer
     */
    void updateEarnedScore(StudentAnswer studentAnswer, Double score);

    /**
     * 更新答案
     *
     * @param studentAnswer
     * @param answer
     */
    void updateAnswer(StudentAnswer studentAnswer, String answer);

    /**
     * 删除学生答案
     *
     * @param studentAnswer
     */
    void deleteStudentAnswer(StudentAnswer studentAnswer);

    /**
     * 根据学生答案信息创建答案
     * @param studentAnswerInfoVO
     * @param answerSheet
     * @return
     */
    StudentAnswer createStudentAnswerByInfoVO(StudentAnswerInfoVO studentAnswerInfoVO, AnswerSheet answerSheet);
}
