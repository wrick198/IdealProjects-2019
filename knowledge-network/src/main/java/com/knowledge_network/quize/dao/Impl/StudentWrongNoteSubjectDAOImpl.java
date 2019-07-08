package com.knowledge_network.quize.dao.Impl;

import com.knowledge_network.quize.dao.StudentWrongNoteSubjectDAO;
import com.knowledge_network.quize.entity.StudentWrongNoteSubject;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/4/24
 * 学生科目错题集DAO接口的实现
 **/
@Repository
public class StudentWrongNoteSubjectDAOImpl extends BaseHibernateDAO<StudentWrongNoteSubject> implements StudentWrongNoteSubjectDAO {
    @Override
    public List<StudentWrongNoteSubject> findStudentWrongNoteByStudent(Student student) {
        return findBy("student", student);
    }

    @Override
    public StudentWrongNoteSubject findStudentWrongNoteSubjectById(int id) {
        return findById(id);
    }

    @Override
    public void updateStudentWrongNoteSubject(StudentWrongNoteSubject studentWrongNoteSubject) {
        save(studentWrongNoteSubject);
    }

    @Override
    public void deleteStudentWrongNoteSubject(StudentWrongNoteSubject studentWrongNoteSubject) {
        delete(studentWrongNoteSubject);
    }
}
