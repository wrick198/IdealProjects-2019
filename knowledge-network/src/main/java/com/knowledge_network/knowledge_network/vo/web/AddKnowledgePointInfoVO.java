package com.knowledge_network.knowledge_network.vo.web;


import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: wjk
 * Date: 18-1-20
 * Time: 下午10:16
 */
public class AddKnowledgePointInfoVO {
    private int id;
    private String name;
    private String statement;
    private String content;
    private String subject;
    private Integer grade;
    private String importance;
    private String difficult;
    private Collection<AddKnowledgePointRelationshipVO> addKnowledgePointRelationshipVOS;
    private Collection<DeleteKnowledgePointRelationshipVO> deleteKnowledgePointRelationshipVOS;

    public AddKnowledgePointInfoVO() {

    }

    public AddKnowledgePointInfoVO(String name,
                                   String statement,
                                   String content,
                                   String subject,
                                   Integer grade,
                                   String importance,
                                   String difficult,
                                   Collection<AddKnowledgePointRelationshipVO> addKnowledgePointRelationshipVOS,
                                   Collection<DeleteKnowledgePointRelationshipVO> deleteKnowledgePointRelationshipVOS) {
        this.name = name;
        this.statement = statement;
        this.content = content;
        this.subject = subject;
        this.grade = grade;
        this.importance = importance;
        this.difficult = difficult;
        this.addKnowledgePointRelationshipVOS = addKnowledgePointRelationshipVOS;
        this.deleteKnowledgePointRelationshipVOS = deleteKnowledgePointRelationshipVOS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public String getDifficult() {
        return difficult;
    }

    public void setDifficult(String difficult) {
        this.difficult = difficult;
    }

    public Collection<AddKnowledgePointRelationshipVO> getAddKnowledgePointRelationshipVOS() {
        return addKnowledgePointRelationshipVOS;
    }

    public void setAddKnowledgePointRelationshipVOS(Collection<AddKnowledgePointRelationshipVO> addKnowledgePointRelationshipVOS) {
        this.addKnowledgePointRelationshipVOS = addKnowledgePointRelationshipVOS;
    }

    public Collection<DeleteKnowledgePointRelationshipVO> getDeleteKnowledgePointRelationshipVOS() {
        return deleteKnowledgePointRelationshipVOS;
    }

    public void setDeleteKnowledgePointRelationshipVOS(Collection<DeleteKnowledgePointRelationshipVO> deleteKnowledgePointRelationshipVOS) {
        this.deleteKnowledgePointRelationshipVOS = deleteKnowledgePointRelationshipVOS;
    }
}
