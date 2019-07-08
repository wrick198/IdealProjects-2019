package com.knowledge_network.quize.service.Impl;

import com.knowledge_network.quize.dao.AnswerSheetDAO;
import com.knowledge_network.quize.entity.AnswerSheet;
import com.knowledge_network.quize.entity.Paper;
import com.knowledge_network.quize.entity.StudentAnswer;
import com.knowledge_network.quize.service.AnswerSheetService;
import com.knowledge_network.quize.service.PaperService;
import com.knowledge_network.quize.service.StudentAnswerService;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.exceptions.NoMatchingAnswerSheetException;
import com.knowledge_network.support.exceptions.PaperNotFoundException;
import com.knowledge_network.support.exceptions.UserNotMatchException;
import com.knowledge_network.support.utils.Asserts;
import com.knowledge_network.user.entity.Student;
import com.knowledge_network.user.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/5/3
 * 答题卡Serivce实现
 **/
@Service
public class AnswerSheetServiceImpl implements AnswerSheetService {
    @Autowired
    private AnswerSheetDAO answerSheetDAO;
    @Autowired
    private StudentService studentService;
    @Autowired
    private PaperService paperService;
    @Autowired
    private StudentAnswerService studentAnswerService;

    @Override
    public AnswerSheet getAnswerSheetById(Integer id) {
        Asserts.notNull(id, ResponseErrorEnum.ANSWER_SHEET_ID_NOT_NULL);
        return answerSheetDAO.findAnswerSheetById(id);
    }

    @Override
    public List<AnswerSheet> getAnswerSheetByStudent(Student student) {
        Asserts.notNull(student, ResponseErrorEnum.USER_NOT_FOUND);
        Student s = studentService.getStudentByUsername(student.getUsername());
        if (!s.equals(student)) throw new UserNotMatchException(String.valueOf(s.getId()));
        List<AnswerSheet> answerSheets = answerSheetDAO.findAnswerSheetByStudent(student);
        return answerSheets;
    }

    @Override
    public List<AnswerSheet> getAnswerSheetByPaper(Paper paper) {
        Asserts.notNull(paper, ResponseErrorEnum.PAPER_NOT_NULL);
        Paper p = paperService.getPaperById(paper.getId());
        if (!p.equals(paper)) throw new PaperNotFoundException(p.getId());
        List<AnswerSheet> answerSheets = answerSheetDAO.findAnswerSheetByPaper(paper);
        return answerSheets;
    }

    @Override
    public List<AnswerSheet> getAnswerSheetByConditions(List<BaseHibernateDAO.Condition> conditions) {
        Asserts.notNull(conditions, ResponseErrorEnum.CONDITION_NOT_NULL);
        List<AnswerSheet> answerSheets = answerSheetDAO.findAnswerSheetByConditions(conditions);
        return answerSheets;
    }

    @Override
    public List<StudentAnswer> getStudentAnswer(AnswerSheet answerSheet) {
        Asserts.notNull(answerSheet, ResponseErrorEnum.ANSWER_SHEET_NOT_NULL);
        Asserts.notNull(answerSheet.getStudentAnswers(), ResponseErrorEnum.ANSWER_NOT_NULL);
        return answerSheet.getStudentAnswers();
    }

    @Override
    public void updateAnswerSheetStartTime(AnswerSheet answerSheet, Date startTime) {
        Asserts.notNull(answerSheet, ResponseErrorEnum.ANSWER_SHEET_NOT_NULL);
        Asserts.notNull(startTime, ResponseErrorEnum.DO_PAPER_START_TIME_NOT_NULL);
        answerSheet.setStartTime(startTime);
        answerSheetDAO.updateAnswerSheet(answerSheet);
    }

    @Override
    public void updateAnswerSheetEndTime(AnswerSheet answerSheet, Date endTime) {
        Asserts.notNull(answerSheet, ResponseErrorEnum.ANSWER_SHEET_NOT_NULL);
        Asserts.notNull(endTime, ResponseErrorEnum.DO_PAPER_END_TIME_NOT_NULL);
        AnswerSheet as = getAnswerSheetById(answerSheet.getId());
        if (!as.equals(answerSheet)) throw new NoMatchingAnswerSheetException(answerSheet.getId());
        answerSheet.setEndTime(endTime);
        answerSheetDAO.updateAnswerSheet(answerSheet);
    }

    @Override
    public Double updateAnswerSheetScore(AnswerSheet answerSheet) {
        Asserts.notNull(answerSheet, ResponseErrorEnum.ANSWER_SHEET_NOT_NULL);
        AnswerSheet as = getAnswerSheetById(answerSheet.getId());
        if (!as.equals(answerSheet)) throw new NoMatchingAnswerSheetException(answerSheet.getId());
        Double score = studentAnswerService.countScore(as.getStudentAnswers());
        as.setScore(score);
        answerSheetDAO.updateAnswerSheet(as);
        return score;
    }

    @Override
    public void updateSetPassed(AnswerSheet answerSheet, boolean isPassed) {
        Asserts.notNull(answerSheet, ResponseErrorEnum.ANSWER_SHEET_NOT_NULL);
        AnswerSheet as = getAnswerSheetById(answerSheet.getId());
        if (!as.equals(answerSheet)) throw new NoMatchingAnswerSheetException(answerSheet.getId());
        as.setPassed(isPassed);
        answerSheetDAO.updateAnswerSheet(as);
    }

    @Override
    public void updateStudentAnswer(AnswerSheet answerSheet, List<StudentAnswer> studentAnswers) {
        Asserts.notNull(answerSheet, ResponseErrorEnum.ANSWER_SHEET_NOT_NULL);
        Asserts.notNull(studentAnswers, ResponseErrorEnum.ANSWER_NOT_NULL);
        answerSheet.setStudentAnswers(studentAnswers);
        answerSheetDAO.updateAnswerSheet(answerSheet);
    }

    @Override
    public void deleteAnswerSheet(AnswerSheet answerSheet) {

    }

    @Override
    public boolean judgePassed(AnswerSheet answerSheet) {
        Asserts.notNull(answerSheet, ResponseErrorEnum.ANSWER_SHEET_NOT_NULL);
        if (answerSheet.getScore() >= answerSheet.getPaper().getPassPoint())
            return true;
        else return false;
    }

    @Override
    public AnswerSheet createEmptyAnswerSheet(Student student, Paper paper, Date startTime) {
        Asserts.notNull(student, ResponseErrorEnum.USER_ID_NOT_NULL);
        Asserts.notNull(paper, ResponseErrorEnum.PAPER_NOT_NULL);
        Asserts.notNull(startTime, ResponseErrorEnum.DO_PAPER_START_TIME_NOT_NULL);
        AnswerSheet answerSheet = new AnswerSheet();
        answerSheet.setPassed(false);
        answerSheet.setStudent(student);
        answerSheet.setPaper(paper);
        answerSheet.setStartTime(startTime);
        answerSheet.setEndTime(null);
        answerSheetDAO.updateAnswerSheet(answerSheet);

        return answerSheet;
    }

    @Override
    public void saveOrUpdate(AnswerSheet answerSheet) {
        Asserts.notNull(answerSheet, ResponseErrorEnum.ANSWER_SHEET_NOT_NULL);
        if (isValid(answerSheet))
            answerSheetDAO.updateAnswerSheet(answerSheet);
    }

    @Override
    public boolean isValid(AnswerSheet answerSheet) {
        // TODO: 实现答题卡正确性性检查
        return true;
    }

    @Override
    public boolean hasAnswerSheet(Integer answerSheetId) {
        if (answerSheetId == null || getAnswerSheetById(answerSheetId) == null) return false;
        return true;
    }
}
