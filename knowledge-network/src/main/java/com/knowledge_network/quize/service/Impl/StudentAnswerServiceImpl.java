package com.knowledge_network.quize.service.Impl;

import com.knowledge_network.quize.dao.StudentAnswerDAO;
import com.knowledge_network.quize.entity.AnswerSheet;
import com.knowledge_network.quize.entity.StudentAnswer;
import com.knowledge_network.quize.service.QuizeService;
import com.knowledge_network.quize.service.StudentAnswerService;
import com.knowledge_network.quize.vo.StudentAnswerInfoVO;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.utils.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/5/3
 * 学生答案Service实现
 **/
@Service
public class StudentAnswerServiceImpl implements StudentAnswerService {
    @Autowired
    private StudentAnswerDAO studentAnswerDAO;
    @Autowired
    private QuizeService quizeService;

    @Override
    public Double countScore(List<StudentAnswer> studentAnswers) {
        Asserts.notNull(studentAnswers, ResponseErrorEnum.ANSWER_NOT_NULL);
        Double score = 0.0;
        for (StudentAnswer sa : studentAnswers) {
            score += sa.getEarnedScore();
        }
        return score;
    }

    @Override
    public StudentAnswer getStudentAnswerById(Integer id) {
        Asserts.notNull(id, ResponseErrorEnum.STUDENT_ANSWER_ID_NOT_NULL);
        return studentAnswerDAO.findStudentAnswerById(id);
    }

    @Override
    public List<StudentAnswer> getStudentAnswerByAnswerSheet(AnswerSheet answerSheet) {
        Asserts.notNull(answerSheet, ResponseErrorEnum.ANSWER_SHEET_NOT_NULL);
        return studentAnswerDAO.findStudentAnswerByAnswerSheet(answerSheet);
    }

    @Override
    public List<StudentAnswer> getStudentAnswerByConditions(List<BaseHibernateDAO.Condition> conditions) {
        Asserts.notNull(conditions, ResponseErrorEnum.CONDITION_NOT_NULL);
        return studentAnswerDAO.findStudentAnswerByConditions(conditions);
    }

    @Override
    public void updateEarnedScore(StudentAnswer studentAnswer, Double score) {
        Asserts.notNull(studentAnswer, ResponseErrorEnum.STUDENT_ANSWER_NOT_NULL);
        studentAnswer.setEarnedScore(score);
        studentAnswerDAO.updateStudentAnswer(studentAnswer);
    }

    @Override
    public void updateAnswer(StudentAnswer studentAnswer, String answer) {
        Asserts.notNull(studentAnswer, ResponseErrorEnum.STUDENT_ANSWER_NOT_NULL);
        Asserts.notNull(answer, ResponseErrorEnum.ANSWER_NOT_NULL);
        studentAnswer.setAnswer(answer);
        studentAnswerDAO.updateStudentAnswer(studentAnswer);
    }

    @Override
    public void deleteStudentAnswer(StudentAnswer studentAnswer) {

    }

    @Override
    public StudentAnswer createStudentAnswerByInfoVO(StudentAnswerInfoVO studentAnswerInfoVO, AnswerSheet answerSheet) {
        Asserts.notNull(studentAnswerInfoVO, ResponseErrorEnum.ANSWER_NOT_NULL);
        Asserts.notNull(answerSheet, ResponseErrorEnum.ANSWER_SHEET_NOT_NULL);
        StudentAnswer studentAnswer = new StudentAnswer();

        studentAnswer.setAnswer(studentAnswerInfoVO.getAnswer());
        studentAnswer.setQuize(quizeService.getQuizeById(studentAnswerInfoVO.getQuize().getId()));
        studentAnswer.setEarnedScore(0.0);
        studentAnswer.setAnswerSheet(answerSheet);

        studentAnswerDAO.updateStudentAnswer(studentAnswer);
        return studentAnswer;
    }
}
