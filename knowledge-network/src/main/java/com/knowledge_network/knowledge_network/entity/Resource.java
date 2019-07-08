package com.knowledge_network.knowledge_network.entity;

import com.knowledge_network.quize.entity.Paper;
import com.knowledge_network.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by pentonbin on 17-12-1
 */
@Entity
@Table(name = "resource")
public class Resource implements Serializable {

    public static final int RESOURCE_DISABLE = 0;
    public static final int RESOURCE_ENABLE = 1;

    public static final int RESOURCE_NON_DELETED = 0;
    public static final int RESOURCE_DELETED = 1;

    public static final int RESOURCE_NON_NEED_PAY = 0;
    public static final int RESOURCE_NEED_PAY = 1;

    /**
     * 资源id
     * 与业务无关，自动生成（递增）
     */
    private Integer id;
    /**
     * 资源名称
     */
    private String name;
    /**
     * 资源的年级
     */
    private Integer grade;
    /**
     * 资源的下载次数
     */
    private Integer download;
    /**
     * 资源描述
     */
    private String description;
    /**
     * 资源用户评分次数
     */
    private Integer scoreTimes;
    /**
     * 资源的平均分数
     */
    private Double score;
    /**
     * 资源排名
     */
    private Integer rank;
    /**
     * 资源上传者用户，仅支持教师、知识网络专家和系统管理员上传
     */
    private User uploader;
    /**
     * 该资源的URL地址
     */
    private String url;
    /**
     * 资源上传的时间
     */
    private Timestamp uploadDatetime;
    /**
     * 当前资源是否被禁用
     * 0：被禁用，值{@link Resource#RESOURCE_DISABLE}
     * 1：正常，值{@link Resource#RESOURCE_ENABLE}
     */
    private Integer enable;
    /**
     * 当前资源是否已被删除
     * 0：正常，值{@link Resource#RESOURCE_NON_DELETED}
     * 1：已被删除，值{@link Resource#RESOURCE_DELETED}
     */
    private Integer deleted;
    /**
     * 资源是否付费
     * 0：免费，值{@link Resource#RESOURCE_NON_NEED_PAY}
     * 1：付费，值值{@link Resource#RESOURCE_NEED_PAY}
     */
    private Integer needPay;
    /**
     * 该资源对应的学科
     */
    private Subject subject;
    /**
     * 包含该资源的课程
     */
    private Collection<Course> courses = new ArrayList<>();
    /**
     * 包含该资源的知识点
     */
    private Collection<KnowledgePoint> knowledgePoints = new ArrayList<>();
    /**
     * 资源类型
     * 可以查看{@link ResourceType}
     */
    private ResourceType resourceType;
    /**
     * 下载该资源的用户
     */
    private Collection<User> downloadUser = new ArrayList<>();
    /**
     * 对该资源打分的用户
     */
    private Collection<UserMarkResourceScoreRelationship> markedScoreUser = new ArrayList<>();

    private Collection<Paper> papers = new ArrayList<>();

    public Resource() {
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
    @Column(name = "grade", nullable = false)
    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "download", nullable = false)
    public Integer getDownload() {
        return download;
    }

    public void setDownload(Integer download) {
        this.download = download;
    }

    @Basic
    @Column(name = "upload_datetime", nullable = false)
    public Timestamp getUploadDatetime() {
        return uploadDatetime;
    }

    public void setUploadDatetime(Timestamp uploadDatetime) {
        this.uploadDatetime = uploadDatetime;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "rank")
    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "uploader_user_id", referencedColumnName = "id")
    public User getUploader() {
        return uploader;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    @Basic
    @Column(name = "need_pay", nullable = false)
    public Integer getNeedPay() {
        return needPay;
    }

    public void setNeedPay(Integer needPay) {
        this.needPay = needPay;
    }

    @ManyToOne(targetEntity = Subject.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @ManyToMany(mappedBy = "resources")
    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }

    @ManyToMany(mappedBy = "resources")
    public Collection<KnowledgePoint> getKnowledgePoints() {
        return knowledgePoints;
    }

    public void setKnowledgePoints(Collection<KnowledgePoint> knowledgePoints) {
        this.knowledgePoints = knowledgePoints;
    }

    @ManyToOne(targetEntity = ResourceType.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "resource_type_id", nullable = false, referencedColumnName = "id")
    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    @ManyToMany(targetEntity = Paper.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "resource_contains_paper",
            joinColumns = @JoinColumn(name = "resource_id"),
            inverseJoinColumns = @JoinColumn(name = "paper_id")
    )
    public Collection<Paper> getPapers() {
        return papers;
    }

    public void setPapers(Collection<Paper> papers) {
        this.papers = papers;
    }

    @ManyToMany(mappedBy = "downloadResources")
    public Collection<User> getDownloadUser() {
        return downloadUser;
    }

    public void setDownloadUser(Collection<User> downloadUser) {
        this.downloadUser = downloadUser;
    }

    @Basic
    @Column(name = "score_times")
    public Integer getScoreTimes() {
        return scoreTimes;
    }

    public void setScoreTimes(Integer scoreTimes) {
        this.scoreTimes = scoreTimes;
    }

    @Basic
    @Column(name = "score")
    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL)
    public Collection<UserMarkResourceScoreRelationship> getMarkedScoreUser() {
        return markedScoreUser;
    }

    public void setMarkedScoreUser(Collection<UserMarkResourceScoreRelationship> scoreUser) {
        this.markedScoreUser = scoreUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resource resource = (Resource) o;

        if (id != resource.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }
}
