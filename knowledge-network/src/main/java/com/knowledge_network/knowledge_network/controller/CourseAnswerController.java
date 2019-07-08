package com.knowledge_network.knowledge_network.controller;

import com.knowledge_network.auth.security.anno.AuthorizationPermission;
import com.knowledge_network.knowledge_network.entity.CourseAnswer;
import com.knowledge_network.knowledge_network.service.CourseAnswerService;
import com.knowledge_network.knowledge_network.vo.CourseAnswerInfoVO;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.support.utils.VOUtils;
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
@RequestMapping(value = "/courseAnswer")
public class CourseAnswerController {

    @Autowired
    private CourseAnswerService courseAnswerService;

    /**
     * 获取当前问题下的回答
     *
     * @param pageVO——condition是问题id
     * @return
     */
    @AuthorizationPermission(permissions = {"COURSE_RETRIEVE"}, checkCurrentLoginUser = false)
    @RequestMapping(value = "/list", method = RequestMethod.PUT)
    public ResponseResult<ListVO<CourseAnswerInfoVO>> getCourseAnswer(@RequestBody PageVO pageVO) {
        List<CourseAnswer> courseAnswers = courseAnswerService.getCourseAnswersByCondition(pageVO.getStart(),
                pageVO.getRows(), VOUtils.conditionVO2Condition(pageVO.getConditions().get(0)));
        List<CourseAnswerInfoVO> courseAnswerInfoVOS = new ArrayList<>();

        if (courseAnswers != null) {
            for (CourseAnswer courseAnswer : courseAnswers) {
                courseAnswerInfoVOS.add(new CourseAnswerInfoVO(courseAnswer));
            }
            return ResponseResult.newSucceedInstance(null,
                    new ListVO<>(Integer.toUnsignedLong(courseAnswers.size()), pageVO.getStart(), 10, courseAnswerInfoVOS));
        }

        return ResponseResult.newSucceedInstance("No CourseAnswers", null);
    }

    // TODO: 2018/4/17 添加课程回答

    // TODO: 2018/4/17 更新课程回答

    // TODO: 2018/4/17 删除课程回答
}

