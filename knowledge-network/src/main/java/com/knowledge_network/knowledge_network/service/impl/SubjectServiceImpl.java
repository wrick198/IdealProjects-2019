package com.knowledge_network.knowledge_network.service.impl;

import com.knowledge_network.knowledge_network.dao.SubjectDAO;
import com.knowledge_network.knowledge_network.entity.Subject;
import com.knowledge_network.knowledge_network.service.SubjectService;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.utils.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pentonbin on 17-12-16
 */
@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectDAO subjectDAO;

    @Override
    public Subject getSubjectById(Integer id) {
        return subjectDAO.findSubjectById(id);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectDAO.findAllSubjects();
    }

    @Override
    public Subject getSubjectByName(String name) {
        Asserts.notNull(name, ResponseErrorEnum.SUBJECT_NAME_NOT_NULL);
        return subjectDAO.findSubjectByName(name);
    }
}
