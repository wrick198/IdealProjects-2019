package com.knowledge_network.user.vo;

import com.knowledge_network.knowledge_network.vo.SubjectInfoVO;
import com.knowledge_network.user.entity.Teacher;

/**
 * Created by pentonbin on 17-12-14
 * <p>
 * 教师个人信息页面数据对象
 */
public class TeacherInfoVO extends UserInfoVO {

    private String certificateNum;
    private SchoolInfoVO school;
    private SubjectInfoVO subject;

    public TeacherInfoVO() {
    }

    public TeacherInfoVO(Teacher teacher) {
        super(teacher);
        if (teacher != null) {
            certificateNum = teacher.getCertificateNum();
//            school = new SchoolInfoVO(teacher.getSchool());
            subject = new SubjectInfoVO(teacher.getSubject());
        }
    }

    public String getCertificateNum() {
        return certificateNum;
    }

    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum;
    }

    public SchoolInfoVO getSchool() {
        return school;
    }

    public void setSchool(SchoolInfoVO school) {
        this.school = school;
    }

    public SubjectInfoVO getSubject() {
        return subject;
    }

    public void setSubject(SubjectInfoVO subject) {
        this.subject = subject;
    }
}
