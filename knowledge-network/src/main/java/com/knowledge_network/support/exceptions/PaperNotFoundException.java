package com.knowledge_network.support.exceptions;

import com.knowledge_network.support.base.BaseException;
import com.knowledge_network.support.common.ResponseErrorEnum;

/**
 * ** Created by gongjiangtao on 2018/4/18
 **/
public class PaperNotFoundException extends BaseException {
    private int id;

    public PaperNotFoundException(int id) {
        super(ResponseErrorEnum.PAPER_NOT_FOUND);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
