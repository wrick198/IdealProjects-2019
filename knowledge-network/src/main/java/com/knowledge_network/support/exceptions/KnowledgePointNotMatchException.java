package com.knowledge_network.support.exceptions;

import com.knowledge_network.support.base.BaseException;
import com.knowledge_network.support.common.ResponseErrorEnum;

/**
 * ** Created by gongjiangtao on 2018/4/16
 **/
public class KnowledgePointNotMatchException extends BaseException {
    private int id;
    public KnowledgePointNotMatchException(int id) {
        super(ResponseErrorEnum.NO_MATCHING_KNOWLEDGEPOINT);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
