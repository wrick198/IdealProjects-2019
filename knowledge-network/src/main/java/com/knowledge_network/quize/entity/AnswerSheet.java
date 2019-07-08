package com.knowledge_network.quize.entity;

import com.knowledge_network.user.entity.Student;
import com.knowledge_network.user.entity.Teacher;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * ** Created by gongjiangtao on 2018/5/2
 * 答题卡实体类
 **/
@Entity
@Table(name = "answer_sheet")
public class AnswerSheet implements Serializable {
    /**
     * id主键
     */
    private Integer id;
    /**
     * 得分
     */
    private Double score = 0.0;
    /**
     * 是否合格
     */
    private boolean passed;
    /**
     * 答题的开始时间
     */
    private Date startTime;
    /**
     * 答题结束时间
     */
    private Date endTime;
    /**
     * 与学生的关联
     * 多对一
     */
    private Student student;
    /**
     * 与教师的关联
     * 多对多
     */
    private List<Teacher> teachers = new ArrayList<>();
    /**
     * 与试卷的关联
     * 多对一
     */
    private Paper paper;
    /**
     * 与学生提交的答案关联
     * 一对多
     */
    private List<StudentAnswer> studentAnswers = new ArrayList<>();

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "score", nullable = false)
    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Column(name = "passed", nullable = false)
    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    @Column(name = "start_time", nullable = false)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Column(name = "end_time")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToMany(mappedBy = "checkedAnswerSheets")
    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    @ManyToOne
    @JoinColumn(name = "paper_id", nullable = false)
    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    @OneToMany(targetEntity = StudentAnswer.class, cascade = CascadeType.ALL, mappedBy = "answerSheet")
    public List<StudentAnswer> getStudentAnswers() {
        return studentAnswers;
    }

    public void setStudentAnswers(List<StudentAnswer> studentAnswers) {
        this.studentAnswers = studentAnswers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerSheet that = (AnswerSheet) o;
        return passed == that.passed &&
                Objects.equals(id, that.id) &&
                Objects.equals(score, that.score) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(teachers, that.teachers) &&
                Objects.equals(paper, that.paper) &&
                Objects.equals(studentAnswers, that.studentAnswers);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, score, passed, startTime, endTime, student, teachers, paper, studentAnswers);
    }
}
