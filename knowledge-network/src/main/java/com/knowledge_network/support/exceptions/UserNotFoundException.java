package com.knowledge_network.support.exceptions;

import com.knowledge_network.support.base.BaseException;
import com.knowledge_network.support.common.ResponseErrorEnum;

/**
 * Created by pentonbin on 17-12-10
 * <p>
 * 用户不存在异常
 */
public class UserNotFoundException extends BaseException {

    private String userId;

    public UserNotFoundException(String userId) {
        super(ResponseErrorEnum.USER_NOT_FOUND);
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
