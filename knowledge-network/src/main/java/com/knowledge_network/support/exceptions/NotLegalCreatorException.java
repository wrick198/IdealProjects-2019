package com.knowledge_network.support.exceptions;

import com.knowledge_network.support.base.BaseException;
import com.knowledge_network.support.common.ResponseErrorEnum;

/**
 * ** Created by gongjiangtao on 2018/4/17
 **/
public class NotLegalCreatorException extends BaseException {
    private int id;

    public NotLegalCreatorException(int id) {
        super(ResponseErrorEnum.QUIZE_OR_PAPER_CREATOR_MUST_NOT_A_STUDENT_OR_PARENT);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
