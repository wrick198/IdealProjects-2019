package com.knowledge_network.user.entity;

import com.knowledge_network.circle.entity.Collect;
import com.knowledge_network.knowledge_network.entity.Course;
import com.knowledge_network.quize.entity.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by pentonbin on 17-12-1
 */
@Entity
@Table(name = "student")
@PrimaryKeyJoinColumn(name = "user_id")
public class Student extends User {

    /**
     * 学生学号（注意不是学生id）
     */
    private String studentIdNum;
    /**
     * 学生年级
     */
    private Integer grade;
    /**
     * 学生参加的课程列表
     */
    private Collection<Course> courses = new ArrayList<>();
    /**
     * 学生收藏的课程列表
     */
    private Collection<Course> collectedCourses = new ArrayList<>();
    /**
     * 学生关联的家长
     */
    private Collection<Parent> parents = new ArrayList<>();
    /**
     * 学生的学习计划
     */
    private Collection<LearningPlan> learningPlans = new ArrayList<>();
    /**
     * 学生的学习轨迹
     */
    private Collection<LearningPath> learningPaths = new ArrayList<>();
    /**
     * 学生所在的学校
     */
    private School school;
    /**
     * 学生所在班级
     * 如果还成为2B模式（班主任认领），则该字段为null
     */
    private Clazz clazz;
    /**
     * 与错题关联
     */
    private Collection<WrongNote> wrongNotes = new ArrayList<>();
    /**
     * 与错题集关联
     */
    private Collection<StudentWrongNoteSubject> wrongNoteSubjects = new ArrayList<>();
    /**
     * 与学生已做错题的关联
     */
    private Collection<HasDoneQuize> hasDoneQuizes = new ArrayList<>();
    /**
     * 与答题卡关联
     */
    private Collection<AnswerSheet> answerSheets = new ArrayList<>();

    public Student() {
    }

    @Basic
    @Column(name = "student_id_num")
    public String getStudentIdNum() {
        return studentIdNum;
    }

    public void setStudentIdNum(String studentIdNum) {
        this.studentIdNum = studentIdNum;
    }

    @Basic
    @Column(name = "grade")
    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @ManyToMany(targetEntity = Course.class, cascade = CascadeType.ALL)
    @JoinTable(name = "student_has_course",
            joinColumns = @JoinColumn(name = "student_user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }

    @ManyToMany(targetEntity = Course.class, cascade = CascadeType.ALL)
    @JoinTable(name = "student_collected_course",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    public Collection<Course> getCollectedCourses() {
        return collectedCourses;
    }

    public void setCollectedCourses(Collection<Course> collectedCourses) {
        this.collectedCourses = collectedCourses;
    }

    @ManyToMany(targetEntity = Parent.class, cascade = CascadeType.ALL)
    @JoinTable(name = "student_has_parent",
            joinColumns = @JoinColumn(name = "student_user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_user_id", referencedColumnName = "user_id")
    )
    public Collection<Parent> getParents() {
        return parents;
    }

    public void setParents(Collection<Parent> parents) {
        this.parents = parents;
    }

    @OneToMany(targetEntity = LearningPlan.class, cascade = CascadeType.ALL, mappedBy = "student")
    public Collection<LearningPlan> getLearningPlans() {
        return learningPlans;
    }

    public void setLearningPlans(Collection<LearningPlan> learningPlans) {
        this.learningPlans = learningPlans;
    }

    @OneToMany(targetEntity = LearningPath.class, cascade = CascadeType.ALL, mappedBy = "student")
    public Collection<LearningPath> getLearningPaths() {
        return learningPaths;
    }

    public void setLearningPaths(Collection<LearningPath> learningPaths) {
        this.learningPaths = learningPaths;
    }

    @ManyToOne(targetEntity = School.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @ManyToOne(targetEntity = Clazz.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    @OneToMany(targetEntity = WrongNote.class, mappedBy = "student", cascade = CascadeType.ALL)
    public Collection<WrongNote> getWrongNotes() {
        return wrongNotes;
    }

    public void setWrongNotes(Collection<WrongNote> wrongNotes) {
        this.wrongNotes = wrongNotes;
    }

    @OneToMany(targetEntity = StudentWrongNoteSubject.class, cascade = CascadeType.ALL, mappedBy = "student")
    public Collection<StudentWrongNoteSubject> getWrongNoteSubjects() {
        return wrongNoteSubjects;
    }

    public void setWrongNoteSubjects(Collection<StudentWrongNoteSubject> wrongNoteSubjects) {
        this.wrongNoteSubjects = wrongNoteSubjects;
    }

    @OneToMany(targetEntity = HasDoneQuize.class, cascade = CascadeType.ALL, mappedBy = "student")
    public Collection<HasDoneQuize> getHasDoneQuizes() {
        return hasDoneQuizes;
    }

    public void setHasDoneQuizes(Collection<HasDoneQuize> hasDoneQuizes) {
        this.hasDoneQuizes = hasDoneQuizes;
    }

    @OneToMany(targetEntity = AnswerSheet.class, cascade = CascadeType.ALL, mappedBy = "student")
    public Collection<AnswerSheet> getAnswerSheets() {
        return answerSheets;
    }

    public void setAnswerSheets(Collection<AnswerSheet> answerSheets) {
        this.answerSheets = answerSheets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (id != student.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }
}
