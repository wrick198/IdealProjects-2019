package com.knowledge_network.knowledge_network.vo;

import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import org.springframework.security.access.method.P;

/**
 * Created with IntelliJ IDEA.
 * User: wjk
 * Date: 18-1-17
 * Time: 下午4:11
 * 此VO用于KnowledgePointInfoVO中的后置知识点
 */
public class PostVO {
    private int id;
    private String name;

    public PostVO() {

    }

    public PostVO(KnowledgePoint knowledgePoint) {
        id = knowledgePoint.getId();
        name = knowledgePoint.getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
