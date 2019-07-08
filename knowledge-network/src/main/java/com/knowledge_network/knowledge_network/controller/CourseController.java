package com.knowledge_network.knowledge_network.controller;

import com.knowledge_network.auth.security.anno.AuthorizationPermission;
import com.knowledge_network.auth.user.KnowledgeNetworkUserDetails;
import com.knowledge_network.auth.user.LoginUserHolder;
import com.knowledge_network.knowledge_network.entity.Course;
import com.knowledge_network.knowledge_network.service.CourseService;
import com.knowledge_network.knowledge_network.service.SubjectService;
import com.knowledge_network.knowledge_network.vo.CourseInfoVO;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.support.exceptions.CourseNotFoundException;
import com.knowledge_network.support.utils.Asserts;
import com.knowledge_network.support.utils.IOUtils;
import com.knowledge_network.support.utils.JsonMapper;
import com.knowledge_network.support.utils.VOUtils;
import com.knowledge_network.user.entity.UserRole;
import com.knowledge_network.user.vo.ListVO;
import com.knowledge_network.user.vo.web.ConditionVO;
import com.knowledge_network.user.vo.web.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private SubjectService subjectService;

    /**
     * 查看学生已有的课程
     * 接口在 {@link com.knowledge_network.user.controller.UserCourseController#courseList(String, PageVO)}
     */

    /**
     * 查看学生最近的课程
     * 接口在{@link com.knowledge_network.user.controller.UserCourseController#getRecentCourses(String, PageVO)}
     */

    /**
     * 获取所有课程的列表（或者是科目/年级筛选过的课程列表）
     *
     * @param pageVO
     * @return
     */
    @AuthorizationPermission(permissions = {"COURSE_RETRIEVE"}, checkCurrentLoginUser = false)
    @RequestMapping(value = "/list", method = RequestMethod.PUT)
    public ResponseResult<ListVO<CourseInfoVO>> getCourseList(@RequestBody PageVO pageVO) {
        Asserts.notNull(pageVO, ResponseErrorEnum.BAD_REQUEST);
        Asserts.notNull(pageVO.getStart(), ResponseErrorEnum.OTHER_START_NOT_NULL);
        Asserts.notNull(pageVO.getRows(), ResponseErrorEnum.OTHER_ROWS_NOT_NULL);

        Integer grade = null;
        Integer subject_id = null;
        if (pageVO.getConditions() != null && pageVO.getConditions().size() > 0) {
            // 先保存一下要筛选的条件——科目和年级
            for (ConditionVO conditionVO : pageVO.getConditions()) {
                if (subject_id != null && grade != null) {
                    break;
                }
                if (conditionVO.getProperty().equalsIgnoreCase("grade")
                        && conditionVO.getOp().equalsIgnoreCase(BaseHibernateDAO.ConditionType.EQ.getOp())) {
                    grade = (Integer) conditionVO.getValue();
                } else if (conditionVO.getProperty().equalsIgnoreCase("subject")
                        && conditionVO.getOp().equalsIgnoreCase(BaseHibernateDAO.ConditionType.EQ.getOp())) {
                    subject_id = (Integer) conditionVO.getValue();
                }
            }
        }
        long totalCount = 0;
        List<Course> courses = null;
        List<CourseInfoVO> courseInfoVOS = new ArrayList<>();
        if (grade == null && subject_id == null) {
            courses = courseService.getAllCoursesPerPage(pageVO.getStart(), pageVO.getRows());
            totalCount = courseService.getAllCourseCount();
        } else {
            List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
            if (grade != null) {
                conditions.add(new BaseHibernateDAO.Condition(BaseHibernateDAO.ConditionType.EQ,
                        "grade", grade));
            }
            if (subject_id != null) {
                conditions.add(new BaseHibernateDAO.Condition(BaseHibernateDAO.ConditionType.EQ,
                        "subject", subjectService.getSubjectById(subject_id)));
            }
            courses = courseService.getCoursesPageByConditions(pageVO.getStart(), pageVO.getRows(), conditions);
        }
        if (courses != null && courses.size() > 0) {
            for (Course course : courses) {
                courseInfoVOS.add(new CourseInfoVO(course));
            }
            return ResponseResult.newSucceedInstance(null,
                    new ListVO<>(totalCount, pageVO.getStart(), courseInfoVOS.size(), courseInfoVOS));
        }

        return ResponseResult.newSucceedInstance("No Course Found", null);
    }

    /**
     * 获取课程的具体信息
     *
     * @param courseId
     * @return
     * @throws CourseNotFoundException
     */
    @AuthorizationPermission(permissions = {"COURSE_RETRIEVE"}, checkCurrentLoginUser = false)
    @RequestMapping(value = "/courseInfo/{courseId}", method = RequestMethod.GET)
    public ResponseResult<CourseInfoVO> courseInfo(@PathVariable String courseId) throws CourseNotFoundException {
        if (courseService.getCourseById(Integer.parseInt(courseId)) != null) {
            CourseInfoVO courseInfoVO = courseService.getCourseInfo(courseId);
            return ResponseResult.newSucceedInstance(null, courseInfoVO);
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.COURSE_NOT_FOUND, null);
    }

    /**
     * 添加新课程
     *
     * @param request
     * @return
     */
    @AuthorizationPermission(permissions = {"COURSE_CREATE"}, checkCurrentLoginUser = false)
    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    public ResponseResult<String> addCourse(HttpServletRequest request) {
        String json = IOUtils.readDataFromHttpServletRequest(request);
        Map<String, Object> jsonMap = JsonMapper.json2Map(json);

//        Asserts.hasText((String)jsonMap.get("knowledgePoint"), ResponseErrorEnum.COURSE_HAS_NO_KNOWLEDGEPOINT);
//        List<KnowledgePoint> knowledgePoints = (List<KnowledgePoint>) jsonMap.get("knowledgePoint");
        // TODO: 2018/4/16 课程带有的知识点属性是一个列表，转换是否会成功还未知
        CourseInfoVO courseInfoVO = JsonMapper.json2Obj(json, CourseInfoVO.class);
        courseInfoVO.setTeacherId(LoginUserHolder.getInstance().getCurrentLoginUser().getUserId());
        Course course = new Course();
        if (courseInfoVO != null) {
            Asserts.notNull(jsonMap.get("knowledgePointId"), ResponseErrorEnum.COURSE_HAS_NO_KNOWLEDGEPOINT);
            courseService.initCourseInfo(course, courseInfoVO);
            courseService.createCourse(course);
            return ResponseResult.newSucceedInstance("Created Succeeded", null);
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.CREATE_COURSE_FAIL, null);
    }

    /**
     * 根据课程id来修改课程信息
     *
     * @param courseId
     * @param request
     * @return
     * @throws CourseNotFoundException
     */
    @AuthorizationPermission(permissions = {"COURSE_UPDATE"}, checkCurrentLoginUser = false)
    @RequestMapping(value = "/editCourse/{courseId}", method = RequestMethod.PUT)
    public ResponseResult<String> editCourse(@PathVariable String courseId, HttpServletRequest request) throws CourseNotFoundException {
        Integer id = Integer.parseInt(courseId);
        Course course = courseService.getCourseById(id);
        if (course == null) {
            throw new CourseNotFoundException(courseId);
        } else {
            String json = IOUtils.readDataFromHttpServletRequest(request);
            courseService.updateCourseInfoByJson(course, json);
            return ResponseResult.newSucceedInstance("Edit Success!", null);
        }
    }

    /**
     * 根据ID删除课程（不要轻易测试！！！爆炸输出的接口！）
     *
     * @param courseId
     * @return
     */
    @AuthorizationPermission(permissions = {"COURSE_DELETE"}, checkCurrentLoginUser = false)
    @RequestMapping(value = "/deleteCourse/{courseId}", method = RequestMethod.GET)
    public ResponseResult<String> deleteCourse(@PathVariable String courseId) {
        Integer id = Integer.parseInt(courseId);
        Course course = courseService.getCourseById(id);
        if (course != null) {
            KnowledgeNetworkUserDetails currentLoginUser = LoginUserHolder.getInstance().getCurrentLoginUser();
            if (course.getTeacher().getId().equals(currentLoginUser.getUserId())) {
                courseService.removeCourse(course);
            } else {
                Collection<UserRole> roles = currentLoginUser.getUserRoles();
                List<String> rolesStrings = new ArrayList<>();
                for (UserRole role : roles) {
                    rolesStrings.add(role.getRole());
                }
                if (rolesStrings.contains(UserRole.KNOWLEDGE_NETWORK_MANAGER) || rolesStrings.contains(UserRole.SYSTEM_MANAGER)) {
                    courseService.removeCourse(course);
                } else {
                    return ResponseResult.newErrorInstance(ResponseErrorEnum.DELETE_COURSE_FAIL, null);
                }
            }
            return ResponseResult.newSucceedInstance("Delete Success!", null);
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.COURSE_NOT_FOUND, null);
    }

    /**
     * 根据课程名字搜索课程
     *
     * @param pageVO
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.PUT)
    public ResponseResult<ListVO<? extends CourseInfoVO>> searchCourse(@RequestBody PageVO pageVO) {
        Asserts.notNull(pageVO, ResponseErrorEnum.BAD_REQUEST);
        Asserts.notEmpty(pageVO.getConditions(), ResponseErrorEnum.CONDITION_NOT_NULL);

        if (pageVO.getConditions().get(0).getProperty().equalsIgnoreCase("courseName")
                && pageVO.getConditions().get(0).getOp().equalsIgnoreCase(BaseHibernateDAO.ConditionType.LIKE.getOp())) {
            BaseHibernateDAO.Condition condition = VOUtils.conditionVO2Condition(pageVO.getConditions().get(0));
            List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
            conditions.add(condition);

            List<Course> courses = courseService.getCoursesPageByConditions(pageVO.getStart(), pageVO.getRows(), conditions);
            long count = courseService.getCourseCountByConditions(conditions);

            List<CourseInfoVO> courseInfoVOS = new ArrayList<>();
            for (Course course : courses) {
                courseInfoVOS.add(new CourseInfoVO(course));
            }

            return ResponseResult.newSucceedInstance(null,
                    new ListVO<>(count, pageVO.getStart(), courses.size(), courseInfoVOS));
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.BAD_REQUEST, null);
    }

    /**
     * 学生参加课程
     *
     * @param courseId
     * @return
     */
    @AuthorizationPermission(permissions = {"COURSE_RETRIEVE"}, checkCurrentLoginUser = false)
    @RequestMapping(value = "/entry/{courseId}", method = RequestMethod.GET)
    public ResponseResult<String> entryCourse(@PathVariable String courseId) {
        Course course = courseService.getCourseById(Integer.parseInt(courseId));
        if (course != null) {
            if (courseService.entryCourse(course)) {
                return ResponseResult.newSucceedInstance("Entry success!", null);
            }
            return ResponseResult.newErrorInstance(ResponseErrorEnum.ENTRY_COURSE_FAIL, null);
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.COURSE_NOT_FOUND, null);
    }

    /**
     * 学生退出课程
     *
     * @param courseId
     * @return
     */
    @AuthorizationPermission(permissions = {"COURSE_RETRIEVE"}, checkCurrentLoginUser = false)
    @RequestMapping(value = "/exit/{courseId}", method = RequestMethod.GET)
    public ResponseResult<String> exitCourse(@PathVariable String courseId) {
        Course course = courseService.getCourseById(Integer.parseInt(courseId));
        if (course != null) {
            if (courseService.exitCourse(course)) {
                return ResponseResult.newSucceedInstance("Exit success!", null);
            }
            return ResponseResult.newErrorInstance(ResponseErrorEnum.EXIT_COURSE_FAIL, null);
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.COURSE_NOT_FOUND, null);
    }

    // TODO: 2018/4/17 推荐课程获取
    @AuthorizationPermission(permissions = {"COURSE_RETRIEVE"}, checkCurrentLoginUser = false)
    @RequestMapping(value = "/recommend/{courseId}", method = RequestMethod.GET)
    public ResponseResult<ListVO<CourseInfoVO>> getRecommendCourse(@PathVariable String courseId) {
        return null;
    }

}
