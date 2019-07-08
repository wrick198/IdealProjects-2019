package com.knowledge_network.quize.entity;

import com.knowledge_network.user.entity.Student;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * ** Created by gongjiangtao on 2018/5/2
 * 学生已做错题实体类
 **/
@Entity
@Table(name = "student_has_done_quize")
public class HasDoneQuize implements Serializable {
    /**
     * 学生试题联合外键
     */
    private StudentHasDoneQuizePK studentHasDoneQuizePK = new StudentHasDoneQuizePK();
    /**
     * 练习次数
     */
    private int practiceTime = 0;
    /**
     * 错误次数
     */
    private int wrongTime = 0;
    /**
     * 正确次数
     */
    private int rightTime = 0;
    /**
     * 与学生的关联
     */
    private Student student;
    /**
     * 与试题的关联
     */
    private Quize quize;

    public HasDoneQuize() {

    }

    public HasDoneQuize(Student student, Quize quize) {
        this.student = student;
        this.quize = quize;

        student.getHasDoneQuizes().add(this);
        quize.getHasDoneByStudent().add(this);

        studentHasDoneQuizePK.studentId = student.getId();
        studentHasDoneQuizePK.quizeId = quize.getId();
    }

    @EmbeddedId
    public StudentHasDoneQuizePK getStudentHasDoneQuizePK() {
        return studentHasDoneQuizePK;
    }

    public void setStudentHasDoneQuizePK(StudentHasDoneQuizePK studentHasDoneQuizePK) {
        this.studentHasDoneQuizePK = studentHasDoneQuizePK;
    }

    @Column(name = "practice_time", nullable = false)
    public int getPracticeTime() {
        return practiceTime;
    }

    public void setPracticeTime(int practiceTime) {
        this.practiceTime = practiceTime;
    }

    @Column(name = "wrong_time", nullable = false)
    public int getWrongTime() {
        return wrongTime;
    }

    public void setWrongTime(int wrongTime) {
        this.wrongTime = wrongTime;
    }

    @Column(name = "right_time", nullable = false)
    public int getRightTime() {
        return rightTime;
    }

    public void setRightTime(int rightTime) {
        this.rightTime = rightTime;
    }

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToOne
    @JoinColumn(name = "quize_id", insertable = false, updatable = false)
    public Quize getQuize() {
        return quize;
    }

    public void setQuize(Quize quize) {
        this.quize = quize;
    }

    @Embeddable
    private class StudentHasDoneQuizePK implements Serializable {
        private int studentId;
        private int quizeId;

        @Column(name = "student_id", nullable = false)
        public int getStudentId() {
            return studentId;
        }

        public void setStudentId(int studentId) {
            this.studentId = studentId;
        }

        @Column(name = "quize_id", nullable = false)
        public int getQuizeId() {
            return quizeId;
        }

        public void setQuizeId(int quizeId) {
            this.quizeId = quizeId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StudentHasDoneQuizePK that = (StudentHasDoneQuizePK) o;
            return studentId == that.studentId &&
                    quizeId == that.quizeId;
        }

        @Override
        public int hashCode() {

            return Objects.hash(studentId, quizeId);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HasDoneQuize that = (HasDoneQuize) o;
        return Objects.equals(studentHasDoneQuizePK, that.studentHasDoneQuizePK);
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentHasDoneQuizePK);
    }
}
