package com.knowledge_network.quize.dao.Impl;

import com.knowledge_network.quize.dao.AnswerSheetDAO;
import com.knowledge_network.quize.entity.AnswerSheet;
import com.knowledge_network.quize.entity.Paper;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.Student;
import com.knowledge_network.user.entity.Teacher;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/5/3
 * 答题卡DAO实现
 **/
@Repository
public class AnswerSheetDAOImpl extends BaseHibernateDAO<AnswerSheet> implements AnswerSheetDAO {
    @Override
    public AnswerSheet findAnswerSheetById(Integer id) {
        return findById(id);
    }

    @Override
    public List<AnswerSheet> findAnswerSheetByStudent(Student student) {
        return findBy("students", student);
    }

    @Override
    public List<AnswerSheet> findAnswerSheetByPaper(Paper paper) {
        return findBy("paper", paper);
    }

    @Override
    public List<AnswerSheet> findAnswerSheetByTeacher(Teacher teacher) {
        return findBy("teachers", teacher);
    }

    @Override
    public List<AnswerSheet> findPassedAnswerSheet() {
        return findBy("passed", true);
    }

    @Override
    public List<AnswerSheet> findAnswerSheetByConditions(List<Condition> conditions) {
        List<Criterion> criteria = new ArrayList<>();
        for (Condition c : conditions) criteria.add(createCriterionByCondition(c));
        return find(criteria);
    }

    @Override
    public void updateAnswerSheet(AnswerSheet answerSheet) {
        save(answerSheet);
    }

    @Override
    public void deleteAnswerSheet(AnswerSheet answerSheet) {
        delete(answerSheet);
    }
}
