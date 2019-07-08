package com.knowledge_network.user.dao.impl;

import com.knowledge_network.knowledge_network.entity.Course;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.dao.StudentDAO;
import com.knowledge_network.user.entity.Clazz;
import com.knowledge_network.user.entity.Student;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by limingcheng on 17-12-7
 * <p>
 * 提供访问用户表student的DAO
 */
@Repository
public class StudentDAOImpl extends BaseHibernateDAO<Student> implements StudentDAO {

    /**
     * 通过id查找学生
     *
     * @param userId
     * @return
     */
    @Override
    public Student findStudentById(int userId) {
        return findById(userId);
    }

    /**
     * 通过学号查找学生
     *
     * @param studentIdNum
     * @return
     */
    @Override
    public Student findStudentByIdNum(String studentIdNum) {
        return (Student) findByUnique("studentIdNum", studentIdNum);
    }

    /**
     * 查找某班学生
     *
     * @param clazz
     * @return
     */
    @Override
    public List<Student> findStudentByClass(Clazz clazz) {
        return findBy("clazz", clazz);
    }

    /**
     * 通过姓名查找学生
     *
     * @param username
     * @return
     */
    @Override
    public Student findStudentByUsername(String username) {
        return findByUnique("username", username);
    }

    /**
     * 查找某课程学生
     *
     * @param course
     * @return
     */
    @Override
    public List<Student> findStudentByCourse(Course course) {
        return null;
    }

    @Override
    public void createStudent(Student student) {
        save(student);
    }

    @Override
    public List<Student> findStudentByConditionsOrderPage(int start, int rows, Order order, List<Condition> conditions) {
        return findByOrderConditionsPage(start, rows, order, conditions);
    }

    @Override
    public List<Student> findStudentByOrderPage(int start, int rows, Order order) {
        return findStudentByConditionsOrderPage(start, rows, order, null);
    }

    @Override
    public long getStudentCountByConditions(List<Condition> conditions) {
        return findCountByConditions(conditions);
    }

    @Override
    public void updateStudent(Student student) {
        save(student);
    }

    /**
     * 更新学生表信息
     *
     * @param map
     * @return
     */
    @Override
    public boolean saveStudent(Map<String, Object> map) {
        return false;
    }
}
