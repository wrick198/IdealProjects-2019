package com.knowledge_network.support.exceptions;

import com.knowledge_network.support.base.BaseException;
import com.knowledge_network.support.common.ResponseErrorEnum;

/**
 * ** Created by gongjiangtao on 2018/4/18
 **/
public class PaperTypeInvalidException extends BaseException {
    private int id;

    public PaperTypeInvalidException(int id) {
        super(ResponseErrorEnum.PAPER_TYPE_INVALID);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
