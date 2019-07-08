package com.knowledge_network.knowledge_network.controller;

import com.knowledge_network.knowledge_network.dao.KnowledgePointDiffcultDAO;
import com.knowledge_network.knowledge_network.dao.KnowledgePointImportanceDAO;
import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.knowledge_network.entity.KnowledgePointRelationship;
import com.knowledge_network.knowledge_network.entity.Subject;
import com.knowledge_network.knowledge_network.service.KnowledgePointRelationshipService;
import com.knowledge_network.knowledge_network.service.KnowledgePointService;
import com.knowledge_network.knowledge_network.service.SubjectService;
import com.knowledge_network.knowledge_network.service.inner.KnowledgePointDifficultService;
import com.knowledge_network.knowledge_network.service.inner.KnowledgePointImportanceService;
import com.knowledge_network.knowledge_network.vo.KnowledgePointIdVO;
import com.knowledge_network.knowledge_network.vo.KnowledgePointInfoVO;
import com.knowledge_network.knowledge_network.vo.web.AddKnowledgePointInfoVO;
import com.knowledge_network.knowledge_network.vo.web.AddKnowledgePointRelationshipVO;
import com.knowledge_network.knowledge_network.vo.web.SearchKnowledgePointVO;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.support.exceptions.KnowledgePointNotFoundException;
import com.knowledge_network.user.vo.ListVO;
import com.knowledge_network.user.vo.web.ConditionVO;
import com.knowledge_network.user.vo.web.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Created by wjk on 17-12-20
 * <p>
 * 知识点相关的Controller层功能
 */

@RestController
@RequestMapping("/knowledgePoint")
public class KnowledgePointController {
    @Autowired
    private KnowledgePointService knowledgePointService;
    @Autowired
    private KnowledgePointRelationshipService knowledgePointRelationshipService;
    @Autowired
    private KnowledgePointDiffcultDAO knowledgePointDiffcultDAO;
    @Autowired
    private KnowledgePointImportanceDAO knowledgePointImportanceDAO;
    @Autowired
    private SubjectService subjectService;

    /**
     * 加载知识点初始信息（分页请求）
     *
     * @param pageVO
     * @return
     */
    @RequestMapping(value = "/knowledgePointInfo", method = RequestMethod.POST)
    public ResponseResult<ListVO<KnowledgePointInfoVO>> getKnowledgePoint(@RequestBody PageVO pageVO) {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        conditions.add(new BaseHibernateDAO.Condition(BaseHibernateDAO.ConditionType.EQ, "enable", new Integer(1)));
        conditions.add(new BaseHibernateDAO.Condition(BaseHibernateDAO.ConditionType.EQ, "deleted", new Integer(0)));
        if (pageVO.getConditions() != null && pageVO.getConditions().size() > 0) {
            for (ConditionVO conditionVO : pageVO.getConditions()) {
                if ((conditionVO.getProperty() != null) && (conditionVO.getProperty().equalsIgnoreCase("subject")))
                    conditions.add(new BaseHibernateDAO.Condition(BaseHibernateDAO.ConditionType.EQ, "subject", subjectService.getSubjectById(Integer.parseInt((String) conditionVO.getValue()))));
                if (conditionVO.getProperty().equalsIgnoreCase("grade"))
                    conditions.add(new BaseHibernateDAO.Condition(BaseHibernateDAO.ConditionType.EQ, "grade", Integer.parseInt((String) conditionVO.getValue())));
                if (conditionVO.getProperty().equalsIgnoreCase("difficult"))
                    conditions.add(new BaseHibernateDAO.Condition(BaseHibernateDAO.ConditionType.EQ, "knowledgePointDifficult", knowledgePointDiffcultDAO.findKnowledgePointDifficultById(Integer.parseInt((String) conditionVO.getValue()))));
                if (conditionVO.getProperty().equalsIgnoreCase("importance"))
                    conditions.add(new BaseHibernateDAO.Condition(BaseHibernateDAO.ConditionType.EQ, "knowledgePointImportance", knowledgePointImportanceDAO.findKnowledgePointImportanceById(Integer.parseInt((String) conditionVO.getValue()))));
            }
        }
        List<KnowledgePoint> knowledgePoints = knowledgePointService.getKnowledgePointPageByCondition(pageVO.getStart(), pageVO.getRows(), conditions);
        long total = knowledgePointService.getKnowledgePointCountByCondition(conditions);
        List<KnowledgePointInfoVO> knowledgePointInfoVOList = new ArrayList<>();
        for (KnowledgePoint knowledgePoint : knowledgePoints) {
            knowledgePointInfoVOList.add(new KnowledgePointInfoVO(knowledgePoint));
        }
        return ResponseResult.newSucceedInstance(null, new ListVO<KnowledgePointInfoVO>(total, pageVO.getStart(),
                knowledgePointInfoVOList.size(), knowledgePointInfoVOList));
    }

    /**
     * 查看某个知识点信息
     *
     * @param knowledgePointId 要查看的知识点ID
     * @return
     * @throws KnowledgePointNotFoundException
     */

    @RequestMapping(value = "/{knowledgePointId}", method = RequestMethod.GET)
    public ResponseResult<KnowledgePointInfoVO> userGetKnowledgePointInfo(@PathVariable String knowledgePointId) {
        Integer id = Integer.parseInt(knowledgePointId);
        KnowledgePoint knowledgePoint = knowledgePointService.getKnowledgePointByID(id);
        if (knowledgePoint == null || knowledgePoint.getDeleted() == 1) {
            return ResponseResult.newErrorInstance(ResponseErrorEnum.KNOWLEDGEPOINT_NOT_FOUND, null);
        } else {
            KnowledgePointInfoVO knowledgePointInfoVO = new KnowledgePointInfoVO(knowledgePoint);
            return ResponseResult.newSucceedInstance(null, knowledgePointInfoVO);
        }

    }

    //@AuthenticationPermission(permissions = "KNOWLEDGE_POINT_CREATE", checkCurrentLoginUser = true)
    @RequestMapping(value = "/addOrEdit", method = RequestMethod.POST)
    public ResponseResult<String> addKnowledgePoint(@RequestBody AddKnowledgePointInfoVO addKnowledgePointInfoVO) {
        int id = addKnowledgePointInfoVO.getId();
        if (id < 0) {
            String name = addKnowledgePointInfoVO.getName();
            if (knowledgePointService.getKnowledgePointByName(name) != null) {
                return ResponseResult.newErrorInstance(ResponseErrorEnum.KNOWLEDGEPOINT_EXIST, null);
            }
            KnowledgePoint knowledgePoint = new KnowledgePoint();
            knowledgePointService.setKnowledgePointByAddKnowledgePointInfoVO(knowledgePoint, addKnowledgePointInfoVO);
            int knowledgePointId = knowledgePoint.getId();
            knowledgePointRelationshipService.addKnowledgePointRelationshipByAddKnowledgePointRelationshipVO(knowledgePointId, addKnowledgePointInfoVO);
            return ResponseResult.newSucceedInstance("Add KnowledgePoint SUCCESS", null);
        } else {
            KnowledgePoint knowledgePoint = knowledgePointService.getKnowledgePointByID(addKnowledgePointInfoVO.getId());
            if (knowledgePoint == null)
                return ResponseResult.newErrorInstance(ResponseErrorEnum.KNOWLEDGEPOINT_NOT_FOUND, null);
            knowledgePointService.setKnowledgePointByAddKnowledgePointInfoVO(knowledgePoint, addKnowledgePointInfoVO);
            knowledgePointRelationshipService.addKnowledgePointRelationshipByAddKnowledgePointRelationshipVO(id, addKnowledgePointInfoVO);
            knowledgePointRelationshipService.deleteKnowledgePointRelationshipByDeleteKnowledgePointRelationshipVOS(id, addKnowledgePointInfoVO.getDeleteKnowledgePointRelationshipVOS());
            return ResponseResult.newSucceedInstance("Edit KnowledgePoint SUCCESS", null);
        }
    }

    /**
     * 更新知识点内容
     *
     * @param knowledgePointId
     * @param knowledgePointInfoVO
     * @return
     * @throws KnowledgePointNotFoundException
     */
    @RequestMapping(value = "/edit/{knowledgePointId}", method = RequestMethod.POST)
    public ResponseResult<String> editKnowledgePoint(@PathVariable String knowledgePointId,
                                                     @RequestBody KnowledgePointInfoVO knowledgePointInfoVO)
            throws KnowledgePointNotFoundException {
        Integer kpId = Integer.parseInt(knowledgePointId);
        KnowledgePoint knowledgePoint = knowledgePointService.getKnowledgePointByID(kpId);
        if (knowledgePoint == null) {
            throw new KnowledgePointNotFoundException(kpId);
        } else {
            String name = knowledgePointInfoVO.getName();
            KnowledgePoint knowledgePoint1 = knowledgePointService.getKnowledgePointByName(name);
            if (knowledgePoint1 != null)
                return ResponseResult.newErrorInstance(ResponseErrorEnum.KNOWLEDGEPOINT_EXIST, null);
            else {
                if (knowledgePointInfoVO.getName() != null)
                    knowledgePoint.setName(knowledgePointInfoVO.getName());
                if (knowledgePointInfoVO.getStatement() != null)
                    knowledgePoint.setStatement(knowledgePointInfoVO.getStatement());
                if (knowledgePointInfoVO.getGrade() != null)
                    knowledgePoint.setGrade(knowledgePointInfoVO.getGrade());
                if (knowledgePointInfoVO.getContent() != null)
                    knowledgePoint.setContent(knowledgePointInfoVO.getContent());
                knowledgePointService.saveKnowledgePoint(knowledgePoint);
                return ResponseResult.newSucceedInstance("Edit KnowledgePoint", null);
            }
        }
    }


    /**
     * 删除知识点
     *
     * @param knowledgePointIdVO 要删除的知识点ID列表
     * @return
     */

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseResult<String> deleteKnowledgePoints(@RequestBody KnowledgePointIdVO knowledgePointIdVO) {
        List<Integer> ids = (List<Integer>) knowledgePointIdVO.getId();
        for (Integer id : ids) {
            KnowledgePoint knowledgePoint = knowledgePointService.getKnowledgePointByID(id);
            if (knowledgePoint == null || knowledgePoint.getDeleted() == 1) {
                return ResponseResult.newErrorInstance(ResponseErrorEnum.KNOWLEDGEPOINT_NOT_FOUND, "KnowledgePintID=" + String.valueOf(id));
            } else {
                knowledgePointService.deleteKnowledgePoint(knowledgePoint);
            }
        }
        return ResponseResult.newSucceedInstance("Delete KnowledgePoint Successfully", null);
    }

    /**
     * 搜索名称中包含某个字段的知识点
     *
     * @param knowledgePointName
     * @return
     * @throws KnowledgePointNotFoundException
     */
    @RequestMapping(value = "search/{knowledgePointName}", method = RequestMethod.GET)
    public ResponseResult<List<SearchKnowledgePointVO>> searchKnowledgePointsByname(@PathVariable String knowledgePointName) {
        List<KnowledgePoint> knowledgePoints = new ArrayList<>();
        List<SearchKnowledgePointVO> searchKnowledgePointVOS = new ArrayList<>();
        knowledgePoints = knowledgePointService.getKnowledgePointsByName(knowledgePointName);
        for (KnowledgePoint knowledgePoint : knowledgePoints) {
            searchKnowledgePointVOS.add(new SearchKnowledgePointVO(knowledgePoint));
        }
        return ResponseResult.newSucceedInstance("null", searchKnowledgePointVOS);
    }


    @RequestMapping(value = "searchDetail/{tagNameOrKnowledgePintName}", method = RequestMethod.GET)
    public ResponseResult<List<KnowledgePointInfoVO>> searchKnowledgePointsBynameOrTage(@PathVariable String tagNameOrKnowledgePintName) {
        List<KnowledgePointInfoVO> knowledgePointInfoVOS = new ArrayList<>();
        List<KnowledgePoint> knowledgePoints = knowledgePointService.getKnowledgePointsByTagOrName(tagNameOrKnowledgePintName);
        for (KnowledgePoint knowledgePoint : knowledgePoints) {
            knowledgePointInfoVOS.add(new KnowledgePointInfoVO(knowledgePoint));
        }

        return ResponseResult.newSucceedInstance("null", knowledgePointInfoVOS);
    }

}
