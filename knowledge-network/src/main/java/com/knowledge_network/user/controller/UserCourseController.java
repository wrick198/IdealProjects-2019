package com.knowledge_network.user.controller;

import com.knowledge_network.auth.security.anno.AuthorizationPermission;
import com.knowledge_network.knowledge_network.entity.Course;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.support.exceptions.UserNotFoundException;
import com.knowledge_network.support.utils.Asserts;
import com.knowledge_network.support.utils.PageUtils;
import com.knowledge_network.user.entity.LearningPath;
import com.knowledge_network.user.entity.Student;
import com.knowledge_network.user.entity.User;
import com.knowledge_network.user.service.LearningPathService;
import com.knowledge_network.user.service.StudentService;
import com.knowledge_network.user.service.UserService;
import com.knowledge_network.user.vo.CourseVO;
import com.knowledge_network.user.vo.ListVO;
import com.knowledge_network.user.vo.web.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by pentonbin on 17-12-29
 * <p>
 * 课程相关接口
 */
@RestController
@RequestMapping(value = "/user/course")
public class UserCourseController {

    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private LearningPathService learningPathService;

    /**
     * 学生功能：获取加入的课程
     *
     * @param userId 学生id
     * @param pageVO 分页
     * @return
     * @throws UserNotFoundException
     */
    @AuthorizationPermission(permissions = {"USER_RETRIEVE", "COURSE_RETRIEVE"}, checkCurrentLoginUser = true)
    @RequestMapping(value = "/list/{userId}", method = RequestMethod.PUT)
    public ResponseResult<ListVO<CourseVO>> courseList(@PathVariable String userId, @RequestBody PageVO pageVO)
            throws UserNotFoundException {
        Asserts.notNull(pageVO, ResponseErrorEnum.BAD_REQUEST);

        Student student = studentService.getStudentById(Integer.parseInt(userId));
        if (student == null || student.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }
        List<CourseVO> courseVOList = new ArrayList<>();
        Collection<Course> courses = student.getCourses();
        List<Course> courseList = PageUtils.getPageListFromCollection(courses, pageVO);
        for (Course course : courseList) {
            courseVOList.add(new CourseVO(course));
        }
        return ResponseResult.newSucceedInstance(null,
                new ListVO<CourseVO>((long) courses.size(), pageVO.getStart(), courseVOList.size(), courseVOList));
    }

    /**
     * 学生功能：获取最近学习课程列表（已默认按照时间先后排序）
     *
     * @param userId 学生id
     * @param pageVO 分页
     * @return
     */
    @AuthorizationPermission(permissions = {"LEARNING_PATH_RETRIEVE"}, checkCurrentLoginUser = true)
    @RequestMapping(value = "/recent/{userId}")
    public ResponseResult<ListVO<CourseVO>> getRecentCourses(@PathVariable String userId, @RequestBody PageVO pageVO)
            throws UserNotFoundException {
        Asserts.notNull(pageVO, ResponseErrorEnum.BAD_REQUEST);

        Student student = studentService.getStudentById(Integer.parseInt(userId));
        if (student == null || student.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }
        List<LearningPath> learningPaths = learningPathService.getRecentLearningPath(student, pageVO.getStart(), pageVO.getRows());
        List<Course> courses = new ArrayList<>();
        for (LearningPath learningPath : learningPaths) {
            courses.add(learningPath.getCourse());
        }
        List<CourseVO> courseVOList = new ArrayList<>();
        for (Course course : courses) {
            courseVOList.add(new CourseVO(course));
        }
        return ResponseResult.newSucceedInstance(null,
                new ListVO<CourseVO>((long) learningPaths.size(), pageVO.getStart(), courseVOList.size(), courseVOList));
    }
}
