package com.knowledge_network.quize.entity;

import com.knowledge_network.knowledge_network.entity.Subject;
import com.knowledge_network.user.entity.Student;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * ** Created by gongjiangtao on 2018/4/24
 * 学生科目错题集实体类
 **/
@Entity
@Table(name = "student_wrong_note_subject")
public class StudentWrongNoteSubject implements Serializable {
    /**
     * 学生科目联合主键
     */
    private StudentWrongNoteSubjectPK studentWrongNoteSubjectPK = new StudentWrongNoteSubjectPK();
    /**
     * 错题本名称
     */
    private String name;
    /**
     * 封面图片url
     */
    private String coverUrl;
    /**
     * 与学生的关联
     */
    private Student student;
    /**
     * 与科目关联
     */
    private Subject subject;

    public StudentWrongNoteSubject() { }

    public StudentWrongNoteSubject(Student student, Subject subject, String name, String coverUrl) {
        this.student = student;
        this.subject = subject;
        this.name = name;
        this.coverUrl = coverUrl;

        studentWrongNoteSubjectPK.studentId = student.getId();
        studentWrongNoteSubjectPK.subjectId = subject.getId();


    }

    @EmbeddedId
    public StudentWrongNoteSubjectPK getStudentWrongNoteSubjectPK() {
        return studentWrongNoteSubjectPK;
    }

    public void setStudentWrongNoteSubjectPK(StudentWrongNoteSubjectPK studentWrongNoteSubjectPK) {
        this.studentWrongNoteSubjectPK = studentWrongNoteSubjectPK;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "cover_url", nullable = false)
    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
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
    @JoinColumn(name = "subject_id", insertable = false, updatable = false)
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Embeddable
    private class StudentWrongNoteSubjectPK implements Serializable {
        private int studentId;
        private int subjectId;

        @Column(name = "student_id", nullable = false)
        public int getStudentId() {
            return studentId;
        }

        public void setStudentId(int studentId) {
            this.studentId = studentId;
        }

        @Column(name = "subject_id", nullable = false)
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
            StudentWrongNoteSubjectPK that = (StudentWrongNoteSubjectPK) o;
            return studentId == that.studentId &&
                    subjectId == that.subjectId;
        }

        @Override
        public int hashCode() {

            return Objects.hash(studentId, subjectId);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentWrongNoteSubject that = (StudentWrongNoteSubject) o;
        return Objects.equals(studentWrongNoteSubjectPK, that.studentWrongNoteSubjectPK) &&
                Objects.equals(name, that.name) &&
                Objects.equals(coverUrl, that.coverUrl) &&
                Objects.equals(student, that.student) &&
                Objects.equals(subject, that.subject);
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentWrongNoteSubjectPK, name, coverUrl, student, subject);
    }
}
