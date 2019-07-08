package com.knowledge_network.user.vo;

import com.knowledge_network.user.entity.Parent;
import com.knowledge_network.user.entity.Student;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by pentonbin on 17-12-14
 * <p>
 * 家长个人信息页面数据对象
 */
public class ParentInfoVO extends UserInfoVO {

    private Collection<StudentInfoVO> students;

    public ParentInfoVO() {
    }

    public ParentInfoVO(Parent parent, boolean setupStudentsInfo) {
        super(parent);
        this.students = new ArrayList<>();
        if (setupStudentsInfo) {
            Collection<Student> students = parent.getStudents();
            for (Student student : students) {
                StudentInfoVO studentInfoVO = new StudentInfoVO(student, false);
                this.students.add(studentInfoVO);
            }
        }
    }

    public Collection<StudentInfoVO> getStudents() {
        return students;
    }

    public void setStudents(Collection<StudentInfoVO> students) {
        this.students = students;
    }
}
