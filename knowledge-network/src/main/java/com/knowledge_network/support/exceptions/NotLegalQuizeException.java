package com.knowledge_network.support.exceptions;

import com.knowledge_network.support.base.BaseException;
import com.knowledge_network.support.common.ResponseErrorEnum;

/**
 * ** Created by gongjiangtao on 2018/4/17
 **/
public class NotLegalQuizeException extends BaseException {
    private int id;

    public NotLegalQuizeException(int id) {
        super(ResponseErrorEnum.NOT_A_LEGAL_QUIZE);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
