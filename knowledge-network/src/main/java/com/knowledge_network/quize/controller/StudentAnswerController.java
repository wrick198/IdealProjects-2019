package com.knowledge_network.quize.controller;

import com.knowledge_network.quize.entity.StudentAnswer;
import com.knowledge_network.quize.service.StudentAnswerService;
import com.knowledge_network.quize.vo.StudentAnswerInfoVO;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.support.utils.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ** Created by gongjiangtao on 2018/5/5
 * 学生答案控制器，供教师改卷用
 **/
@RestController
@RequestMapping("/studentAnswer")
public class StudentAnswerController {
    @Autowired
    private StudentAnswerService studentAnswerService;

    /**
     * 更新分数
     *
     * @param studentAnswerInfoVO
     * @return
     */
    @RequestMapping(value = "/updateScore", method = RequestMethod.POST)
    ResponseResult<String> updateScore(@RequestBody StudentAnswerInfoVO studentAnswerInfoVO) {
        Asserts.notNull(studentAnswerInfoVO, ResponseErrorEnum.STUDENT_ANSWER_NOT_NULL);
        StudentAnswer studentAnswer = studentAnswerService.getStudentAnswerById(studentAnswerInfoVO.getId());
        if (studentAnswer != null) {
            studentAnswerService.updateEarnedScore(studentAnswer, studentAnswerInfoVO.getEarnedScore());
            return ResponseResult.newSucceedInstance("Successfully update score", null);
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.STUDENT_ANSWER_NOT_FOUND, null);
    }
}
