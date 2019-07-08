package com.knowledge_network.support.exceptions;

import com.knowledge_network.support.base.BaseException;
import com.knowledge_network.support.common.ResponseErrorEnum;

/**
 * Created by pentonbin on 18-1-25
 * <p>
 * 断言出错异常
 */
public class AssertException extends BaseException {

    public AssertException(ResponseErrorEnum responseError) {
        super(responseError);
    }

}
