package com.knowledge_network.quize.dao.Impl;

import com.knowledge_network.quize.dao.HasDoneQuizeDAO;
import com.knowledge_network.quize.entity.HasDoneQuize;
import com.knowledge_network.quize.entity.Quize;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.Student;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/5/2
 **/
@Repository
public class HasDoneQuizeDAOImpl extends BaseHibernateDAO<HasDoneQuize> implements HasDoneQuizeDAO {
    @Override
    public HasDoneQuize findHasDoneQuizeById(Integer id) {
        return findById(id);
    }

    @Override
    public List<HasDoneQuize> findHasDoneQuizeByStudent(Student student) {
        return findBy("student", student);
    }

    @Override
    public List<HasDoneQuize> findHasDoneQuizeByQuize(Quize quize) {
        return findBy("quize", quize);
    }

    @Override
    public HasDoneQuize findByConditions(List<Condition> conditions) {
        Criteria criteria = createCriteriaByConditions(conditions);
        return findUnique(criteria.list());
    }

    @Override
    public void updateHasDoneQuize(HasDoneQuize hasDoneQuize) {
        save(hasDoneQuize);
    }

    @Override
    public void deleteHasDoneQuize(HasDoneQuize hasDoneQuize) {
        delete(hasDoneQuize);
    }
}
