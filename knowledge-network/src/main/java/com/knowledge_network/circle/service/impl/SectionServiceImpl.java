package com.knowledge_network.circle.service.impl;

import com.knowledge_network.circle.dao.SectionDAO;
import com.knowledge_network.circle.entity.Section;
import com.knowledge_network.circle.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wshish000 on 18-3-6
 */
@Service
public class SectionServiceImpl implements SectionService {

    @Autowired
    private SectionDAO sectionDAO;

    @Override
    public List<Section> findAll() {
        return sectionDAO.findAllSection();
    }

    @Override
    public void save(Section section) {
        sectionDAO.updateSection(section);
    }

    @Override
    public void delete(int id) {
        sectionDAO.deleteSection(findById(id));
    }

    @Override
    public Section findById(int id) {
        return sectionDAO.findSectionById(id);
    }

    @Override
    public Section findByName(String name) {
        return sectionDAO.findByName(name);
    }
}
