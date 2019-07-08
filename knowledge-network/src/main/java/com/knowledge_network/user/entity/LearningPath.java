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
@Table(name = "learning_path")
public class LearningPath implements Serializable {
    /**
     * 学习轨迹id
     * 与业务无关，自动生成（递增）
     */
    private Integer id;
    /**
     * 该学习轨迹中的所有知识点
     */
    private Collection<KnowledgePoint> knowledgePoints = new ArrayList<>();
    /**
     * 最近一次学习时间
     */
    private Timestamp lastLearningTime;
    /**
     * 该学习轨迹所属的课程
     */
    private Course course;
    /**
     * 该学习轨迹对应的学生
     */
    private Student student;

    public LearningPath() {
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

    @ManyToMany(targetEntity = KnowledgePoint.class, cascade = CascadeType.ALL)
    @JoinTable(name = "learning_path_has_knowledge_point",
            joinColumns = @JoinColumn(name = "learning_path_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "knowledge_point_id", referencedColumnName = "id"))
    public Collection<KnowledgePoint> getKnowledgePoints() {
        return knowledgePoints;
    }

    public void setKnowledgePoints(Collection<KnowledgePoint> knowledgePoints) {
        this.knowledgePoints = knowledgePoints;
    }

    @Basic
    @Column(name = "last_learning_time")
    public Timestamp getLastLearningTime() {
        return lastLearningTime;
    }

    public void setLastLearningTime(Timestamp lastLearningTime) {
        this.lastLearningTime = lastLearningTime;
    }

    @ManyToOne(targetEntity = Course.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", nullable = false, referencedColumnName = "id")
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @ManyToOne(targetEntity = Student.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_user_id", nullable = false, referencedColumnName = "user_id")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LearningPath that = (LearningPath) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }


}
