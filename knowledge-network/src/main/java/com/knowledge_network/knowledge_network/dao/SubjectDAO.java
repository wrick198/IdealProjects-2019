package com.knowledge_network.knowledge_network.dao;

import com.knowledge_network.knowledge_network.entity.Subject;

import java.util.List;

/**
 * Created by pentonbin on 17-12-16
 */
public interface SubjectDAO {

    /**
     * 根据科目id获取科目对象
     *
     * @param id 科目id
     * @return 科目对象
     */
    Subject findSubjectById(Integer id);

    /**
     * 获取所有的科目列表
     *
     * @return
     */
    List<Subject> findAllSubjects();

    /**
     * 根绝科目名称获取科目对象
     *
     * @param name 科目名称
     * @return 科目对象
     */
    Subject findSubjectByName(String name);

}
