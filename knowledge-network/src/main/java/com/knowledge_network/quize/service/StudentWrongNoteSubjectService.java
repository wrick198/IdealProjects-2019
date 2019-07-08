package com.knowledge_network.quize.service;

import com.knowledge_network.quize.entity.StudentWrongNoteSubject;
import com.knowledge_network.user.entity.Student;

import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/4/24
 * 学生科目错题集业务层接口
 **/
public interface StudentWrongNoteSubjectService {
    /**
     * 根据Id获取错题集记录
     *
     * @param id 待查id
     * @return id对应的错题集记录
     */
    StudentWrongNoteSubject getStudentWrongNoteSubjectRecordById(int id);

    /**
     * 获取某学生所有科目的错题集记录
     *
     * @param student 待查学生
     * @return 该学生所有科目的错题集记录
     */
    List<StudentWrongNoteSubject> getStudentWrongNoteSubjectRecordByStudent(Student student);

    /**
     * 更新错题集记录的名称
     *
     * @param studentWrongNoteSubject 待更新的错题集记录
     * @param name                    新的名称
     */
    void updateStudentWrongNoteSubjectName(StudentWrongNoteSubject studentWrongNoteSubject, String name);

    /**
     * 更新错题集记录的封面url
     *
     * @param studentWrongNoteSubject 待更新的错题集
     * @param coverUrl                新的封面url
     */
    void updateStudentWrongNoteSubjectCoverUrl(StudentWrongNoteSubject studentWrongNoteSubject, String coverUrl);
}
