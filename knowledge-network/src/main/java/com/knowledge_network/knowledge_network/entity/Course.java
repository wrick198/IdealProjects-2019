package com.knowledge_network.knowledge_network.entity;

import com.knowledge_network.user.entity.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by pentonbin on 17-12-1
 * Modified by lwh
 */
@Entity
@Table(name = "course")
public class Course implements Serializable {

    /**
     * 课程状态
     */
    public static final int COURSE_DISABLE = 0;
    public static final int COURSE_ENABLE = 1;

    /**
     * 课程开课状态
     */
    public static final int COURSE_START = 1;
    public static final int COURSE_END = 0;

    /**
     * 课程id
     * 与业务无关，自动生成（递增）
     */
    private Integer id;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 课程类型
     * 0：普通课程
     * 1：教师创建课程
     */
    private Integer courseType;
    /**
     * 该课程所属年级
     */
    private Integer grade;
    /**
     * 该课程所属的学科
     */
    private Subject subject;
    /**
     * 该课程的用户评分情况
     */
    private Integer score;
    /**
     * 该课程的排名情况
     */
    private Integer rank;
    /**
     * 该课程总体评分
     */
    private Float rate;
    /**
     * 该课程开课时间
     */
    private Timestamp startTime;
    /**
     * 该课程结课时间
     */
    private Timestamp endTime;
    /**
     * 该课程对应学校
     */
    private String school;
    /**
     * 该课程对应教师
     */
    private User teacher;
    /**
     * 该课程图片url
     */
    private String coursePic;
    /**
     * 该课程简介
     */
    private String introduction;
    /**
     * 参加该课程人数
     */
    private Long entries;
    /**
     * 课程是否可用（0表示不可用，1表示可用）
     */
    private Integer enabled;
    /**
     * 该课程状态(0表示结课，1表示开课)
     */
    private Integer status;
    /**
     * 该课程每周课时
     */
    private Integer hour;
    /**
     * 开设课程时间
     */
    private Timestamp publishTime;
    /**
     * 课程包含的知识点列表
     */
    private Collection<KnowledgePoint> knowledgePoints = new ArrayList<>();
    /**
     * 课程包含的资源列表
     */
    private Collection<Resource> resources = new ArrayList<>();
    /**
     * 参加该课程的学生列表
     */
    private Collection<Student> allStudents = new ArrayList<>();
    /**
     * 收藏了该课程的学生列表
     */
    private Collection<Student> collectedStudents = new ArrayList<>();
    /**
     * 包含该课程的学习计划
     */
    private Collection<LearningPlan> learningPlans = new ArrayList<>();
    /**
     * 包含该课程的学习轨迹
     */
    private Collection<LearningPath> learningPaths = new ArrayList<>();

    /**
     * 课程讨论区包含的问题
     */
    private Collection<CourseQuestion> questions = new ArrayList<>();

    /**
     * 课程包含的评价
     */
    private Collection<CourseEvaluate> evaluates = new ArrayList<>();

    public Course() {
    }

    @OneToMany(targetEntity = CourseQuestion.class, cascade = CascadeType.ALL, mappedBy = "course")
    public Collection<CourseQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(Collection<CourseQuestion> questions) {
        this.questions = questions;
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
    @Column(name = "course_type", nullable = false)
    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
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
    @Column(name = "score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Basic
    @Column(name = "rank")
    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Basic
    @Column(name = "rate")
    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    @Basic
    @Column(name = "start_time")
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "school")
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @ManyToOne(targetEntity = Teacher.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "user_id")
    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    @Basic
    @Column(name = "course_pic")
    public String getCoursePic() {
        return coursePic;
    }

    public void setCoursePic(String course_pic) {
        this.coursePic = course_pic;
    }

    @Basic
    @Column(name = "introduction")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Basic
    @Column(name = "entries")
    public Long getEntries() {
        return entries;
    }

    public void setEntries(Long entries) {
        this.entries = entries;
    }

    @Basic
    @Column(name = "enabled")
    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "hour")
    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    @Basic
    @Column(name = "publish_time")
    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

    @ManyToMany(targetEntity = KnowledgePoint.class, cascade = CascadeType.ALL)
    @JoinTable(name = "course_has_knowledge_point",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "knowledge_point_id", referencedColumnName = "id"))
    public Collection<KnowledgePoint> getKnowledgePoints() {
        return knowledgePoints;
    }

    public void setKnowledgePoints(Collection<KnowledgePoint> knowledgePoints) {
        this.knowledgePoints = knowledgePoints;
    }

    @ManyToMany(targetEntity = Resource.class, cascade = CascadeType.ALL)
    @JoinTable(name = "course_has_resource",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id", referencedColumnName = "id"))
    public Collection<Resource> getResources() {
        return resources;
    }

    public void setResources(Collection<Resource> resources) {
        this.resources = resources;
    }

    @ManyToMany(mappedBy = "courses")
    public Collection<Student> getAllStudents() {
        return allStudents;
    }

    public void setAllStudents(Collection<Student> students) {
        this.allStudents = students;
    }

    @ManyToMany(mappedBy = "collectedCourses")
    public Collection<Student> getCollectedStudents() {
        return collectedStudents;
    }

    public void setCollectedStudents(Collection<Student> collectedStudents) {
        this.collectedStudents = collectedStudents;
    }

    @OneToMany(targetEntity = LearningPlan.class, cascade = CascadeType.ALL, mappedBy = "course")
    public Collection<LearningPlan> getLearningPlans() {
        return learningPlans;
    }

    public void setLearningPlans(Collection<LearningPlan> learningPlans) {
        this.learningPlans = learningPlans;
    }

    @OneToMany(targetEntity = LearningPath.class, cascade = CascadeType.ALL, mappedBy = "course")
    public Collection<LearningPath> getLearningPaths() {
        return learningPaths;
    }

    public void setLearningPaths(Collection<LearningPath> learningPaths) {
        this.learningPaths = learningPaths;
    }

    @ManyToOne(targetEntity = Subject.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @OneToMany(targetEntity = CourseEvaluate.class, cascade = CascadeType.ALL, mappedBy = "course")
    public Collection<CourseEvaluate> getEvaluates() {
        return evaluates;
    }

    public void setEvaluates(Collection<CourseEvaluate> evaluates) {
        this.evaluates = evaluates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (id != course.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }
}
