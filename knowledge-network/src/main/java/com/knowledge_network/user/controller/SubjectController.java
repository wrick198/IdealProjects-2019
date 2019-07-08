package com.knowledge_network.user.controller;

import com.knowledge_network.auth.security.anno.AuthorizationPermission;
import com.knowledge_network.knowledge_network.entity.Subject;
import com.knowledge_network.knowledge_network.service.SubjectService;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.user.vo.SubjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pentonbin on 18-1-22
 */
@RestController
@RequestMapping(value = "/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    /**
     * 获取当前系统上所有科目的接口
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @AuthorizationPermission(permissions = {"SUBJECT_RETRIEVE"}, checkCurrentLoginUser = false)
    public ResponseResult<List<SubjectVO>> list() {
        List<Subject> subjects = subjectService.getAllSubjects();
        List<SubjectVO> subjectVOs = new ArrayList<>();
        for (Subject subject : subjects) {
            subjectVOs.add(new SubjectVO(subject));
        }
        return ResponseResult.newSucceedInstance(null, subjectVOs);
    }
}
