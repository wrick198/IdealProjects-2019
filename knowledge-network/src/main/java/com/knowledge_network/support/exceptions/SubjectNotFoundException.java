package com.knowledge_network.support.exceptions;

import com.knowledge_network.support.base.BaseException;
import com.knowledge_network.support.common.ResponseErrorEnum;

/**
 * ** Created by gongjiangtao on 2018/4/17
 **/
public class SubjectNotFoundException extends BaseException {
    private int id;

    public SubjectNotFoundException(int id) {
        super(ResponseErrorEnum.SUBJECT_NOT_FOUND);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
