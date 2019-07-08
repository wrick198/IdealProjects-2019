package com.knowledge_network.circle.dao;

import com.knowledge_network.circle.entity.Section;

import java.util.List;

/**
 * Created by wshish000 on 18-3-5
 */
public interface SectionDAO {

    void deleteSection(Section section);

    void updateSection(Section section);

    List<Section> findAllSection();

    Section findSectionById(int id);

    Section findByName(String name);
}
