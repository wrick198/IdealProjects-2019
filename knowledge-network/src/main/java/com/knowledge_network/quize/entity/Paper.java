package com.knowledge_network.quize.entity;

import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.knowledge_network.entity.Resource;
import com.knowledge_network.user.entity.Teacher;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * ** Created by gongjiangtao on 2018/4/16
 * 试卷实体类
 **/
@Entity
@Table(name = "paper")
public class Paper implements Serializable {
    /**
     * Id字段，唯一标识一个paper
     */
    private Integer id;
    /**
     * 试卷名，不唯一，不能为空
     */
    private String name;
    /**
     * 试卷内容，不能为空
     */
    private String content;
    /**
     * 试卷总分
     */
    private int totalPoint;
    /**
     * 合格分
     */
    private int passPoint;
    /**
     * 试卷是否可用，
     * 若可用值为1，否则为0
     */
    private int available;
    /**
     * 试卷是否免费，
     * 若免费为0，收费暂时为1
     * 以后根据需要可以设置为试卷的价格
     */
    private int status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 对试卷的总结
     */
    private String summary;
    /**
     * 试卷的答案，不知道有什么用
     */
    private String answer;
    /**
     * 答卷的持续时间，
     * StudentDoPaper每个记录的开始时间和
     * 结束时间差都不能超过duration
     */
    private int duration;

    /**
     * 与PaperType表的关联字段
     * 一个paperType可以有多个paper
     * 所以为多（Paper）对一（PaperType）关系
     */
    private PaperType paperType;
    /**
     * 与KnowledgePoint表的关联
     * Paper与KnowledgePoint的关联为多对多
     */
    private Collection<KnowledgePoint> knowledgePoints = new ArrayList<>();
    /**
     * 与试题Quize之间的关联
     * 一张试卷由多个试题构成，所以为一对多关联
     */
    private Collection<Quize> quizes = new ArrayList<>();
    /**
     * 与试卷出题人Teacher的关联
     * 一份试卷可以由多个出题人出，所以为一对多关联
     */
    private Collection<Teacher> creators = new ArrayList<>();
    /**
     * 试卷与Resource的关联
     */
    private Collection<Resource> resources = new ArrayList<>();
    /**
     * 与答题卡关联
     */
    private Collection<AnswerSheet> answerSheets = new ArrayList<>();

    @Id
    @Column(name = "paper_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "content", nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "total_point", nullable = false)
    public int getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(int totalPoint) {
        this.totalPoint = totalPoint;
    }

    @Basic
    @Column(name = "pass_point", nullable = false)
    public int getPassPoint() {
        return passPoint;
    }

    public void setPassPoint(int passPoint) {
        this.passPoint = passPoint;
    }

    @Basic
    @Column(name = "available", nullable = false)
    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "summary")
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Basic
    @Column(name = "answer", nullable = false)
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Basic
    @Column(name = "duration", nullable = false)
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @ManyToOne(targetEntity = PaperType.class, cascade = CascadeType.ALL)
    public PaperType getPaperType() {
        return paperType;
    }

    public void setPaperType(PaperType paperType) {
        this.paperType = paperType;
    }

    @ManyToMany(mappedBy = "papers")
    public Collection<KnowledgePoint> getKnowledgePoints() {
        return knowledgePoints;
    }

    public void setKnowledgePoints(Collection<KnowledgePoint> knowledgePoints) {
        this.knowledgePoints = knowledgePoints;
    }

    @ManyToMany(mappedBy = "papers")
    public Collection<Quize> getQuizes() {
        return quizes;
    }

    public void setQuizes(Collection<Quize> quizes) {
        this.quizes = quizes;
    }

    @ManyToMany(mappedBy = "papers")
    public Collection<Teacher> getCreators() {
        return creators;
    }

    public void setCreators(Collection<Teacher> creators) {
        this.creators = creators;
    }

    @ManyToMany(mappedBy = "papers")
    public Collection<Resource> getResources() {
        return resources;
    }

    public void setResources(Collection<Resource> resources) {
        this.resources = resources;
    }

    @OneToMany(targetEntity = AnswerSheet.class, cascade = CascadeType.ALL, mappedBy = "paper")
    public Collection<AnswerSheet> getAnswerSheets() {
        return answerSheets;
    }

    public void setAnswerSheets(Collection<AnswerSheet> answerSheets) {
        this.answerSheets = answerSheets;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != getClass()) return false;

        Paper that = (Paper) obj;
        if (that.getId() != id) return false;

        return true;
    }
}
