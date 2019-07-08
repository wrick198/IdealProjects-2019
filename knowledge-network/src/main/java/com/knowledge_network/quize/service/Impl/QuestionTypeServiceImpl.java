package com.knowledge_network.quize.service.Impl;

import com.knowledge_network.quize.dao.QuestionTypeDAO;
import com.knowledge_network.quize.entity.QuestionType;
import com.knowledge_network.quize.service.QuestionTypeService;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.exceptions.NotLegalAnswerException;
import com.knowledge_network.support.utils.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ** Created by gongjiangtao on 2018/4/17
 * QuestionTypeService接口的实现
 **/
@Service
public class QuestionTypeServiceImpl implements QuestionTypeService {
    @Autowired
    private QuestionTypeDAO questionTypeDAO;

    /**
     * 检查答案是否合法
     *
     * @param questionType 问题类型
     * @param answer       答案
     * @return
     */
    @Override
    public boolean checkLegalAnswer(QuestionType questionType, String answer) {
        Asserts.notNull(answer, ResponseErrorEnum.ANSWER_NOT_NULL);
        if (answer.isEmpty()) throw new NotLegalAnswerException(answer);
        if (questionType.getQuestionType() == QuestionType.SINGLE_CHOSEN_QUESTION && answer.length() > 1) {
            // TODO:利用正则表达式检查单选题中只出现A、B、C、D四个选项
            return false;
        }
        return true;
    }

    @Override
    public QuestionType getQuestionTypeById(int id) {
        return questionTypeDAO.findQuestionTypeById(id);
    }

    @Override
    public QuestionType getQuestionTypeByName(String name) {
        return questionTypeDAO.findQuestionTypeByName(name);
    }

    @Override
    public QuestionType getQuestionTypeByType(int type) {
        return questionTypeDAO.findQuestionTypeByType(type);
    }

    @Override
    public void updateQuestionTypeName(QuestionType questionType, String name) {

    }
}
