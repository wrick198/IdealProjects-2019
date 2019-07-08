package com.knowledge_network.knowledge_network.dao.impl;

import com.knowledge_network.knowledge_network.dao.SubjectDAO;
import com.knowledge_network.knowledge_network.entity.Subject;
import com.knowledge_network.support.base.BaseHibernateDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pentonbin on 17-12-16
 */
@Repository
public class SubjectDAOImpl extends BaseHibernateDAO<Subject> implements SubjectDAO {

    @Override
    public Subject findSubjectById(Integer id) {
        return findById(id);
    }

    @Override
    public List<Subject> findAllSubjects() {
        return findAll();
    }

    @Override
    public Subject findSubjectByName(String name) {
        return findByUnique("name", name);
    }

}
