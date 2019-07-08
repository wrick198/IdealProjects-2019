package com.knowledge_network.user.entity;

import com.knowledge_network.knowledge_network.entity.Course;
import com.knowledge_network.knowledge_network.entity.KnowledgePoint;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by pentonbin on 17-12-1
 */
@Entity
@Table(name = "learning_plan")
public class LearningPlan implements Serializable {

    /**
     * 学习计划id
     * 与业务无关，自动生成（递增）
     */
    private Integer id;
    /**
     * 学习计划名称
     */
    private String name;
    /**
     * 学习计划开始时间
     */
    private Timestamp startDatetime;
    /**
     * 学习计划结束时间
     */
    private Timestamp endDatetime;
    /**
     * 该学习计划所包含的所有知识点
     */
    private Collection<KnowledgePoint> knowledgePoints = new ArrayList<>();
    /**
     * 该学习计划对应的学生
     */
    private Student student;
    /**
     * 该学习计划所属的课程
     */
    private Course course;

    public LearningPlan() {
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
    @Column(name = "start_datetime")
    public Timestamp getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Timestamp startDatetime) {
        this.startDatetime = startDatetime;
    }

    @Basic
    @Column(name = "end_datetime")
    public Timestamp getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Timestamp endDatetime) {
        this.endDatetime = endDatetime;
    }

    @ManyToMany(targetEntity = KnowledgePoint.class, cascade = CascadeType.ALL)
    @JoinTable(name = "learning_plan_has_knowledge_point",
            joinColumns = @JoinColumn(name = "learning_plan_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "knowledge_point_id", referencedColumnName = "id"))
    public Collection<KnowledgePoint> getKnowledgePoints() {
        return knowledgePoints;
    }

    public void setKnowledgePoints(Collection<KnowledgePoint> knowledgePoints) {
        this.knowledgePoints = knowledgePoints;
    }

    @ManyToOne(targetEntity = Student.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_user_id", referencedColumnName = "user_id")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToOne(targetEntity = Course.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LearningPlan that = (LearningPlan) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }
}
