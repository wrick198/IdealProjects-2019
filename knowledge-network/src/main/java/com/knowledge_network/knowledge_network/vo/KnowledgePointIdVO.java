package com.knowledge_network.knowledge_network.vo;

import com.knowledge_network.knowledge_network.entity.KnowledgePoint;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: wjk
 * Date: 17-12-31
 * Time: 下午6:02
 */
public class KnowledgePointIdVO {
    Collection<Integer> id;

    public KnowledgePointIdVO() {

    }

    public Collection<Integer> getId() {
        return id;
    }

    public void setId(Collection<Integer> id) {
        this.id = id;
    }
}
