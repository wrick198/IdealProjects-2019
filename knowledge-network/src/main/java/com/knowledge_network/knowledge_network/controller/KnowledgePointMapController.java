package com.knowledge_network.knowledge_network.controller;

import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.knowledge_network.entity.Subject;
import com.knowledge_network.knowledge_network.service.CourseService;
import com.knowledge_network.knowledge_network.service.KnowledgePointRelationshipService;
import com.knowledge_network.knowledge_network.service.KnowledgePointService;
import com.knowledge_network.knowledge_network.service.SubjectService;
import com.knowledge_network.knowledge_network.vo.KnowledgePointMapInfoVO;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.support.exceptions.CourseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wjk
 * Date: 17-12-28
 * Time: 下午3:51
 */
@RestController
@RequestMapping("/knowledgePointMap")
public class KnowledgePointMapController {
    @Autowired
    KnowledgePointService knowledgePointService;
    @Autowired
    KnowledgePointRelationshipService knowledgePointRelationshipService;
    @Autowired
    CourseService courseService;
    @Autowired
    SubjectService subjectService;

    /**
     * 获取某个学科的知识地图
     *
     * @param subjectId
     * @return
     * @throws CourseNotFoundException
     */
    @RequestMapping(value = "/{subjectId}", method = RequestMethod.GET)
    public ResponseResult<Object> getKnowledgePointsBySubjectId(@PathVariable String subjectId) {
        int id = Integer.parseInt(subjectId);
        Subject subject = subjectService.getSubjectById(id);
        if (subject == null) {
            return ResponseResult.newErrorInstance(ResponseErrorEnum.SUBJECT_NOT_FOUND, "Subject Not Found,ID=" + String.valueOf(id));
        } else {
            List<KnowledgePoint> knowledgePoints = knowledgePointService.getKnowlegePointBySubject(subject);
            List<KnowledgePoint> enableKnowledgePoint = new ArrayList<>();
            for (KnowledgePoint knowledgePoint : knowledgePoints) {
                if (knowledgePoint.getDeleted() == 0 && knowledgePoint.getEnable() == 1)
                    enableKnowledgePoint.add(knowledgePoint);
            }
            KnowledgePointMapInfoVO knowledgePointMapInfoVO = new KnowledgePointMapInfoVO(enableKnowledgePoint);
            return ResponseResult.newSucceedInstance(null, knowledgePointMapInfoVO);
        }
    }
}
