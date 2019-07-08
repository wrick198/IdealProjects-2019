package com.knowledge_network.circle.dao.impl;

import com.knowledge_network.circle.dao.SectionDAO;
import com.knowledge_network.circle.entity.Section;
import com.knowledge_network.support.base.BaseHibernateDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wshish000 on 18-3-5
 */
@Repository
public class SectionDAOImpl extends BaseHibernateDAO<Section> implements SectionDAO {

    @Override
    public void deleteSection(Section section) {
        delete(section);
    }

    @Override
    public void updateSection(Section section) {
        save(section);
    }

    @Override
    public List<Section> findAllSection() {
        return findAll();
    }

    @Override
    public Section findSectionById(int id) {
        return findById(id);
    }

    @Override
    public Section findByName(String name) {
        return findByUnique("name", name);
    }
}
