package com.knowledge_network.quize.dao;

import com.knowledge_network.quize.entity.StudentWrongNoteSubject;
import com.knowledge_network.user.entity.Student;

import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/4/24
 * 学生科目错题集DAO接口
 **/
public interface StudentWrongNoteSubjectDAO {
    /**
     * 通过id获取错题集记录
     * @param id 待查询的id
     * @return id为id的学生科目错题集记录
     */
    StudentWrongNoteSubject findStudentWrongNoteSubjectById(int id);

    /**
     * 获取某学生所有科目的错题集
     * @param student 待查学生
     * @return 该学生所有科目的错题集
     */
    List<StudentWrongNoteSubject> findStudentWrongNoteByStudent(Student student);

    /**
     * 更新学生科目错题集记录
     * @param studentWrongNoteSubject 待更新的错题集对象
     */
    void updateStudentWrongNoteSubject(StudentWrongNoteSubject studentWrongNoteSubject);

    /**
     * 删除错题集
     * @param studentWrongNoteSubject 待删除的错题集
     */
    void deleteStudentWrongNoteSubject(StudentWrongNoteSubject studentWrongNoteSubject);
}
