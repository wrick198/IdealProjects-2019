package com.knowledge_network.user.vo;

import com.knowledge_network.knowledge_network.entity.Course;
import com.knowledge_network.knowledge_network.vo.SubjectInfoVO;

/**
 * Created by pentonbin on 17-12-29
 * <p>
 * 课程页面数据对象
 */
public class CourseVO {

    private Integer id;
    private String name;
    private Integer courseType;
    private Integer grade;
    private Integer score;
    private Integer rank;
    private SubjectInfoVO subject;

    public CourseVO() {
    }

    public CourseVO(Course course) {
        id = course.getId();
        name = course.getName();
        courseType = course.getCourseType();
        grade = course.getGrade();
        score = course.getScore();
        rank = course.getRank();
        subject = new SubjectInfoVO(course.getSubject());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public SubjectInfoVO getSubject() {
        return subject;
    }

    public void setSubject(SubjectInfoVO subject) {
        this.subject = subject;
    }
}
