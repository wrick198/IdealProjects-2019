package com.knowledge_network.support.exceptions;

import com.knowledge_network.support.base.BaseException;
import com.knowledge_network.support.common.ResponseErrorEnum;

/**
 * ** Created by gongjiangtao on 2018/5/3
 **/
public class NoMatchingAnswerSheetException extends BaseException {
    private Integer id;

    public NoMatchingAnswerSheetException(Integer id) {
        super(ResponseErrorEnum.NO_MATCHING_ANSWER_SHEET);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
