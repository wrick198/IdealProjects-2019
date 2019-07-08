package com.knowledge_network.knowledge_network.controller;

import com.knowledge_network.auth.security.anno.AuthorizationPermission;
import com.knowledge_network.knowledge_network.entity.CourseQuestion;
import com.knowledge_network.knowledge_network.service.CourseQuestionService;
import com.knowledge_network.knowledge_network.vo.CourseQuestionInfoVO;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.support.utils.VOUtils;
import com.knowledge_network.user.vo.ListVO;
import com.knowledge_network.user.vo.web.ConditionVO;
import com.knowledge_network.user.vo.web.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/courseQuestion")
public class CourseQuestionController {

    @Autowired
    private CourseQuestionService courseQuestionService;

    /**
     * 获取当前课程的问题(同样一页10条不可更改)
     *
     * @param pageVO——页面的起始页、条数、条件，条件是课程id
     * @return
     */
    @AuthorizationPermission(permissions = {"COURSE_RETRIEVE"}, checkCurrentLoginUser = false)
    @RequestMapping(value = "/list", method = RequestMethod.PUT)
    public ResponseResult<ListVO<CourseQuestionInfoVO>> getCourseQuestion(@RequestBody PageVO pageVO) {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        for (ConditionVO conditionVO : pageVO.getConditions()) {
            conditions.add(VOUtils.conditionVO2Condition(conditionVO));
        }
        List<CourseQuestion> courseQuestions = courseQuestionService.getCourseQuestionByCondition(pageVO.getStart(),
                pageVO.getRows(), conditions);
        List<CourseQuestionInfoVO> courseQuestionInfoVOS = new ArrayList<>();
        if (courseQuestions != null) {
            for (CourseQuestion courseQuestion : courseQuestions) {
                courseQuestionInfoVOS.add(courseQuestionService.getCourseQuestionInfo(courseQuestion));
            }
            return ResponseResult.newSucceedInstance(null,
                    new ListVO<>((long) courseQuestionInfoVOS.size(), pageVO.getStart(), 10, courseQuestionInfoVOS));
        }
        return ResponseResult.newSucceedInstance("No CourseQuestions", null);
    }

    /**
     * 添加课程问题到当前课程讨论区
     *
     * @param courseQuestionInfoVO
     * @return
     */
    @AuthorizationPermission(permissions = {"COURSE_RETRIEVE"}, checkCurrentLoginUser = false)
    @RequestMapping(value = "/addCourseQuestion", method = RequestMethod.POST)
    public ResponseResult<String> addCourseQuestion(@RequestBody CourseQuestionInfoVO courseQuestionInfoVO) {
        CourseQuestion courseQuestion = new CourseQuestion();
        if (courseQuestionInfoVO != null) {
            courseQuestionService.initCourseQuestionInfo(courseQuestion, courseQuestionInfoVO);
            courseQuestionService.createCourseQuestion(courseQuestionInfoVO.getCourseId(), courseQuestion);
            return ResponseResult.newSucceedInstance("Created Success!", null);
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.ADD_COURSE_QUESTION_FAIL, null);
    }

    // TODO: 2018/4/17 更新课程问题
    @AuthorizationPermission(permissions = {"COURSE_RETRIEVE"}, checkCurrentLoginUser = false)
    @RequestMapping(value = "/editCourseQuestion/{courseQuestionId}", method = RequestMethod.PUT)
    public ResponseResult<String> editCourseQuestion(@PathVariable String courseQuestionId, @RequestBody CourseQuestionInfoVO courseQuestionInfoVO) {
        CourseQuestion courseQuestion = courseQuestionService.getCourseQuestionById(Integer.parseInt(courseQuestionId));
        if (courseQuestion != null) {
            courseQuestionService.updateCourseQuestion(courseQuestion, courseQuestionInfoVO);
            return ResponseResult.newSucceedInstance("Edit Success!", null);
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.EDIT_COURSE_QUESTION_FAIL, null);
    }

    // TODO: 2018/4/17 删除课程问题

}
