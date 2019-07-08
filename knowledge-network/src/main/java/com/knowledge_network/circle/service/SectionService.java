package com.knowledge_network.circle.service;

import com.knowledge_network.circle.entity.Section;

import java.util.List;

/**
 * Created by wshish000 on 18-3-6
 */
public interface SectionService {

    List<Section> findAll();

    void save(Section section);

    void delete(int id);

    Section findById(int id);

    Section findByName(String name);
}
