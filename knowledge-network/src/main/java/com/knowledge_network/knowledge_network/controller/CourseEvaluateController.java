package com.knowledge_network.knowledge_network.controller;

import com.knowledge_network.auth.security.anno.AuthorizationPermission;
import com.knowledge_network.knowledge_network.entity.CourseEvaluate;
import com.knowledge_network.knowledge_network.service.CourseEvaluateService;
import com.knowledge_network.knowledge_network.service.CourseService;
import com.knowledge_network.knowledge_network.vo.CourseEvaluateInfoVO;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.support.utils.VOUtils;
import com.knowledge_network.user.service.UserService;
import com.knowledge_network.user.vo.ListVO;
import com.knowledge_network.user.vo.web.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/courseEvaluate")
public class CourseEvaluateController {

    @Autowired
    private CourseEvaluateService courseEvaluateService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;

    /**
     * 获取课程评价信息(一页10条不可更改)
     * // TODO: 2018/4/17 已测
     *
     * @param pageVO，其中查询条件只是课程ID
     * @return
     */
    @AuthorizationPermission(permissions = {"COURSE_RETRIEVE"}, checkCurrentLoginUser = false)
    @RequestMapping(value = "/list", method = RequestMethod.PUT)
    public ResponseResult<ListVO<CourseEvaluateInfoVO>> getCourseEvaluateList(@RequestBody PageVO pageVO) {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        List<CourseEvaluateInfoVO> courseEvaluateInfoVOs = new ArrayList<>();
        //有条件的筛选：即某一课程ID下筛选，否则通用
        if (pageVO.getConditions() != null && pageVO.getConditions().size() > 0) {
            // TODO: 2018/4/18
            BaseHibernateDAO.Condition condition = VOUtils.conditionVO2Condition(pageVO.getConditions().get(0));
            if (condition.getProperty().equalsIgnoreCase("course") && courseService.getCourseById(Integer.parseInt(condition.getValue().toString())) != null) {
                conditions.add(condition);
            }
        }
        List<CourseEvaluate> courseEvaluates = courseEvaluateService.getCourseEvaluateByCondition(pageVO.getStart(), pageVO.getRows(), conditions);
        if (courseEvaluates != null) {
            for (CourseEvaluate courseEvaluate : courseEvaluates) {
                courseEvaluateInfoVOs.add(new CourseEvaluateInfoVO(courseEvaluate));
            }
            return ResponseResult.newSucceedInstance(null,
                    new ListVO<>((long) courseEvaluateInfoVOs.size(), pageVO.getStart(), 10, courseEvaluateInfoVOs));
        } else {
            return ResponseResult.newSucceedInstance("No Courses", null);
        }

    }

    /**
     * 添加课程评价信息（暂时只允许参加了课程的学生评价）
     *
     * @param courseEvaluateInfoVO
     * @return
     */
    @AuthorizationPermission(permissions = {"COURSE_RETRIEVE"}, checkCurrentLoginUser = false)
    @RequestMapping(value = "/addCourseEvaluate", method = RequestMethod.POST)
    public ResponseResult<String> addCourseEvaluate(@RequestBody CourseEvaluateInfoVO courseEvaluateInfoVO) {
        //判断当前学生是否参加了课程
        if (courseEvaluateInfoVO != null
                && courseService.checkEntryCourse(courseService.getCourseById(courseEvaluateInfoVO.getCourseId()))) {
            CourseEvaluate courseEvaluate = new CourseEvaluate();
            courseEvaluateService.initCourseEvaluate(courseEvaluate, courseEvaluateInfoVO);
            courseEvaluateService.createCourseEvaluate(courseEvaluateInfoVO.getCourseId(), courseEvaluate);
            return ResponseResult.newSucceedInstance("Submit Success", null);
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.EVALUATE_COURSE_FAIL, null);
    }

    /**
     * 更新课程评价信息(暂时只允许参加了课程的学生评价)
     *
     * @param courseEvaluateInfoVO
     * @return
     */
    @AuthorizationPermission(permissions = {"COURSE_RETRIEVE"}, checkCurrentLoginUser = false)
    @RequestMapping(value = "/updateCourseEvaluate", method = RequestMethod.POST)
    public ResponseResult<String> updateCourseEvaluate(@RequestBody CourseEvaluateInfoVO courseEvaluateInfoVO) {
        if (courseEvaluateInfoVO != null
                && courseService.checkEntryCourse(courseService.getCourseById(courseEvaluateInfoVO.getCourseId()))) {
            CourseEvaluate courseEvaluate = new CourseEvaluate();
            courseEvaluateService.initCourseEvaluate(courseEvaluate, courseEvaluateInfoVO);
            courseEvaluateService.updateCourseEvaluate(courseEvaluate);
            return ResponseResult.newSucceedInstance("Updated Success", null);
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.EVALUATE_COURSE_FAIL, null);
    }

    /**
     * 删除课程评价信息（暂时只允许参加了课程的学生删除）
     *
     * @param courseEvaluateInfoVO
     * @return
     */
    @AuthorizationPermission(permissions = {"COURSE_RETRIEVE"}, checkCurrentLoginUser = false)
    @RequestMapping(value = "/deleteCourseEvaluate", method = RequestMethod.POST)
    public ResponseResult<String> deleteCourseEvaluate(@RequestBody CourseEvaluateInfoVO courseEvaluateInfoVO) {
        if (courseEvaluateInfoVO != null
                && courseService.checkEntryCourse(courseService.getCourseById(courseEvaluateInfoVO.getCourseId()))) {
            CourseEvaluate courseEvaluate = new CourseEvaluate();
            courseEvaluateService.initCourseEvaluate(courseEvaluate, courseEvaluateInfoVO);
            courseEvaluateService.deleteCourseEvaluate(courseEvaluate);
            return ResponseResult.newSucceedInstance("Delete Success", null);
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.DELETE_COURSE_FAIL, null);
    }
}
