package com.knowledge_network.quize.vo;

import com.knowledge_network.quize.entity.AnswerSheet;
import com.knowledge_network.quize.entity.StudentAnswer;
import com.knowledge_network.user.entity.Teacher;
import com.knowledge_network.user.vo.StudentInfoVO;
import com.knowledge_network.user.vo.TeacherInfoVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/5/3
 **/
public class AnswerSheetInfoVO {
    private Integer id;

    private Double score;

    private boolean isPassed;

    private Date startTime;

    private Date endTime;

    private StudentInfoVO student;

    private List<TeacherInfoVO> teachers = new ArrayList<>();

    private PaperInfoVO paper;

    private List<StudentAnswerInfoVO> answers = new ArrayList<>();

    public AnswerSheetInfoVO() {

    }

    public AnswerSheetInfoVO(AnswerSheet answerSheet) {
        this.id = answerSheet.getId();
        this.score = answerSheet.getScore();
        this.isPassed = answerSheet.isPassed();
        this.startTime = answerSheet.getStartTime();
        this.endTime = answerSheet.getEndTime();
        this.student = new StudentInfoVO(answerSheet.getStudent(), false);
        this.paper = new PaperInfoVO(answerSheet.getPaper());
        for (StudentAnswer sa : answerSheet.getStudentAnswers())
            this.answers.add(new StudentAnswerInfoVO(sa));
        for (Teacher t : answerSheet.getTeachers())
            this.teachers.add(new TeacherInfoVO(t));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public StudentInfoVO getStudent() {
        return student;
    }

    public void setStudent(StudentInfoVO student) {
        this.student = student;
    }

    public List<TeacherInfoVO> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<TeacherInfoVO> teachers) {
        this.teachers = teachers;
    }

    public PaperInfoVO getPaper() {
        return paper;
    }

    public void setPaper(PaperInfoVO paper) {
        this.paper = paper;
    }

    public List<StudentAnswerInfoVO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<StudentAnswerInfoVO> answers) {
        this.answers = answers;
    }
}
