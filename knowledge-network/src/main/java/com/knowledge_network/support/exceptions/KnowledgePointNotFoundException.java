package com.knowledge_network.support.exceptions;

import com.knowledge_network.support.base.BaseException;
import com.knowledge_network.support.common.ResponseErrorEnum;

/**
 * Created with IntelliJ IDEA.
 * User: wjk
 * Date: 17-12-20
 * Time: 上午10:08
 * 找不到知识点异常
 */
public class KnowledgePointNotFoundException extends BaseException {

    private Integer kpid;
    private String name;

    public KnowledgePointNotFoundException(Integer kpid) {
        super(ResponseErrorEnum.KNOWLEDGEPOINT_NOT_FOUND);
        this.kpid = kpid;
    }

    public KnowledgePointNotFoundException(String name) {
        super(ResponseErrorEnum.KNOWLEDGEPOINT_NOT_FOUND);
        this.name = name;
    }

    public Integer getKpid() {
        return kpid;
    }

    public String getName() {
        return name;
    }
}
