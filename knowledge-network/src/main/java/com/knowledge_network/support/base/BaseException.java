package com.knowledge_network.support.base;

import com.knowledge_network.support.common.ResponseErrorEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pentonbin on 17-12-10
 * <p>
 * 自定义异常的基类（不需要日志打印，在统一异常处理类{@link com.knowledge_network.support.common.RuntimeExceptionHandler}中进行日志打印）
 */
public class BaseException extends RuntimeException {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ResponseErrorEnum error;

    public BaseException(ResponseErrorEnum responseError) {
        super(responseError.getErrorMessage());
        this.error = responseError;
    }

    public BaseException(ResponseErrorEnum responseError, Throwable cause) {
        super(responseError.getErrorMessage(), cause);
        this.error = responseError;
    }

    public ResponseErrorEnum getError() {
        return error;
    }
}
