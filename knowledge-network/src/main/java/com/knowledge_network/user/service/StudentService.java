package com.knowledge_network.user.service;

import com.knowledge_network.knowledge_network.entity.Course;
import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.*;
import com.knowledge_network.user.vo.StudentInfoVO;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by limingcheng on 17-12-7
 * <p>
 * 提供学生接口student的Service
 */
public interface StudentService {

    /**
     * 判断学号是否存在
     *
     * @param idNum
     * @return
     */
    boolean checkStudentIdNumExist(String idNum);

    /**
     * 设置学生个人信息
     *
     * @param map
     * @return
     */
    boolean updateStudentInfo(Map<String, Object> map);

    /**
     * 判断学生是否绑定了家长
     *
     * @param userId
     * @return
     */
    boolean checkStudentHasParent(int userId);

    /**
     * 绑定或修改学生家长部分信息
     *
     * @param userId
     * @return
     */
    boolean updateStudentParentInfo(int userId, Map<String, Object> map);

    /**
     * 获得学生的某学科所有课程
     *
     * @param userId
     * @return
     */
    Collection<Course> getStudentCourses(int userId, int subjectId);

    /**
     * 获得学生某一课程
     *
     * @param courseId
     * @return
     */
    Course getStudentOneCourse(int courseId);

    /**
     * 获得学生班级
     *
     * @param userId
     * @return
     */
    Clazz getStudentClass(int userId);

    /**
     * 修改学生班级信息
     *
     * @param map
     * @return
     */
    boolean updateStudentClassInfo(Map<String, Object> map);

    /**
     * 获得知识图谱
     *
     * @param userId
     * @return
     */
    List<KnowledgePoint> getStudentKnowledgePointsMap(int userId);

    /**
     * 获得学生学习计划
     *
     * @param userId
     * @param courseId
     * @return
     */
    LearningPlan getStudentLearningSubjectPlans(int userId, int courseId);

    /**
     * 获得学生学习轨迹
     *
     * @param userId
     * @param courseId
     * @return
     */
    LearningPath getStudentLearningSubjectPath(int userId, int courseId);

    /**
     * 根据学生id获取学生对象
     *
     * @param id 学生id
     * @return 学生对象
     * @author pentonbin
     */
    Student getStudentById(Integer id);

    /**
     * 修改学生个人信息，将同步到数据库
     *
     * @param student       学生用户
     * @param studentInfoVO 要修改的信息
     * @author pentonbin
     */
    void updateStudentInfo(Student student, StudentInfoVO studentInfoVO);

    /**
     * 初始化学生个人信息，将不同步到数据库
     *
     * @param student       学生用户
     * @param studentInfoVO 要修改的信息
     */
    void initStudentInfo(Student student, StudentInfoVO studentInfoVO);

    /**
     * 为学生添加家长关联
     *
     * @param student 学生对象
     * @param parent  家长对象
     * @author pentonbin
     */
    void addParent(Student student, Parent parent);

    /**
     * 根据学生用户名获取学生对象
     *
     * @param username 学生用户名
     * @return 学生对象
     * @author pentonbin
     */
    Student getStudentByUsername(String username);

    /**
     * 学生移除关联的家长
     *
     * @param student 学生
     * @param parent  移除关联的家长对象
     * @author pentonbin
     */
    void removeParent(Student student, Parent parent);

    /**
     * 创建新的学生用户
     *
     * @param student 学生用户
     * @author pentonbin
     */
    void createStudent(Student student);

    /**
     * 获取所有学生分页
     *
     * @param start 起始下标
     * @param rows  每一页行数
     * @return 分页的学生
     * @author pentonbin
     */
    List<Student> getAllStudentPage(int start, int rows);

    /**
     * 获取所有学生人数
     *
     * @return 人数
     */
    long getAllStudentCount();

    /**
     * 根据条件获取学生分页
     *
     * @param start      起始下标
     * @param rows       每一页行数
     * @param conditions 条件
     * @return
     * @author pentonbin
     */
    List<Student> getStudentPageByConditions(int start, int rows, List<BaseHibernateDAO.Condition> conditions);

    /**
     * 根据条件获取学生人数
     *
     * @param conditions 条件
     * @return
     * @author pentonbin
     */
    long getStudentCountByConditions(List<BaseHibernateDAO.Condition> conditions);

    /**
     * 更新学生信息
     * @param student
     */
    void updateStudent(Student student);
}
