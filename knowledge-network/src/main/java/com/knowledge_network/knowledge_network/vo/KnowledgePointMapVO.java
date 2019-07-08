package com.knowledge_network.knowledge_network.vo;

import com.knowledge_network.knowledge_network.entity.KnowledgePoint;

/**
 * Created with IntelliJ IDEA.
 * User: wjk
 * Date: 17-12-28
 * Time: 下午3:55
 * <p>
 * 绘制知识点地图所需的点
 */
public class KnowledgePointMapVO {
    private Integer id;
    private String name;
    private String statement;
    private int importance;

    public KnowledgePointMapVO() {
    }

    public KnowledgePointMapVO(KnowledgePoint knowledgePoint) {
        this.id = knowledgePoint.getId();
        this.name = knowledgePoint.getName();
        this.statement = knowledgePoint.getStatement();
        if (knowledgePoint.getKnowledgePointImportance() != null)
            this.importance = knowledgePoint.getKnowledgePointImportance().getLevel();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }
}
