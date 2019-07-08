package com.knowledge_network.quize.dao;

import com.knowledge_network.quize.entity.QuestionType;
import org.aspectj.weaver.patterns.TypePatternQuestions;

/**
 * ** Created by gongjiangtao on 2018/4/16
 * 提供访问QuestionType的DAO
 **/
public interface QuestionTypeDAO {
    /**
     * 通过id查找问题类型
     * @param id
     * @return id对应的问题类型
     */
    QuestionType findQuestionTypeById(int id);

    /**
     * 通过类型查找问题类型
     * @param type 待查类型
     * @return 类型为type的问题类型
     */
    QuestionType findQuestionTypeByType(int type);

    /**
     * 通过名称查询问题类型
     * @param name 待查名称
     * @return 名称为name的问题类型
     */
    QuestionType findQuestionTypeByName(String name);
}
