package com.knowledge_network.quize.vo;

import com.knowledge_network.knowledge_network.entity.Tag;
import com.knowledge_network.knowledge_network.vo.KnowledgePointInfoVO;
import com.knowledge_network.knowledge_network.vo.SubjectInfoVO;
import com.knowledge_network.knowledge_network.vo.TagInfoVO;
import com.knowledge_network.quize.entity.Quize;
import com.knowledge_network.user.vo.TeacherInfoVO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/4/24
 * 试题信息页面
 **/
public class QuizeInfoVO {
    private int id;

    private String name;

    private String content;

    private String answer;

    private String analysis;

    private String url;

    private Date createTime;

    private Date lastModify;

    private Float examingPoint;

    private TeacherInfoVO creator;

    private List<KnowledgePointInfoVO> knowledgePoints = new ArrayList<>();

    private SubjectInfoVO subject;

    private QuestionTypeInfoVO questionType;

    private DifficultyInfoVO difficulty;

    private Collection<TagInfoVO> tags = new ArrayList<>();

    public QuizeInfoVO() {

    }

    public QuizeInfoVO(Quize quize) {
        id = quize.getId();
        name = quize.getQuizeName();
        content = quize.getContent();
        answer = quize.getAnswer();
        analysis = quize.getAnalysis();
        url = quize.getUrl();
        createTime = quize.getCreateTime();
        lastModify = quize.getLastModify();
        examingPoint = quize.getExamingPoint();
        creator = new TeacherInfoVO(quize.getCreator());
        questionType = new QuestionTypeInfoVO(quize.getQuestionType());
        difficulty = new DifficultyInfoVO(quize.getDifficulty());
        for (Tag tag : quize.getTags()) {
            tags.add(new TagInfoVO(tag));
        }
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModify() {
        return lastModify;
    }

    public void setLastModify(Date lastModify) {
        this.lastModify = lastModify;
    }

    public Float getExamingPoint() {
        return examingPoint;
    }

    public void setExamingPoint(Float examingPoint) {
        this.examingPoint = examingPoint;
    }

    public TeacherInfoVO getCreator() {
        return creator;
    }

    public void setCreator(TeacherInfoVO creator) {
        this.creator = creator;
    }

    public List<KnowledgePointInfoVO> getKnowledgePoints() {
        return knowledgePoints;
    }

    public void setKnowledgePoints(List<KnowledgePointInfoVO> knowledgePoints) {
        this.knowledgePoints = knowledgePoints;
    }

    public SubjectInfoVO getSubject() {
        return subject;
    }

    public void setSubject(SubjectInfoVO subject) {
        this.subject = subject;
    }

    public QuestionTypeInfoVO getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionTypeInfoVO questionType) {
        this.questionType = questionType;
    }

    public DifficultyInfoVO getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyInfoVO difficulty) {
        this.difficulty = difficulty;
    }

    public Collection<TagInfoVO> getTags() {
        return tags;
    }

    public void setTags(Collection<TagInfoVO> tags) {
        this.tags = tags;
    }
}
