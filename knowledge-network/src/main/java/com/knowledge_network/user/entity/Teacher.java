package com.knowledge_network.user.entity;

import com.knowledge_network.circle.entity.Collect;
import com.knowledge_network.knowledge_network.entity.Course;
import com.knowledge_network.knowledge_network.entity.Resource;
import com.knowledge_network.knowledge_network.entity.Subject;
import com.knowledge_network.quize.entity.AnswerSheet;
import com.knowledge_network.quize.entity.Paper;
import com.knowledge_network.quize.entity.Quize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by pentonbin on 17-12-1
 */
@Entity
@Table(name = "teacher")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "user_id")
public class Teacher extends User {

    /**
     * 教师所在学校
     */
    private School school;
    /**
     * 指定授课科目
     */
    private Subject subject;
    /**
     * 教师证号
     */
    private String certificateNum;
    /**
     * 教师所上传资源
     */
    private Collection<Resource> resources;
    /**
     * 教师出的试卷
     */
    private Collection<Paper> papers;
    /**
     * 教师出的试题
     */
    private Collection<Quize> quizes = new ArrayList<>();
    /**
     * 与课程关联
     */
    private Collection<Course> courses = new ArrayList<>();
    /**
     * 与答题卡关联，教师改过的答题卡
     */
    private Collection<AnswerSheet> checkedAnswerSheets = new ArrayList<>();

    public Teacher() {
    }

    @Basic
    @Column(name = "certificate_num", nullable = false)
    public String getCertificateNum() {
        return certificateNum;
    }

    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum;
    }

    @ManyToOne(targetEntity = School.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @ManyToOne(targetEntity = Subject.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @OneToMany(targetEntity = Course.class, cascade = CascadeType.ALL, mappedBy = "teacher")
    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }

    @ManyToMany(targetEntity = Paper.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "teacher_has_paper",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "paper_id")
    )
    public Collection<Paper> getPapers() {
        return papers;
    }

    public void setPapers(Collection<Paper> papers) {
        this.papers = papers;
    }

    @OneToMany(targetEntity = Quize.class, cascade = CascadeType.ALL, mappedBy = "creator")
    public Collection<Quize> getQuizes() {
        return quizes;
    }

    public void setQuizes(Collection<Quize> quizes) {
        this.quizes = quizes;
    }

    @ManyToMany(targetEntity = AnswerSheet.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "teacher_check_answer_sheet",
            joinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "answersheet_id", referencedColumnName = "id")
    )
    public Collection<AnswerSheet> getCheckedAnswerSheets() {
        return checkedAnswerSheets;
    }

    public void setCheckedAnswerSheets(Collection<AnswerSheet> checkedAnswerSheets) {
        this.checkedAnswerSheets = checkedAnswerSheets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (id != teacher.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }
}
