package com.knowledge_network.user.vo;

import com.knowledge_network.user.entity.Parent;
import com.knowledge_network.user.entity.Student;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by pentonbin on 17-12-14
 * <p>
 * 学生个人信息页面数据对象
 */
public class StudentInfoVO extends UserInfoVO {

    private String studentIdNum;
    private Integer grade;
    private SchoolInfoVO school;
    private Collection<ParentInfoVO> parents;

    public StudentInfoVO() {
    }

    public StudentInfoVO(Student student, boolean setupParentsInfo) {
        super(student);
        if (student != null) {
            this.studentIdNum = student.getStudentIdNum();
            this.grade = student.getGrade();
            this.parents = new ArrayList<>();
            this.school = new SchoolInfoVO(student.getSchool());
            if (setupParentsInfo) {
                Collection<Parent> parents = student.getParents();
                ParentInfoVO parentInfoVO = null;
                for (Parent parent : parents) {
                    parentInfoVO = new ParentInfoVO(parent, false);
                    this.parents.add(parentInfoVO);
                }
            }
        }
    }

    public String getStudentIdNum() {
        return studentIdNum;
    }

    public void setStudentIdNum(String studentIdNum) {
        this.studentIdNum = studentIdNum;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Collection<ParentInfoVO> getParents() {
        return parents;
    }

    public void setParents(Collection<ParentInfoVO> parents) {
        this.parents = parents;
    }

    public SchoolInfoVO getSchool() {
        return school;
    }

    public void setSchool(SchoolInfoVO school) {
        this.school = school;
    }
}
