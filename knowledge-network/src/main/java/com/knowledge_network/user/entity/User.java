package com.knowledge_network.user.entity;

import com.knowledge_network.circle.entity.Collect;
import com.knowledge_network.circle.entity.Notification;
import com.knowledge_network.circle.entity.Reply;
import com.knowledge_network.circle.entity.Topic;
import com.knowledge_network.knowledge_network.entity.CourseClicks;
import com.knowledge_network.knowledge_network.entity.KnowledgePointClicks;
import com.knowledge_network.knowledge_network.entity.ResourceClicks;
import com.knowledge_network.quize.entity.Quize;
import com.knowledge_network.knowledge_network.entity.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by pentonbin on 17-12-1
 */
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {

    // 用户状态
    public static final int USER_DISABLE = 0;
    public static final int USER_ENABLE = 1;

    // 用户性别
    public static final int USER_GENDER_SECRET = 0;
    public static final int USER_GENDER_BOY = 1;
    public static final int USER_GENDER_GIRL = 2;
    public static final int USER_GENDER_OTHERS = 3;

    // 用户注销
    public static final int USER_NON_LOGOFF = 0;
    public static final int USER_LOGOFF = 1;

    /**
     * 用户id
     * 与业务无关，自动生成（递增）
     */
    protected Integer id;
    /**
     * 用户名（登录使用）
     */
    protected String username;
    /**
     * 用户邮件地址
     */
    protected String email;
    /**
     * 用户手机号
     */
    protected String phone;
    /**
     * 用户密码
     */
    protected String password;
    /**
     * 用户注册时间
     */
    protected Timestamp registerDatetime;
    /**
     * 用户最后一次登录时间
     */
    protected Timestamp lastLoginDatetime;
    /**
     * 用户真实姓名
     */
    protected String realName;
    /**
     * 用户性别
     * 0：保密
     * 1：男生
     * 2：女生
     * 3：其他
     */
    protected Integer sex;
    /**
     * 用户头像地址
     */
    protected String imageUrl;
    /**
     * 用户生日
     */
    protected Date birthday;
    /**
     * 用户自我介绍
     */
    protected String description;
    /**
     * 用户家庭住址
     */
    protected String address;
    /**
     * 用户是否有效
     * 0：无效
     * 1：有效
     */
    protected Integer enable;
    /**
     * 用户是否注销
     * 0：未注销
     * 1：已注销
     */
    protected Integer logoff;
    /**
     * 用户角色列表
     */
    protected Collection<UserRoleRelationship> userRoleRelationships = new ArrayList<>();
    /**
     * 用户点击的课程情况
     */
    protected Collection<CourseClicks> courseClicks = new ArrayList<>();
    /**
     * 用户点击的资源情况
     */
    protected Collection<ResourceClicks> resourceClicks = new ArrayList<>();
    /**
     * 用户点击的知识点情况
     */
    protected Collection<KnowledgePointClicks> knowledgePointClicks = new ArrayList<>();

    protected Collection<Topic> topics = new ArrayList<>();

    protected Collection<Collect> collects = new ArrayList<>();

    protected Collection<Notification> notifications = new ArrayList<>();

    protected Collection<Reply> replies = new ArrayList<>();
    /**
     * 用户在课程模块提交的问题
     */
    protected Collection<CourseQuestion> questions = new ArrayList<>();
    /**
     * 用户在课程模块的回答
     */
    protected Collection<CourseAnswer> answers = new ArrayList<>();
    /**
     * 用户在课程模块对回答的回复
     */
    protected Collection<CourseAnswer> answerReplies = new ArrayList<>();
    /**
     * 用户在课程模块的评价
     */
    protected Collection<CourseEvaluate> evaluates = new ArrayList<>();
    /**
     * 当前用户所上传资源
     */
    private Collection<Resource> uploadResources = new ArrayList<>();

    /**
     * 当前用户下载的资源
     */
    private Collection<Resource> downloadResources = new ArrayList<>();

    /**
     * 标记分数的资源
     */
    private Collection<UserMarkResourceScoreRelationship> markScoreResource = new ArrayList<>();

    public User() {
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
    @Column(name = "user_name", nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    @Basic
    @Column(name = "email", unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone", unique = true)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "register_datetime", nullable = false)
    public Timestamp getRegisterDatetime() {
        return registerDatetime;
    }

    public void setRegisterDatetime(Timestamp registerDatetime) {
        this.registerDatetime = registerDatetime;
    }

    @Basic
    @Column(name = "last_login_datetime")
    public Timestamp getLastLoginDatetime() {
        return lastLoginDatetime;
    }

    public void setLastLoginDatetime(Timestamp lastLoginDatetime) {
        this.lastLoginDatetime = lastLoginDatetime;
    }

    @Basic
    @Column(name = "real_name")
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Basic
    @Column(name = "sex")
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Basic
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
    @Column(name = "logoff", nullable = false)
    public Integer getLogoff() {
        return logoff;
    }

    public void setLogoff(Integer logoff) {
        this.logoff = logoff;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public Collection<UserRoleRelationship> getUserRoleRelationships() {
        return userRoleRelationships;
    }

    public void setUserRoleRelationships(Collection<UserRoleRelationship> userRoleRelationships) {
        this.userRoleRelationships = userRoleRelationships;
    }

    @Transient
    public UserRole getInitialRole() {
        Collection<UserRoleRelationship> roleRelationships = getUserRoleRelationships();
        for (UserRoleRelationship relationship : roleRelationships) {
            if (relationship.getInitialRole() == UserRoleRelationship.USER_ROLE_INITIAL_ROLE) {
                return relationship.getUserRole();
            }
        }
        return null;
    }

    @OneToMany(targetEntity = CourseClicks.class, cascade = CascadeType.ALL, mappedBy = "user")
    public Collection<CourseClicks> getCourseClicks() {
        return courseClicks;
    }

    public void setCourseClicks(Collection<CourseClicks> courseClicks) {
        this.courseClicks = courseClicks;
    }

    @OneToMany(targetEntity = ResourceClicks.class, cascade = CascadeType.ALL, mappedBy = "user")
    public Collection<ResourceClicks> getResourceClicks() {
        return resourceClicks;
    }

    public void setResourceClicks(Collection<ResourceClicks> resourceClicks) {
        this.resourceClicks = resourceClicks;
    }

    @OneToMany(targetEntity = KnowledgePointClicks.class, cascade = CascadeType.ALL, mappedBy = "user")
    public Collection<KnowledgePointClicks> getKnowledgePointClicks() {
        return knowledgePointClicks;
    }

    public void setKnowledgePointClicks(Collection<KnowledgePointClicks> knowledgePointClicks) {
        this.knowledgePointClicks = knowledgePointClicks;
    }

    @OneToMany(targetEntity = Topic.class, cascade = CascadeType.ALL, mappedBy = "user")
    public Collection<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Collection<Topic> topics) {
        this.topics = topics;
    }

    @OneToMany(targetEntity = Collect.class, cascade = CascadeType.ALL, mappedBy = "user")
    public Collection<Collect> getCollects() {
        return collects;
    }

    public void setCollects(Collection<Collect> collects) {
        this.collects = collects;
    }

    @OneToMany(targetEntity = Notification.class, cascade = CascadeType.ALL, mappedBy = "user")
    public Collection<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Collection<Notification> notifications) {
        this.notifications = notifications;
    }

    @OneToMany(targetEntity = Reply.class, cascade = CascadeType.ALL, mappedBy = "user")
    public Collection<Reply> getReplies() {
        return replies;
    }

    public void setReplies(Collection<Reply> replies) {
        this.replies = replies;
    }

    @OneToMany(targetEntity = CourseQuestion.class, cascade = CascadeType.ALL, mappedBy = "asker")
    public Collection<CourseQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(Collection<CourseQuestion> questions) {
        this.questions = questions;
    }

    @OneToMany(targetEntity = CourseAnswer.class, cascade = CascadeType.ALL, mappedBy = "fromUser")
    public Collection<CourseAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(Collection<CourseAnswer> answers) {
        this.answers = answers;
    }

    @OneToMany(targetEntity = CourseAnswer.class, cascade = CascadeType.ALL, mappedBy = "toUser")
    public Collection<CourseAnswer> getAnswerReplies() {
        return answerReplies;
    }

    public void setAnswerReplies(Collection<CourseAnswer> answerReplys) {
        this.answerReplies = answerReplys;
    }

    @OneToMany(targetEntity = CourseEvaluate.class, cascade = CascadeType.ALL, mappedBy = "user")
    public Collection<CourseEvaluate> getEvaluates() {
        return evaluates;
    }

    public void setEvaluates(Collection<CourseEvaluate> evaluates) {
        this.evaluates = evaluates;
    }

    @OneToMany(targetEntity = Resource.class, cascade = CascadeType.ALL, mappedBy = "uploader")
    public Collection<Resource> getUploadResources() {
        return uploadResources;
    }

    public void setUploadResources(Collection<Resource> resources) {
        this.uploadResources = resources;
    }

    @ManyToMany(targetEntity = Resource.class, cascade = CascadeType.ALL)
    @JoinTable(name = "user_has_download_resource",
            joinColumns = @JoinColumn(name = "id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id", referencedColumnName = "id"))
    public Collection<Resource> getDownloadResources() {
        return downloadResources;
    }

    public void setDownloadResources(Collection<Resource> downloadResources) {
        this.downloadResources = downloadResources;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public Collection<UserMarkResourceScoreRelationship> getMarkScoreResource() {
        return markScoreResource;
    }

    public void setMarkScoreResource(Collection<UserMarkResourceScoreRelationship> markScoreResource) {
        this.markScoreResource = markScoreResource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }
}
