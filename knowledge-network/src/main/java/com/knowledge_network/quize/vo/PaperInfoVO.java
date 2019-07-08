package com.knowledge_network.quize.vo;

import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.knowledge_network.vo.KnowledgePointInfoVO;
import com.knowledge_network.quize.entity.Paper;
import com.knowledge_network.quize.entity.Quize;
import com.knowledge_network.user.entity.Teacher;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/5/3
 **/
public class PaperInfoVO {
    private Integer id;

    private String name;

    private String content;

    private Integer totalPoint;

    private Integer passPoint;

    private Date createTime;

    private Integer duration;

    private Integer status;

    private String summary;

    private String paperType;

    private List<String> knowledgePoints = new ArrayList<>();

    private List<QuizeInfoVO> quizes = new ArrayList<>();

    private List<String> creatorNames = new ArrayList<>();

    public PaperInfoVO() {

    }

    public PaperInfoVO(Paper paper) {
        this.id = paper.getId();
        this.name = paper.getName();
        this.content = paper.getContent();
        this.totalPoint = paper.getTotalPoint();
        this.passPoint = paper.getPassPoint();
        this.createTime = paper.getCreateTime();
        this.duration = paper.getDuration();
        this.paperType = paper.getPaperType().getName();
        this.status = paper.getStatus();
        this.summary = paper.getSummary();

        for (KnowledgePoint kp : paper.getKnowledgePoints())
            this.knowledgePoints.add(kp.getName());
        for (Quize q : paper.getQuizes())
            this.quizes.add(new QuizeInfoVO(q));
        for (Teacher t : paper.getCreators())
            this.creatorNames.add(t.getRealName());
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

    public Integer getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(Integer totalPoint) {
        this.totalPoint = totalPoint;
    }

    public Integer getPassPoint() {
        return passPoint;
    }

    public void setPassPoint(Integer passPoint) {
        this.passPoint = passPoint;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    public List<String> getKnowledgePoints() {
        return knowledgePoints;
    }

    public void setKnowledgePoints(List<String> knowledgePoints) {
        this.knowledgePoints = knowledgePoints;
    }

    public List<QuizeInfoVO> getQuizes() {
        return quizes;
    }

    public void setQuizes(List<QuizeInfoVO> quizes) {
        this.quizes = quizes;
    }

    public List<String> getCreatorNames() {
        return creatorNames;
    }

    public void setCreatorNames(List<String> creatorNames) {
        this.creatorNames = creatorNames;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
