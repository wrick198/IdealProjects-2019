package com.knowledge_network.user.dao;

import com.knowledge_network.knowledge_network.entity.Course;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.Clazz;
import com.knowledge_network.user.entity.Student;
import org.hibernate.criterion.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by limingcheng on 17-12-7
 * <p>
 * 提供访问用户表student的DAO
 */
public interface StudentDAO {

    /**
     * 通过id查找学生
     *
     * @param studentId
     * @return
     */
    Student findStudentById(int studentId);

    /**
     * 通过学号查找学生
     *
     * @param studentIdNum
     * @return
     */
    Student findStudentByIdNum(String studentIdNum);

    /**
     * 查找某班学生
     *
     * @param clazz
     * @return
     */
    List<Student> findStudentByClass(Clazz clazz);

    /**
     * 通过学生用户名查找学生
     *
     * @param studentName
     * @return
     */
    Student findStudentByUsername(String studentName);

    /**
     * 更新学生表信息
     *
     * @param map
     */
    boolean saveStudent(Map<String, Object> map);

    /**
     * 查找某课程学生
     *
     * @param course
     * @return
     */
    List<Student> findStudentByCourse(Course course);

    /**
     * 创建新的学生用户
     *
     * @param student 学生用户
     * @author pentonbin
     */
    void createStudent(Student student);

    /**
     * 根据条件获取学生分页
     *
     * @param start      起始下标
     * @param rows       每一页行数
     * @param conditions 条件
     * @return
     * @author pentonbin
     */
    List<Student> findStudentByConditionsOrderPage(int start, int rows, Order order, List<BaseHibernateDAO.Condition> conditions);

    /**
     * 分页获取学生对象
     *
     * @param order 排序
     * @param start 起始下标
     * @param rows  每一页行数
     * @return 符合条件的用户
     * @author pentonbin
     */
    List<Student> findStudentByOrderPage(int start, int rows, Order order);

    /**
     * 获取筛选条件后学生的总数
     *
     * @param conditions 筛选条件，如果为null则返回所有用户数量
     * @return 用户总数
     * @author pentonbin
     */
    long getStudentCountByConditions(List<BaseHibernateDAO.Condition> conditions);

    /**
     * 更新学生对象
     *
     * @param student
     */
    void updateStudent(Student student);

}
