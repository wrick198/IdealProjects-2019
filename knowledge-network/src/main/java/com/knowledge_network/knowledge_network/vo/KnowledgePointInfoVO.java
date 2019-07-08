package com.knowledge_network.knowledge_network.vo;

import com.knowledge_network.knowledge_network.entity.Course;
import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.knowledge_network.entity.KnowledgePointRelationship;
import com.knowledge_network.knowledge_network.entity.Tag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wjk
 * Date: 17-12-26
 * Time: 下午2:37
 * 此VO用于添加新的知识点功能
 */

public class KnowledgePointInfoVO {
    private Integer id;
    private String name;
    private SubjectInfoVO subject;
    private String statement;
    private String content;
    private Integer grade;
    private KnowledgePointImportanceInfoVO importance;
    private KnowledgePointDifficultInfoVO difficult;
    private Collection<String> tags;
    private Collection<CourseInfoVO> courseVOS;
    private ParentVO parentVO;
    private Collection<ChildrenVO> childrenVOS;
    private Collection<PostVO> postVOS;
    private Collection<PreVO> preVOS;


    public KnowledgePointInfoVO() {

    }

    public KnowledgePointInfoVO(KnowledgePoint knowledgePoint) {
        if (knowledgePoint != null) {
            id = knowledgePoint.getId();
            name = knowledgePoint.getName();
            statement = knowledgePoint.getStatement();
            content = knowledgePoint.getContent();
            grade = knowledgePoint.getGrade();
            if (knowledgePoint.getSubject() != null)
                subject = new SubjectInfoVO(knowledgePoint.getSubject());
            if (knowledgePoint.getKnowledgePointImportance() != null)
                importance = new KnowledgePointImportanceInfoVO(knowledgePoint.getKnowledgePointImportance());
            if (knowledgePoint.getKnowledgePointDifficult() != null)
                difficult = new KnowledgePointDifficultInfoVO(knowledgePoint.getKnowledgePointDifficult());
            List<Tag> tagList = (List<Tag>) knowledgePoint.getTags();
            tags = new ArrayList<>();
            for (Tag tag : tagList) {
                tags.add(tag.getName());
            }
            courseVOS = new ArrayList<>();
            Collection<Course> courses = knowledgePoint.getCourses();
            if (courses.size() != 0) {
                for (Course course : courses) {
                    courseVOS.add(new CourseInfoVO(course));
                }
            }
            postVOS = new ArrayList<>();
            childrenVOS = new ArrayList<>();
            preVOS = new ArrayList<>();
            List<KnowledgePointRelationship> knowledgePointRelationships2 = (List<KnowledgePointRelationship>) knowledgePoint.getKnowledgePointRelationships2();
            for (KnowledgePointRelationship knowledgePointRelationship : knowledgePointRelationships2) {
                if (knowledgePointRelationship.getKnowledgePointRelationshipType() == 0)
                    parentVO = new ParentVO(knowledgePointRelationship.getKnowledgePoint1());
                if (knowledgePointRelationship.getKnowledgePointRelationshipType() == 1)
                    preVOS.add(new PreVO(knowledgePointRelationship.getKnowledgePoint1()));
            }

            List<KnowledgePointRelationship> knowledgePointRelationships1 = (List<KnowledgePointRelationship>) knowledgePoint.getKnowledgePointRelationships1();
            for (KnowledgePointRelationship knowledgePointRelationship : knowledgePointRelationships1) {
                if (knowledgePointRelationship.getKnowledgePointRelationshipType() == 0)
                    childrenVOS.add(new ChildrenVO(knowledgePointRelationship.getKnowledgePoint2()));
                if (knowledgePointRelationship.getKnowledgePointRelationshipType() == 1)
                    postVOS.add(new PostVO(knowledgePointRelationship.getKnowledgePoint2()));
            }

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

    public SubjectInfoVO getSubject() {
        return subject;
    }

    public void setSubject(SubjectInfoVO subject) {
        this.subject = subject;
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

    public KnowledgePointImportanceInfoVO getImportance() {
        return importance;
    }

    public void setImportance(KnowledgePointImportanceInfoVO importance) {
        this.importance = importance;
    }

    public KnowledgePointDifficultInfoVO getDifficult() {
        return difficult;
    }

    public void setDifficult(KnowledgePointDifficultInfoVO diffcult) {
        this.difficult = diffcult;
    }

    public Collection<String> getTags() {
        return tags;
    }

    public void setTags(Collection<String> tags) {
        this.tags = tags;
    }

    public Collection<CourseInfoVO> getCourseVOS() {
        return courseVOS;
    }

    public void setCourseVOS(Collection<CourseInfoVO> courseVOS) {
        this.courseVOS = courseVOS;
    }

    public ParentVO getParentVO() {
        return parentVO;
    }

    public void setParentVO(ParentVO parentVO) {
        this.parentVO = parentVO;
    }

    public Collection<ChildrenVO> getChildrenVOS() {
        return childrenVOS;
    }

    public void setChildrenVOS(Collection<ChildrenVO> childrenVOS) {
        this.childrenVOS = childrenVOS;
    }

    public Collection<PostVO> getPostVOS() {
        return postVOS;
    }

    public void setPostVOS(Collection<PostVO> postVOS) {
        this.postVOS = postVOS;
    }

    public Collection<PreVO> getPreVOS() {
        return preVOS;
    }

    public void setPreVOS(Collection<PreVO> preVOS) {
        this.preVOS = preVOS;
    }
}
