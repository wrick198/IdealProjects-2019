package com.knowledge_network.quize.service.Impl;

import com.knowledge_network.quize.dao.StudentWrongNoteSubjectDAO;
import com.knowledge_network.quize.entity.StudentWrongNoteSubject;
import com.knowledge_network.quize.service.StudentWrongNoteSubjectService;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.utils.Asserts;
import com.knowledge_network.user.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/4/24
 **/
@Service
public class StudentWrongNoteSubjectServiceImpl implements StudentWrongNoteSubjectService {
    @Autowired
    StudentWrongNoteSubjectDAO studentWrongNoteSubjectDAO;

    @Override
    public List<StudentWrongNoteSubject> getStudentWrongNoteSubjectRecordByStudent(Student student) {
        return studentWrongNoteSubjectDAO.findStudentWrongNoteByStudent(student);
    }

    @Override
    public StudentWrongNoteSubject getStudentWrongNoteSubjectRecordById(int id) {
        return studentWrongNoteSubjectDAO.findStudentWrongNoteSubjectById(id);
    }

    @Override
    public void updateStudentWrongNoteSubjectCoverUrl(StudentWrongNoteSubject studentWrongNoteSubject, String coverUrl) {
        Asserts.notNull(studentWrongNoteSubject, ResponseErrorEnum.STUDENT_WRONG_NOTE_SUBJECT_NOT_NULL);
        Asserts.notNull(coverUrl, ResponseErrorEnum.WRONG_NOTE_COVER_URL_NOT_NULL);
        // TODO:验证数据库中是否能找到studentWrongNoteSubject这条记录
        studentWrongNoteSubject.setCoverUrl(coverUrl);
        studentWrongNoteSubjectDAO.updateStudentWrongNoteSubject(studentWrongNoteSubject);
    }

    @Override
    public void updateStudentWrongNoteSubjectName(StudentWrongNoteSubject studentWrongNoteSubject, String name) {
        Asserts.notNull(studentWrongNoteSubject, ResponseErrorEnum.STUDENT_WRONG_NOTE_SUBJECT_NOT_NULL);
        Asserts.notNull(name, ResponseErrorEnum.WRONG_NOTE_NAME_NOT_NULL);
        // TODO:验证数据库中是否能找到studentWrongNoteSubject这条记录
        studentWrongNoteSubject.setName(name);
        studentWrongNoteSubjectDAO.updateStudentWrongNoteSubject(studentWrongNoteSubject);
    }
}
