package com.knowledge_network.support.exceptions;

import com.knowledge_network.support.base.BaseException;
import com.knowledge_network.support.common.ResponseErrorEnum;

/**
 * Created with IntelliJ IDEA.
 * User: wjk
 * Date: 17-12-31
 * Time: 上午10:39
 */
public class CourseNotFoundException extends BaseException {

    private String id;

    public CourseNotFoundException(String id) {
        super(ResponseErrorEnum.COURSE_NOT_FOUND);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
