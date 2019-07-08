package com.knowledge_network.user.service.impl;

import com.knowledge_network.knowledge_network.entity.Course;
import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.knowledge_network.service.CourseService;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.utils.Asserts;
import com.knowledge_network.support.utils.StringUtils;
import com.knowledge_network.user.dao.StudentDAO;
import com.knowledge_network.user.entity.*;
import com.knowledge_network.user.service.*;
import com.knowledge_network.user.vo.StudentInfoVO;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by limingcheng on 17-12-7
 * <p>
 * 学生接口studentService的具体实现
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDAO studentDAO;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private LearningPathService learningPathService;
    @Autowired
    private LearningPlanService learningPlanService;
    @Autowired
    private SchoolService schoolService;

    /**
     * 检查学号是否存在
     *
     * @param idNum
     * @return
     */
    @Override
    public boolean checkStudentIdNumExist(String idNum) {
        if (StringUtils.isNullOrEmpty(idNum)) {
            return false;
        }
        return studentDAO.findStudentByIdNum(idNum.trim()) != null;
    }

    /**
     * 修改学生个人信息
     *
     * @param map
     * @return
     */
    @Override
    public boolean updateStudentInfo(Map<String, Object> map) {
        return false;
    }

    /**
     * 检查是否绑定了学生家长
     *
     * @param userId
     * @return
     */
    @Override
    public boolean checkStudentHasParent(int userId) {
        Student student = (Student) userService.getUserById(userId);
        if (student.getParents().isEmpty()) {
            return false;
        }
        return student.getParents() != null;
    }

    /**
     * 修改学生家长部分信息
     * 通过map传进的参数修改学生所绑定的家长部分信息（手机号，姓名）
     *
     * @param userId
     * @return
     */
    @Override
    public boolean updateStudentParentInfo(int userId, Map<String, Object> map) {
        return false;
    }

    /**
     * 获得学生该学科所有课程
     * 通过学科id和学生id查找学科所选课程
     *
     * @param userId
     * @return
     */
    @Override
    public Collection<Course> getStudentCourses(int userId, int subjectId) {
        Student student = (Student) userService.getUserById(userId);
        Collection<Course> subjectCourses = null;
        Collection<Course> studentCourses = student.getCourses();
        for (Course course : studentCourses) {
            if (course.getSubject().getId() != null && course.getSubject().getId() == subjectId) {
                subjectCourses.add(course);
            }
        }
        return student.getCourses();
    }

    /**
     * 获得学生当前课程
     *
     * @param courseId
     * @return
     */
    @Override
    public Course getStudentOneCourse(int courseId) {
        return courseService.getCourseById(courseId);
    }

    /**
     * 获得学生所在班级
     *
     * @param userId
     * @return
     */
    @Override
    public Clazz getStudentClass(int userId) {
        return null;
    }

    /**
     * 修改学生班级信息
     * 通过map传进的参数修改学生所在班级的信息
     *
     * @param map
     * @return
     */
    @Override
    public boolean updateStudentClassInfo(Map<String, Object> map) {
        return false;
    }

    /**
     * 获得学生知识图谱
     *
     * @param userId
     * @return
     */
    @Override
    public List<KnowledgePoint> getStudentKnowledgePointsMap(int userId) {
        return null;
    }

    /**
     * 获得学生该学科学习计划
     * 通过查找学生和课程获得学生学习计划
     *
     * @param userId
     * @param courseId
     * @return
     */
    @Override
    public LearningPlan getStudentLearningSubjectPlans(int userId, int courseId) {
        return learningPlanService.getLearningPlan(userId, courseId);
    }

    /**
     * 获得学生学习轨迹
     * 通过查找学生和课程所对应的科目获得学生学习轨迹
     *
     * @param userId
     * @param courseId
     * @return
     */
    @Override
    public LearningPath getStudentLearningSubjectPath(int userId, int courseId) {
        return learningPathService.getLearningPath(userId, courseId);
    }

    @Override
    public Student getStudentById(Integer id) {
        return studentDAO.findStudentById(id);
    }

    @Override
    public void updateStudentInfo(Student student, StudentInfoVO studentInfoVO) {
        // 同步到数据库使用userService，所以需要先设置学生的特有信息（例如学号）
        initStudentInfo(student, studentInfoVO);
        studentDAO.updateStudent(student);
    }

    @Override
    public void initStudentInfo(Student student, StudentInfoVO studentInfoVO) {
        student.setStudentIdNum(studentInfoVO.getStudentIdNum());
        student.setGrade(studentInfoVO.getGrade());
        if (studentInfoVO.getSchool() != null) {
            Asserts.notNull(studentInfoVO.getSchool().getId(), ResponseErrorEnum.SCHOOL_ID_NOT_NULL);
            student.setSchool(schoolService.getSchoolById(studentInfoVO.getSchool().getId()));
        }
        userService.initUserInfo(student, studentInfoVO);
    }

    @Override
    public void addParent(Student student, Parent parent) {
        if (student == null || parent == null) {
            return;
        }
        if (!student.getParents().contains(parent)) {
            // TODO：是否需要另一方确认？
            student.getParents().add(parent);
            parent.getStudents().add(student);
            studentDAO.updateStudent(student);
        }
    }

    @Override
    public Student getStudentByUsername(String username) {
        Asserts.hasText(username, ResponseErrorEnum.USERNAME_NOT_NULL);
        return studentDAO.findStudentByUsername(username);
    }

    @Override
    public void removeParent(Student student, Parent parent) {
        if (student == null || parent == null) {
            return;
        }
        if (student.getParents().contains(parent)) {
            student.getParents().remove(parent);
            parent.getStudents().remove(student);
            studentDAO.updateStudent(student);
        }
    }

    @Override
    public void createStudent(Student student) {
        if (student != null) {
            student.setRegisterDatetime(new Timestamp(System.currentTimeMillis()));
            student.setEnable(User.USER_ENABLE);
            student.setLogoff(User.USER_NON_LOGOFF);
            studentDAO.createStudent(student);
        }
    }

    @Override
    public List<Student> getAllStudentPage(int start, int rows) {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        conditions.add(new BaseHibernateDAO.Condition(BaseHibernateDAO.ConditionType.EQ, "logoff", User.USER_NON_LOGOFF));
        return getStudentPageByConditions(start, rows, conditions);
    }

    @Override
    public long getAllStudentCount() {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        conditions.add(new BaseHibernateDAO.Condition(BaseHibernateDAO.ConditionType.EQ, "logoff", User.USER_NON_LOGOFF));
        return getStudentCountByConditions(conditions);
    }

    @Override
    public List<Student> getStudentPageByConditions(int start, int rows, List<BaseHibernateDAO.Condition> conditions) {
        if (start < 0 || rows <= 0) {
            return new ArrayList<>();
        }
        // 排序：按照注册时间降序排列
        Order order = Order.desc("registerDatetime");
        // 筛选条件：conditions
        if (conditions != null && conditions.size() > 0) {
            return studentDAO.findStudentByConditionsOrderPage(start, rows, order, conditions);
        }
        return studentDAO.findStudentByOrderPage(start, rows, order);
    }

    @Override
    public long getStudentCountByConditions(List<BaseHibernateDAO.Condition> conditions) {
        return studentDAO.getStudentCountByConditions(conditions);
    }

    @Override
    public void updateStudent(Student student) {
        studentDAO.updateStudent(student);
    }
}
