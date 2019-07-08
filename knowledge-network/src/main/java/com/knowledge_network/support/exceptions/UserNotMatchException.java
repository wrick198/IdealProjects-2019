package com.knowledge_network.support.exceptions;

import com.knowledge_network.support.base.BaseException;
import com.knowledge_network.support.common.ResponseErrorEnum;

/**
 * ** Created by gongjiangtao on 2018/4/16
 * 提交的创建者与数据库中找到的创建者不匹配异常
 **/
public class UserNotMatchException extends BaseException {
    private String userId;

    public UserNotMatchException(String id) {
        super(ResponseErrorEnum.NO_MATCHING_USER);
        userId = id;
    }

    public String getUserId() {
        return userId;
    }
}
