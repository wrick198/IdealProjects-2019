package com.knowledge_network.knowledge_network.vo;

import com.knowledge_network.knowledge_network.entity.Course;
import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.user.vo.UserInfoVO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wjk
 * Date: 17-12-28
 * Time: 上午9:49
 * <p>
 * Modified by Lwh
 * 课程简介页面对象
 */
public class CourseInfoVO {
    private Integer id;
    private String name;
    private Integer courseType;
    private Integer grade;
    private Integer subjectId;
    private Integer score;
    private Integer rank;
    private Float rate;
    private Timestamp startTime;
    private Timestamp endTime;
    private String school;
    //    private String teacher; //教师真实姓名
    private Integer teacherId;
    private UserInfoVO teacher;
    private String coursePic;
    private String introduction;
    private Long entries;
    private Integer hour;
    private List<Integer> knowledgePointId = new ArrayList<>();
//    private List<KnowledgePointVO> knowledgePoints = new ArrayList<>();

    public CourseInfoVO() {

    }

    public CourseInfoVO(Course course) {
        if (course != null) {
            id = course.getId();
            name = course.getName();
            courseType = course.getCourseType();
            grade = course.getGrade();
            subjectId = course.getSubject().getId();
            score = course.getScore();
            rank = course.getRank();
            rate = course.getRate();
            startTime = course.getStartTime();
            endTime = course.getEndTime();
            school = course.getSchool();
            coursePic = course.getCoursePic();
            introduction = course.getIntroduction();
            entries = course.getEntries();
            hour = course.getHour();
            teacher = new UserInfoVO(course.getTeacher());
            teacherId = course.getTeacher().getId();
            for (KnowledgePoint knowledgePoint : course.getKnowledgePoints()) {
                knowledgePointId.add(knowledgePoint.getId());
            }

        }
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

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subject) {
        this.subjectId = subject;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCoursePic() {
        return coursePic;
    }

    public void setCoursePic(String coursePic) {
        this.coursePic = coursePic;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Long getEntries() {
        return entries;
    }

    public void setEntries(Long entries) {
        this.entries = entries;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

//    public List<KnowledgePointVO> getKnowledgePoints() {
//        return knowledgePoints;
//    }
//
//    public void setKnowledgePoints(List<KnowledgePointVO> knowledgePoints) {
//        this.knowledgePoints = knowledgePoints;
//    }

    public UserInfoVO getTeacher() {
        return teacher;
    }

    public void setTeacher(UserInfoVO teacher) {
        this.teacher = teacher;
    }

    public List<Integer> getKnowledgePointId() {
        return knowledgePointId;
    }

    public void setKnowledgePointId(List<Integer> knowledgePointId) {
        this.knowledgePointId = knowledgePointId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}
