package com.knowledge_network.user.dao.impl;

import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.dao.SchoolDAO;
import com.knowledge_network.user.entity.School;
import org.springframework.stereotype.Repository;

/**
 * Created by pentonbin on 17-12-16
 */
@Repository
public class SchoolDAOImpl extends BaseHibernateDAO<School> implements SchoolDAO {

    @Override
    public School findSchoolById(Integer id) {
        return findById(id);
    }
}
