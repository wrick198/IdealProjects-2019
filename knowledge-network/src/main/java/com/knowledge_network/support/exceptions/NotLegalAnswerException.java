package com.knowledge_network.support.exceptions;

import com.knowledge_network.support.base.BaseException;
import com.knowledge_network.support.common.ResponseErrorEnum;

/**
 * ** Created by gongjiangtao on 2018/4/17
 **/
public class NotLegalAnswerException extends BaseException {
    private String answer;

    public NotLegalAnswerException(String answer) {
        super(ResponseErrorEnum.NOT_A_LEGAL_ANSWER);
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }
}
