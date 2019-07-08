package com.knowledge_network.quize.service.Impl;

import com.knowledge_network.knowledge_network.entity.Subject;
import com.knowledge_network.quize.dao.WrongNoteDAO;
import com.knowledge_network.quize.entity.Quize;
import com.knowledge_network.quize.entity.WrongNote;
import com.knowledge_network.quize.service.WrongNoteService;
import com.knowledge_network.support.base.BaseHibernateDAO.Condition;
import com.knowledge_network.support.base.BaseHibernateDAO.ConditionType;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.utils.Asserts;
import com.knowledge_network.user.entity.Student;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/4/24
 **/
@Service
public class WrongNoteServiceImpl implements WrongNoteService {
    @Autowired
    private WrongNoteDAO wrongNoteDAO;

    @Override
    public WrongNote getWrongNoteById(Integer id) {
        return wrongNoteDAO.findWrongNoteById(id);
    }

    @Override
    public List<WrongNote> getWrongNotesByStudentSubjectPage(int start, int rows, List<Condition> conditions) {
        Asserts.notNull(conditions, ResponseErrorEnum.CONDITION_NOT_NULL);
        // 按练习时间降序
        Order order = Order.desc("practiceTime");

        return wrongNoteDAO.findWrongNotesByConditionOrderPage(start, rows, order, conditions);
    }

    @Override
    public long getWrongNoteCountByStudentSubject(Student student, Subject subject) {
        Asserts.notNull(student, ResponseErrorEnum.USER_ID_NOT_NULL);
        Asserts.notNull(subject, ResponseErrorEnum.SUBJECT_ID_NOT_NULL);
        List<Condition> conditions = new ArrayList<>();
        conditions.add(new Condition(ConditionType.EQ, "student", student));
        conditions.add(new Condition(ConditionType.EQ, "subject", subject));

        return wrongNoteDAO.findWrongNoteCountByCondition(conditions);
    }

    @Override
    public List<WrongNote> getWrongNoteByConditions(List<Condition> conditions) {
        Asserts.notNull(conditions, ResponseErrorEnum.CONDITION_NOT_NULL);
        if (conditions.size() < 3) return null;
        return wrongNoteDAO.findWrongNoteByConditions(conditions);
    }

    @Override
    public void updatePracticeTimePlusOne(WrongNote wrongNote) {
        Asserts.notNull(wrongNote, ResponseErrorEnum.WRONG_NOTE_NOT_NULL);
        if (wrongNote.getStudent() != null && wrongNote.getQuize() != null && wrongNote.getSubject() != null) {
            wrongNote.setPracticeTime(wrongNote.getPracticeTime() + 1);
            wrongNoteDAO.updateWrongNote(wrongNote);
        }
    }

    @Override
    public void addQuizeToStudentSubject(Quize quize, Student student, Subject subject) {
        Asserts.notNull(quize, ResponseErrorEnum.QUIZE_NOT_NULL);
        Asserts.notNull(quize, ResponseErrorEnum.SUBJECT_ID_NOT_NULL);
        Asserts.notNull(student, ResponseErrorEnum.USER_ID_NOT_NULL);
        WrongNote wrongNote = new WrongNote();
        wrongNote.setPracticeTime(0);
        wrongNote.setStudent(student);
        wrongNote.setSubject(subject);
        wrongNote.setQuize(quize);
        wrongNoteDAO.updateWrongNote(wrongNote);
    }

    @Override
    public void deleteWrongNote(WrongNote wrongNote) {
        Asserts.notNull(wrongNote, ResponseErrorEnum.WRONG_NOTE_NOT_NULL);
        wrongNoteDAO.deleteWrongNote(wrongNote);
    }
}
