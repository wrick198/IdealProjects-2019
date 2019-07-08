package com.knowledge_network.quize.controller;

import com.knowledge_network.quize.entity.HasDoneQuize;
import com.knowledge_network.quize.service.HasDoneQuizeService;
import com.knowledge_network.quize.vo.HasDoneQuizeInfoVO;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.support.utils.Asserts;
import com.knowledge_network.user.entity.Student;
import com.knowledge_network.user.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/5/2
 * 做题记录控制器
 **/
@RestController
@RequestMapping("/hasDoneQuize")
public class HasDoneQuizeController {
    @Autowired
    HasDoneQuizeService hasDoneQuizeService;
    @Autowired
    StudentService studentService;

    /**
     * 查看某学生已做的试题
     *
     * @param studentId
     * @return
     */
    @RequestMapping(value = "/{studentId}", method = RequestMethod.GET)
    ResponseResult<List<HasDoneQuizeInfoVO>> studentAllDoneQuize(@PathVariable String studentId) {
        Asserts.notNull(studentId, ResponseErrorEnum.USER_ID_NOT_NULL);
        Student student = studentService.getStudentById(Integer.parseInt(studentId));
        if (student != null) {
            List<HasDoneQuize> hasDoneQuizes = hasDoneQuizeService.getHasDoneQuizeByStudent(student);
            List<HasDoneQuizeInfoVO> hasDoneQuizeInfoVOS = new ArrayList<>();
            if (hasDoneQuizes != null) {
                for (HasDoneQuize r : hasDoneQuizes)
                    hasDoneQuizeInfoVOS.add(new HasDoneQuizeInfoVO(r));
            }
            return ResponseResult.newSucceedInstance(null, hasDoneQuizeInfoVOS);
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.USER_NOT_FOUND, null);
    }
}
