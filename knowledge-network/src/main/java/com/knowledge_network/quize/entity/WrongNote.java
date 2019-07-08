package com.knowledge_network.quize.entity;

import com.knowledge_network.knowledge_network.entity.Subject;
import com.knowledge_network.user.entity.Student;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * ** Created by gongjiangtao on 2018/4/16
 **/
@Entity
@Table(name = "wrong_note")
public class WrongNote implements Serializable {
    /**
     * 学生-错题-试题-科目多表关联联合主键
     */
    private WrongNotePK wrongNotePK = new WrongNotePK();

    /**
     * 训练次数
     */
    private int practiceTime;
    /**
     * 与学生的关联
     */
    private Student student;
    /**
     * 与测试题的关联
     */
    private Quize quize;
    /**
     * 与科目的关联
     */
    private Subject subject;

    public WrongNote() {
    }

    /**
     * 多表关联的构造函数
     *
     * @param student      学生对象
     * @param quize        试题对象
     * @param subject      科目对象
     * @param practiceTime 训练次数
     */
    public WrongNote(Student student, Quize quize, Subject subject, int practiceTime) {
        this.student = student;
        this.quize = quize;
        this.subject = subject;
        this.practiceTime = practiceTime;

        wrongNotePK.studentId = student.getId();
        wrongNotePK.quizeId = quize.getId();
        wrongNotePK.subjectId = subject.getId();

        student.getWrongNotes().add(this);
        quize.getWrongNotes().add(this);
        subject.getWrongNotes().add(this);
    }

    @EmbeddedId
    public WrongNotePK getWrongNotePK() {
        return wrongNotePK;
    }

    public void setWrongNotePK(WrongNotePK wrongNotePK) {
        this.wrongNotePK = wrongNotePK;
    }

    @Column(name = "practice_time", nullable = false)
    public int getPracticeTime() {
        return practiceTime;
    }

    public void setPracticeTime(int practiceTime) {
        this.practiceTime = practiceTime;
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

    @ManyToOne
    @JoinColumn(name = "subject_id", insertable = false, updatable = false)
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Embeddable
    public class WrongNotePK implements Serializable {
        private int studentId;
        private int quizeId;
        private int subjectId;

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

        @Column(name = "subject_Id", nullable = false)
        public int getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(int subjectId) {
            this.subjectId = subjectId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            WrongNotePK that = (WrongNotePK) o;
            return studentId == that.studentId &&
                    quizeId == that.quizeId &&
                    subjectId == that.subjectId;
        }

        @Override
        public int hashCode() {

            return Objects.hash(studentId, quizeId, subjectId);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WrongNote wrongNote = (WrongNote) o;
        return Objects.equals(wrongNotePK, wrongNote.wrongNotePK);
    }

    @Override
    public int hashCode() {

        return Objects.hash(wrongNotePK);
    }
}
