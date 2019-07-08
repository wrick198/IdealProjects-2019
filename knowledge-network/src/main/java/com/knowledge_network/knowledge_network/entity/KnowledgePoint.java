package com.knowledge_network.knowledge_network.entity;

import com.knowledge_network.quize.entity.Paper;
import com.knowledge_network.quize.entity.Quize;
import com.knowledge_network.user.entity.LearningPath;
import com.knowledge_network.user.entity.LearningPlan;
import com.knowledge_network.user.entity.TeachingPlan;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by pentonbin on 17-12-1
 */
@Entity
@Table(name = "knowledge_point")
public class KnowledgePoint implements Serializable {

    public static final int KNOWLEDGE_POINT_DISABLE = 0;
    public static final int KNOWLEDGE_POINT_ENABLE = 1;

    public static final int KNOWLEDGE_POINT_DELETED = 1;
    public static final int KNOWLEDGE_POINT_NON_DELETED = 0;

    /**
     * 知识点id
     * 与业务无关，自动生成（递增）
     */
    private Integer id;
    /**
     * 知识点名称
     */
    private String name;
    /**
     * 知识点描述
     */
    private String statement;
    /**
     * 知识点内容（富文本）
     */
    private String content;
    /**
     * 知识点所属年级
     */
    private Integer grade;
    /**
     * 当前知识点是否被禁用
     * 0：被禁用，值{@link KnowledgePoint#KNOWLEDGE_POINT_DISABLE}
     * 1：正常，值{@link KnowledgePoint#KNOWLEDGE_POINT_ENABLE}
     */
    private Integer enable;
    /**
     * 知识点是否已被删除
     * 1：表示已删除，变量值参考{@link KnowledgePoint#KNOWLEDGE_POINT_DELETED}
     * 0表示未删除，变量值参考{@link KnowledgePoint#KNOWLEDGE_POINT_NON_DELETED}
     */
    private Integer deleted;
    /**
     * 知识点所属的科目
     */
    private Subject subject;
    /**
     * 包含该知识点的课程列表
     */
    private Collection<Course> courses = new ArrayList<>();
    /**
     * 该知识点下的所有资源
     */
    private Collection<Resource> resources = new ArrayList<>();
    /**
     * 该知识点下的所有标签
     */
    private Collection<Tag> tags = new ArrayList<>();
    /**
     * 包含该知识点的所有学习轨迹
     */
    private Collection<LearningPath> learningPaths = new ArrayList<>();
    /**
     * 包含该知识点的所有学习计划
     */
    private Collection<LearningPlan> learningPlans = new ArrayList<>();
    /**
     * 包含该知识点的所有教案
     */
    private Collection<TeachingPlan> teachingPlans = new ArrayList<>();
    /**
     * 该知识点的困难程度
     * 可查看{@link KnowledgePointDifficult}
     */
    private KnowledgePointDifficult knowledgePointDifficult;
    /**
     * 该知识点的重要程度
     * 可查看{@link KnowledgePointImportance}
     */
    private KnowledgePointImportance knowledgePointImportance;
    /**
     * 知识点与知识点之间的关系；A --> B
     * 当前为B知识点，保存了所有的A知识点（也就是当前知识点的前序知识点或父知识点）
     */
    private Collection<KnowledgePointRelationship> knowledgePointRelationships2 = new ArrayList<>();
    /**
     * 知识点与知识点之间的关系；A --> B
     * 当前为A知识点，保存了所有的B知识点（也就是当前知识点的后序知识点或子知识点）
     */
    private Collection<KnowledgePointRelationship> knowledgePointRelationships1 = new ArrayList<>();

    private Collection<Quize> quizes = new ArrayList<>();

    private Collection<Paper> papers = new ArrayList<>();

    public KnowledgePoint() {
    }

    @Id
    @Column(name = "id", nullable = false)
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
    @Column(name = "statement")
    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
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
    @Column(name = "grade")
    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "enable", nullable = false)
    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    @Basic
    @Column(name = "deleted", nullable = false)
    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @ManyToOne(targetEntity = Subject.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @ManyToMany(mappedBy = "knowledgePoints")
    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }

    @ManyToMany(targetEntity = Resource.class, cascade = CascadeType.ALL)
    @JoinTable(name = "knowledge_point_has_resource",
            joinColumns = @JoinColumn(name = "knowledge_point_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id", referencedColumnName = "id"))
    public Collection<Resource> getResources() {
        return resources;
    }

    public void setResources(Collection<Resource> resources) {
        this.resources = resources;
    }

    @ManyToMany(targetEntity = Tag.class, cascade = CascadeType.ALL)
    @JoinTable(name = "knowledge_point_has_tag",
            joinColumns = @JoinColumn(name = "knowledge_point_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    public Collection<Tag> getTags() {
        return tags;
    }

    public void setTags(Collection<Tag> tags) {
        this.tags = tags;
    }

    @ManyToMany(mappedBy = "knowledgePoints")
    public Collection<LearningPath> getLearningPaths() {
        return learningPaths;
    }

    public void setLearningPaths(Collection<LearningPath> learningPaths) {
        this.learningPaths = learningPaths;
    }

    @ManyToMany(mappedBy = "knowledgePoints")
    public Collection<LearningPlan> getLearningPlans() {
        return learningPlans;
    }

    public void setLearningPlans(Collection<LearningPlan> learningPlans) {
        this.learningPlans = learningPlans;
    }

    @ManyToMany(mappedBy = "knowledgePoints")
    public Collection<TeachingPlan> getTeachingPlans() {
        return teachingPlans;
    }

    public void setTeachingPlans(Collection<TeachingPlan> teachingPlans) {
        this.teachingPlans = teachingPlans;
    }

    @ManyToOne(targetEntity = KnowledgePointDifficult.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "knowledge_point_difficult_id", referencedColumnName = "id")
    public KnowledgePointDifficult getKnowledgePointDifficult() {
        return knowledgePointDifficult;
    }

    public void setKnowledgePointDifficult(KnowledgePointDifficult knowledgePointDifficult) {
        this.knowledgePointDifficult = knowledgePointDifficult;
    }

    @ManyToOne(targetEntity = KnowledgePointImportance.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "knowledge_point_importance_id", referencedColumnName = "id")
    public KnowledgePointImportance getKnowledgePointImportance() {

        return knowledgePointImportance;
    }

    public void setKnowledgePointImportance(KnowledgePointImportance knowledgePointImportance) {
        this.knowledgePointImportance = knowledgePointImportance;
    }

    @OneToMany(mappedBy = "knowledgePoint1", cascade = CascadeType.ALL)
    public Collection<KnowledgePointRelationship> getKnowledgePointRelationships1() {
        return knowledgePointRelationships1;
    }

    public void setKnowledgePointRelationships1(Collection<KnowledgePointRelationship> knowledgePointRelationships1) {
        this.knowledgePointRelationships1 = knowledgePointRelationships1;
    }

    @OneToMany(mappedBy = "knowledgePoint2", cascade = CascadeType.ALL)
    public Collection<KnowledgePointRelationship> getKnowledgePointRelationships2() {
        return knowledgePointRelationships2;
    }

    public void setKnowledgePointRelationships2(Collection<KnowledgePointRelationship> knowledgePointRelationships2) {
        this.knowledgePointRelationships2 = knowledgePointRelationships2;
    }

    @ManyToMany(targetEntity = Quize.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "knowledge_point_has_quize",
            joinColumns = @JoinColumn(name = "knowledge_point_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "quize_id", referencedColumnName = "id")
    )
    public Collection<Quize> getQuizes() {
        return quizes;
    }

    public void setQuizes(Collection<Quize> quizes) {
        this.quizes = quizes;
    }

    @ManyToMany(targetEntity = Paper.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "knowledge_point_has_paper",
            joinColumns = @JoinColumn(name = "knowledge_point_id"),
            inverseJoinColumns = @JoinColumn(name = "paper_id")
    )
    public Collection<Paper> getPapers() {
        return papers;
    }

    public void setPapers(Collection<Paper> papers) {
        this.papers = papers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KnowledgePoint that = (KnowledgePoint) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }
}
