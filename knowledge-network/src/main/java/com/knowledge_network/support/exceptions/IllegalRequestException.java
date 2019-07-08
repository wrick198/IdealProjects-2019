package com.knowledge_network.support.exceptions;

import com.knowledge_network.support.base.BaseException;
import com.knowledge_network.support.common.ResponseErrorEnum;

/**
 * Created by pentonbin on 18-4-4
 */
public class IllegalRequestException extends BaseException {

    public IllegalRequestException(ResponseErrorEnum error) {
        super(error);
    }
}
