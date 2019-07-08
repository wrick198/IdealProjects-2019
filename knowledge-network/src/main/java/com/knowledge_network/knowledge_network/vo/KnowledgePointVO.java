package com.knowledge_network.knowledge_network.vo;

import com.knowledge_network.knowledge_network.entity.*;
import com.knowledge_network.user.entity.LearningPath;
import com.knowledge_network.user.entity.LearningPlan;
import com.knowledge_network.user.entity.TeachingPlan;

import java.util.Collection;

public class KnowledgePointVO {
    private Integer id;
    private String name;
    private String statement;
    private String content;
    private Integer grade;
    private Collection<Course> courses;
    private Collection<Resource> resources;
    private Collection<Tag> tags;
//    private Collection<LearningPath> learningPaths;
//    private Collection<LearningPlan> learningPlans;
//    private Collection<TeachingPlan> teachingPlans;
    private int knowledgePointDifficult;
    private int knowledgePointImportance;
//    private Collection<KnowledgePointRelationship> knowledgePointRelationships1;
//    private Collection<KnowledgePointRelationship> knowledgePointRelationships2;

    public KnowledgePointVO() {

    }

    public KnowledgePointVO(KnowledgePoint knowledgePoint) {
        if (knowledgePoint != null) {
            id = knowledgePoint.getId();
            name = knowledgePoint.getName();
            statement = knowledgePoint.getStatement();
            content = knowledgePoint.getContent();
            grade = knowledgePoint.getGrade();
            courses = knowledgePoint.getCourses();
            resources = knowledgePoint.getResources();
//            tags = knowledgePoint.getTags();
//            learningPaths = knowledgePoint.getLearningPaths();
//            learningPlans = knowledgePoint.getLearningPlans();
//            teachingPlans = knowledgePoint.getTeachingPlans();
            knowledgePointDifficult = knowledgePoint.getKnowledgePointDifficult().getLevel();
            knowledgePointImportance = knowledgePoint.getKnowledgePointImportance().getLevel();
//            knowledgePointRelationships1 = knowledgePoint.getKnowledgePointRelationships1();
//            knowledgePointRelationships2 = knowledgePoint.getKnowledgePointRelationships2();
        }
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

    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }

    public Collection<Resource> getResources() {
        return resources;
    }

    public void setResources(Collection<Resource> resources) {
        this.resources = resources;
    }

//    public Collection<Tag> getTags() {
//        return tags;
//    }
//
//    public void setTags(Collection<Tag> tags) {
//        this.tags = tags;
//    }
//
//    public Collection<LearningPath> getLearningPaths() {
//        return learningPaths;
//    }
//
//    public void setLearningPaths(Collection<LearningPath> learningPaths) {
//        this.learningPaths = learningPaths;
//    }
//
//    public Collection<LearningPlan> getLearningPlans() {
//        return learningPlans;
//    }
//
//    public void setLearningPlans(Collection<LearningPlan> learningPlans) {
//        this.learningPlans = learningPlans;
//    }
//
//    public Collection<TeachingPlan> getTeachingPlans() {
//        return teachingPlans;
//    }
//
//    public void setTeachingPlans(Collection<TeachingPlan> teachingPlans) {
//        this.teachingPlans = teachingPlans;
//    }

    public int  getKnowledgePointDifficult() {
        return knowledgePointDifficult;
    }

    public void setKnowledgePointDifficult(int knowledgePointDifficult) {
        this.knowledgePointDifficult = knowledgePointDifficult;
    }

    public int getKnowledgePointImportance() {
        return knowledgePointImportance;
    }

    public void setKnowledgePointImportance(int  knowledgePointImportance) {
        this.knowledgePointImportance = knowledgePointImportance;
    }

//    public Collection<KnowledgePointRelationship> getKnowledgePointRelationships1() {
//        return knowledgePointRelationships1;
//    }
//
//    public void setKnowledgePointRelationships1(Collection<KnowledgePointRelationship> knowledgePointRelationships1) {
//        this.knowledgePointRelationships1 = knowledgePointRelationships1;
//    }
//
//    public Collection<KnowledgePointRelationship> getKnowledgePointRelationships2() {
//        return knowledgePointRelationships2;
//    }
//
//    public void setKnowledgePointRelationships2(Collection<KnowledgePointRelationship> knowledgePointRelationships2) {
//        this.knowledgePointRelationships2 = knowledgePointRelationships2;
//    }
}
