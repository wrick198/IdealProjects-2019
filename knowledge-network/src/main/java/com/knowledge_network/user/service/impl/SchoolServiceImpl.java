package com.knowledge_network.user.service.impl;

import com.knowledge_network.user.dao.SchoolDAO;
import com.knowledge_network.user.entity.School;
import com.knowledge_network.user.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pentonbin on 17-12-16
 */
@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolDAO schoolDAO;

    @Override
    public School getSchoolById(Integer id) {
        return schoolDAO.findSchoolById(id);
    }
}
