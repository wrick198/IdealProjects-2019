package com.knowledge_network.support.common;

import com.knowledge_network.support.exceptions.AssertException;
import com.knowledge_network.support.exceptions.IllegalRequestException;
import com.knowledge_network.support.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by pentonbin on 17-12-7
 * <p>
 * 全局异常处理类，全局未捕获异常日志打印
 */
@ControllerAdvice
public class RuntimeExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RuntimeExceptionHandler.class);

    // TODO 具体的异常处理与前端确定

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public ResponseResult<String> userNotFoundException(UserNotFoundException userNotFoundException) {
        logger.warn(userNotFoundException.getMessage() + "---->[userId=]" + userNotFoundException.getUserId());
        return ResponseResult.newErrorInstance(userNotFoundException.getError(), null);
    }

    @ExceptionHandler(AssertException.class)
    @ResponseBody
    public ResponseResult<String> assertException(AssertException exception) {
        logger.warn("Bad Request:" + exception.getMessage());
        return ResponseResult.newErrorInstance(exception.getError(), null);
    }

    @ExceptionHandler(IllegalRequestException.class)
    @ResponseBody
    public ResponseResult<String> illegalRequest(IllegalRequestException exception) {
        logger.warn("Illegal request:" + exception.getMessage());
        return ResponseResult.newErrorInstance(exception.getError(), null);
    }

    // TODO：调试需要，先不处理其他异常
}
