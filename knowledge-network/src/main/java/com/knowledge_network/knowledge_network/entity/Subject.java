package com.knowledge_network.knowledge_network.entity;

import com.knowledge_network.quize.entity.Quize;
import com.knowledge_network.quize.entity.StudentWrongNoteSubject;
import com.knowledge_network.quize.entity.WrongNote;
import com.knowledge_network.user.entity.Teacher;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by pentonbin on 17-12-1
 */
@Entity
@Table(name = "subject")
public class Subject implements Serializable {
    /**
     * 科目id
     * 与业务无关，自动生成（递增）
     */
    private Integer id;
    /**
     * 科目名称
     */
    private String name;
    /**
     * 科目下所有知识点
     */
    private Collection<KnowledgePoint> knowledgePoints = new ArrayList<>();
    /**
     * 科目为当前科目名称的所有课程
     */
    private Collection<Course> courses = new ArrayList<>();
    /**
     * 教师所教科目为当前科目名称的所有教师
     */
    private Collection<Teacher> teachers = new ArrayList<>();
    /**
<<<<<<< HEAD
     * 与试题关联
     */
    private Collection<Quize> quizes = new ArrayList<>();
    /**
     * 与错题关联
     */
    private Collection<WrongNote> wrongNotes = new ArrayList<>();
    /**
     *
     */
    private Collection<StudentWrongNoteSubject> studentWrongNoteSubjects = new ArrayList<>();
    /**
     * 当前学科下的所有资源
     */
    private Collection<Resource> resources = new ArrayList<>();

    public Subject() {
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
    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(targetEntity = KnowledgePoint.class, cascade = CascadeType.ALL, mappedBy = "subject")
    public Collection<KnowledgePoint> getKnowledgePoints() {
        return knowledgePoints;
    }

    public void setKnowledgePoints(Collection<KnowledgePoint> knowledgePoints) {
        this.knowledgePoints = knowledgePoints;
    }

    @OneToMany(targetEntity = Course.class, cascade = CascadeType.ALL, mappedBy = "subject")
    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }

    @OneToMany(targetEntity = Teacher.class, cascade = CascadeType.ALL, mappedBy = "subject")
    public Collection<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Collection<Teacher> teachers) {
        this.teachers = teachers;
    }

    @OneToMany(targetEntity = Quize.class, cascade = CascadeType.ALL, mappedBy = "subject")
    public Collection<Quize> getQuizes() {
        return quizes;
    }

    public void setQuizes(Collection<Quize> quizes) {
        this.quizes = quizes;
    }

    @OneToMany(targetEntity = WrongNote.class, mappedBy = "subject", cascade = CascadeType.ALL)
    public Collection<WrongNote> getWrongNotes() {
        return wrongNotes;
    }

    public void setWrongNotes(Collection<WrongNote> wrongNotes) {
        this.wrongNotes = wrongNotes;
    }

    @OneToMany(targetEntity = StudentWrongNoteSubject.class, cascade = CascadeType.ALL, mappedBy = "subject")
    public Collection<StudentWrongNoteSubject> getStudentWrongNoteSubjects() {
        return studentWrongNoteSubjects;
    }

    public void setStudentWrongNoteSubjects(Collection<StudentWrongNoteSubject> studentWrongNoteSubjects) {
        this.studentWrongNoteSubjects = studentWrongNoteSubjects;
    }

    @OneToMany(targetEntity = Resource.class, cascade = CascadeType.ALL, mappedBy = "subject")
    public Collection<Resource> getResources() {
        return resources;
    }

    public void setResources(Collection<Resource> resources) {
        this.resources = resources;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (id != subject.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }
}
