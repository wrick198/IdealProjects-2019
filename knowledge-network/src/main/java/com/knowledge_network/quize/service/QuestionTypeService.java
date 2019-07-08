package com.knowledge_network.quize.service;

import com.knowledge_network.quize.entity.QuestionType;

/**
 * ** Created by gongjiangtao on 2018/4/17
 * QuestionType的Service接口
 **/
public interface QuestionTypeService {
    /**
     * 通过问题类型检查答案是否有效
     *
     * @param questionType 问题类型
     * @param answer       答案
     * @return
     */
    boolean checkLegalAnswer(QuestionType questionType, String answer);

    /**
     * 通过id获取问题类型
     *
     * @param id 待查id
     * @return 问题类型对象
     */
    QuestionType getQuestionTypeById(int id);

    /**
     * 通过问题类型名称获取问题类型
     *
     * @param name 待查名称
     * @return 问题类型对象
     */
    QuestionType getQuestionTypeByName(String name);

    /**
     * 通过类型查找问题类型
     *
     * @param type
     * @return
     */
    QuestionType getQuestionTypeByType(int type);

    /**
     * @param questionType
     * @param name
     */
    void updateQuestionTypeName(QuestionType questionType, String name);
}
