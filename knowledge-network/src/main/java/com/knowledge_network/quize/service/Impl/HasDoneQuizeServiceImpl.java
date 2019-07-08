package com.knowledge_network.quize.service.Impl;

import com.knowledge_network.quize.dao.HasDoneQuizeDAO;
import com.knowledge_network.quize.entity.HasDoneQuize;
import com.knowledge_network.quize.entity.Quize;
import com.knowledge_network.quize.service.HasDoneQuizeService;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.utils.Asserts;
import com.knowledge_network.user.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/5/2
 **/
@Service
public class HasDoneQuizeServiceImpl implements HasDoneQuizeService {
    @Autowired
    private HasDoneQuizeDAO hasDoneQuizeDAO;

    @Override
    public HasDoneQuize getHasDoneQuizeById(Integer id) {
        Asserts.notNull(id, ResponseErrorEnum.STUDENT_HAS_DONE_QUIZE_ID_NOT_NULL);
        return hasDoneQuizeDAO.findHasDoneQuizeById(id);
    }

    @Override
    public List<HasDoneQuize> getHasDoneQuizeByQuize(Quize quize) {
        Asserts.notNull(quize, ResponseErrorEnum.QUIZE_NOT_NULL);
        return hasDoneQuizeDAO.findHasDoneQuizeByQuize(quize);
    }

    @Override
    public HasDoneQuize getHasDoneQuizeByConditions(List<BaseHibernateDAO.Condition> conditions) {
        Asserts.notNull(conditions, ResponseErrorEnum.CONDITION_NOT_NULL);
        return hasDoneQuizeDAO.findByConditions(conditions);
    }

    @Override
    public List<HasDoneQuize> getHasDoneQuizeByStudent(Student student) {
        Asserts.notNull(student, ResponseErrorEnum.USER_ID_NOT_NULL);
        return hasDoneQuizeDAO.findHasDoneQuizeByStudent(student);
    }

    @Override
    public void updatePracticeTimePlusOne(HasDoneQuize hasDoneQuize) {
        Asserts.notNull(hasDoneQuize, ResponseErrorEnum.STUDENT_HAS_DONE_QUIZE_NOT_NULL);
        hasDoneQuize.setPracticeTime(hasDoneQuize.getPracticeTime() + 1);
        hasDoneQuizeDAO.updateHasDoneQuize(hasDoneQuize);
    }

    @Override
    public void updateRightTimePlusOne(HasDoneQuize hasDoneQuize) {
        Asserts.notNull(hasDoneQuize, ResponseErrorEnum.STUDENT_HAS_DONE_QUIZE_NOT_NULL);
        hasDoneQuize.setRightTime(hasDoneQuize.getRightTime() + 1);
        hasDoneQuizeDAO.updateHasDoneQuize(hasDoneQuize);
    }

    @Override
    public void updateWrongTimePlusOne(HasDoneQuize hasDoneQuize) {
        Asserts.notNull(hasDoneQuize, ResponseErrorEnum.STUDENT_HAS_DONE_QUIZE_NOT_NULL);
        hasDoneQuize.setWrongTime(hasDoneQuize.getWrongTime() + 1);
        hasDoneQuizeDAO.updateHasDoneQuize(hasDoneQuize);
    }

    @Override
    public void addHasDoneQuizeRecord(HasDoneQuize hasDoneQuize) {
        Asserts.notNull(hasDoneQuize, ResponseErrorEnum.STUDENT_HAS_DONE_QUIZE_NOT_NULL);
        hasDoneQuizeDAO.updateHasDoneQuize(hasDoneQuize);
    }

    @Override
    public void deleteHasDoneQuizeRecord(HasDoneQuize hasDoneQuize) {
        Asserts.notNull(hasDoneQuize, ResponseErrorEnum.STUDENT_HAS_DONE_QUIZE_NOT_NULL);
        hasDoneQuizeDAO.deleteHasDoneQuize(hasDoneQuize);
    }
}
