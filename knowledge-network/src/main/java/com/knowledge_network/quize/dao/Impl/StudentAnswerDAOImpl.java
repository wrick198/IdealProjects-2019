package com.knowledge_network.quize.dao.Impl;

import com.knowledge_network.quize.dao.StudentAnswerDAO;
import com.knowledge_network.quize.entity.AnswerSheet;
import com.knowledge_network.quize.entity.StudentAnswer;
import com.knowledge_network.support.base.BaseHibernateDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/5/3
 **/
@Repository
public class StudentAnswerDAOImpl extends BaseHibernateDAO<StudentAnswer> implements StudentAnswerDAO {
    @Override
    public StudentAnswer findStudentAnswerById(Integer id) {
        return findById(id);
    }

    @Override
    public List<StudentAnswer> findStudentAnswerByAnswerSheet(AnswerSheet answerSheet) {
        return findBy("answerSheet", answerSheet);
    }

    @Override
    public List<StudentAnswer> findStudentAnswerByConditions(List<Condition> conditions) {
        List<Criterion> criteria = new ArrayList<>();
        for (Condition c : conditions) criteria.add(createCriterionByCondition(c));
        return find(criteria);
    }

    @Override
    public void updateStudentAnswer(StudentAnswer studentAnswer) {
        save(studentAnswer);
    }

    @Override
    public void deleteStudentAnswer(StudentAnswer studentAnswer) {
        delete(studentAnswer);
    }
}
