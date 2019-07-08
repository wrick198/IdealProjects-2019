package com.knowledge_network.knowledge_network.service.impl;

import com.knowledge_network.auth.user.LoginUserHolder;
import com.knowledge_network.knowledge_network.dao.CourseDAO;
import com.knowledge_network.knowledge_network.entity.Course;
import com.knowledge_network.knowledge_network.service.CourseService;
import com.knowledge_network.knowledge_network.service.KnowledgePointService;
import com.knowledge_network.knowledge_network.service.SubjectService;
import com.knowledge_network.knowledge_network.vo.CourseInfoVO;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.exceptions.CourseNotFoundException;
import com.knowledge_network.support.utils.Asserts;
import com.knowledge_network.support.utils.JsonMapper;
import com.knowledge_network.user.entity.Student;
import com.knowledge_network.user.entity.Teacher;
import com.knowledge_network.user.service.StudentService;
import com.knowledge_network.user.service.UserService;
import com.knowledge_network.user.vo.UserInfoVO;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDAO courseDAO;
    @Autowired
    private StudentService studentService;
    @Autowired
    private KnowledgePointService knowledgePointService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private UserService userService;


    @Override
    public void removeCourse(Integer id) {
        Course course = courseDAO.findCourseById(id);
        if (course != null) {
            courseDAO.removeCourse(course);
        }
    }

    @Override
    public Course getCourseById(Integer id) {
        if (id == null) {
            return null;
        }
        return courseDAO.findCourseById(id);
    }

    @Override
    public CourseInfoVO getCourseInfo(String courseId) throws CourseNotFoundException {
        Asserts.notNull(courseId, ResponseErrorEnum.COURSE_NOT_FOUND);

        Integer id = Integer.parseInt(courseId);
        Course course = getCourseById(id);
        if (course == null) {
            throw new CourseNotFoundException(courseId);
        }
        return new CourseInfoVO(course);
    }

    @Override
    public void updateCourseInfo(Course course, CourseInfoVO courseInfoVO) {
        initCourseInfo(course, courseInfoVO);
        courseDAO.updateCourse(course);
    }

    @Override
    public void initCourseInfo(Course course, CourseInfoVO courseInfoVO) {
        course.setName(courseInfoVO.getName());
        course.setCourseType(courseInfoVO.getCourseType());
        course.setGrade(courseInfoVO.getGrade());
        course.setStartTime(courseInfoVO.getStartTime());
        course.setEndTime(courseInfoVO.getEndTime());
        course.setSchool(courseInfoVO.getSchool());
        course.setCoursePic(courseInfoVO.getCoursePic());
        course.setIntroduction(courseInfoVO.getIntroduction());
        course.setHour(courseInfoVO.getHour());
        for (Integer knowledgePointId : courseInfoVO.getKnowledgePointId()) {
            course.getKnowledgePoints().add(knowledgePointService.getKnowledgePointByID(knowledgePointId));
        }
        course.setSubject(subjectService.getSubjectById(courseInfoVO.getSubjectId()));
        Teacher teacher = new Teacher();
        if (course.getTeacher() == null) {
            teacher = (Teacher) userService.getUserById(courseInfoVO.getTeacherId());
            course.setTeacher(teacher);
        }
    }

    @Override
    public List<Course> getAllCoursesPerPage(int start, int rows) {
//        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
//        conditions.add(new BaseHibernateDAO.Condition(BaseHibernateDAO.ConditionType.EQ, "", 0));
        return getCoursesPageByConditions(start, rows, null);
    }

    @Override
    public List<Course> getCoursesPageByConditions(int start, int rows, List<BaseHibernateDAO.Condition> conditions) {
        if (start < 0 || rows <= 0) {
            return new ArrayList<>();
        }
        Order order = Order.desc("publishTime");
        if (conditions != null && conditions.size() > 0) {
            return courseDAO.findCoursesByConditionsOrderPage(start, rows, order, conditions);
        }
        return courseDAO.findCoursesByOrderPage(start, rows, order);
    }

    @Override
    public long getAllCourseCount() {
        return 0;
    }

    @Override
    public long getCourseCountByConditions(List<BaseHibernateDAO.Condition> conditions) {
        return 0;
    }

    @Override
    public void createCourse(Course course) {
        if (course != null) {
            course.setPublishTime(new Timestamp(System.currentTimeMillis()));
            course.setEnabled(Course.COURSE_ENABLE);
            course.setStatus(Course.COURSE_START);
            course.setEntries(0L);
            course.setRate(0F);
            courseDAO.updateCourse(course);
        }
    }

    @Override
    public void removeCourse(Course course) {
        courseDAO.removeCourse(course);
    }

    @Override
    public void updateCourse(Course course) {
        courseDAO.updateCourse(course);
    }

    @Override
    public void updateCourseInfoByJson(Course course, String Json) {
        Asserts.hasText(Json, ResponseErrorEnum.BAD_REQUEST);

        initCourseInfo(course, JsonMapper.json2Obj(Json, CourseInfoVO.class));
//        course.setEnabled(Course.COURSE_ENABLE);
        courseDAO.updateCourse(course);
    }

    /**
     * // TODO: 2018/4/17  通过jython来获取相似课程
     *
     * @param userId   当前用户
     * @param courseId 当前课程
     * @return
     */
    @Override
    public List<Course> getRecommendCourses(String userId, String courseId) {
        return null;
    }

    /**
     * 检查用户是否参加了该课程
     *
     * @param course
     * @return false表示没选，true表示选了
     */
    @Override
    public boolean checkEntryCourse(Course course) {
        Integer userId = LoginUserHolder.getInstance().getCurrentLoginUser().getUserId();
        Student student = studentService.getStudentById(userId);
        if (student.getCourses().contains(course)) {
            return true;
        }
        return false;
    }

    /**
     * 学生参加课程
     *
     * @param course
     */
    @Override
    public boolean entryCourse(Course course) {
        Integer userId = LoginUserHolder.getInstance().getCurrentLoginUser().getUserId();
        Student student = studentService.getStudentById(userId);
        if (!checkEntryCourse(course)) {
            student.getCourses().add(course);
            course.getAllStudents().add(student);
            studentService.updateStudent(student);
            courseDAO.updateCourse(course);
            return true;
        }
        return false;
    }

    /**
     * 学生退出课程
     *
     * @param course
     */
    @Override
    public boolean exitCourse(Course course) {
        Integer userId = LoginUserHolder.getInstance().getCurrentLoginUser().getUserId();
        Student student = studentService.getStudentById(userId);
        if (checkEntryCourse(course)) {
            student.getCourses().remove(course);
            course.getAllStudents().remove(student);
            studentService.updateStudent(student);
            courseDAO.updateCourse(course);
            return true;
        }
        return false;
    }


}
