package com.knowledge_network.user.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by pentonbin on 17-12-1
 * <p>
 * 2B之后的班级概念
 */
@Entity
@Table(name = "class")
public class Clazz implements Serializable {

    /**
     * 班级id
     * 与业务无关，自动生成（递增）
     */
    private Integer id;
    /**
     * 年级
     */
    private Integer grade;
    /**
     * 班号
     */
    private Integer classNum;
    /**
     * 班级简介/格言等
     */
    private String description;
    /**
     * 班级所在学校
     */
    private School school;
    /**
     * 该班级学生
     */
    private Collection<Student> students = new ArrayList<>();


    public Clazz() {
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
    @Column(name = "grade")
    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "class_num")
    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(targetEntity = School.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @OneToMany(targetEntity = Student.class, cascade = CascadeType.ALL, mappedBy = "clazz")
    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clazz clazz = (Clazz) o;

        if (id != clazz.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }
}
