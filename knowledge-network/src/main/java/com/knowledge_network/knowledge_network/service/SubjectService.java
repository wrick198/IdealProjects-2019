package com.knowledge_network.knowledge_network.service;

import com.knowledge_network.knowledge_network.entity.Subject;

import java.util.List;

/**
 * Created by pentonbin on 17-12-16
 */
public interface SubjectService {

    /**
     * 根据科目id获取科目对象
     *
     * @param id 科目id
     * @return 科目对象
     */
    public Subject getSubjectById(Integer id);

    /**
     * 获取所有的科目列表
     *
     * @return
     */
    List<Subject> getAllSubjects();

    /**
     * 通过名称获取科目
     *
     * @param name 科目名称
     * @return 科目对象
     */
    Subject getSubjectByName(String name);
}
