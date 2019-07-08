package com.knowledge_network.knowledge_network.controller;

import com.knowledge_network.auth.security.anno.AuthorizationPermission;
import com.knowledge_network.knowledge_network.service.KnowledgePointRelationshipService;
import com.knowledge_network.knowledge_network.vo.KnowledgePointRelationshipVO;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.support.utils.IOUtils;
import com.knowledge_network.support.utils.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * # Created by HeartUnderBlade on 2017/12/10
 */
@Controller
@RequestMapping("/knowledgepointrelationship")
class KnowledgePointRelationshipController {
    @Autowired
    private KnowledgePointRelationshipService knowledgePointRelationshipService;

    /**
     * 知识网络地图关系访问接口
     *
     * @param request 请求报文
     * @return 关系数量或报错信息
     */
    @AuthorizationPermission(permissions = {"KNOWLEDGE_POINT_RETRIEVE"}, checkCurrentLoginUser = true)
    @RequestMapping(value = "/get/{knowledgepointid}", method = RequestMethod.GET)
    public ResponseResult<String> getRelationship(HttpServletRequest request) {

        return ResponseResult.newSucceedInstance("Created Succeed", null);
    }

    /**
     * 知识网络地图关系创建接口
     *
     * @param kp1     当前知识点id
     * @param request 请求报文
     * @return 关系数量或报错信息
     */
    @AuthorizationPermission(permissions = {"KNOWLEDGE_POINT_CREATE"}, checkCurrentLoginUser = true)
    @RequestMapping(value = "/add/{knowledgepointid}", method = RequestMethod.POST)
    public ResponseResult<String> addRelationship(@PathVariable int kp1, HttpServletRequest request) {
        String json = IOUtils.readDataFromHttpServletRequest(request);
        Map<String, Object> jsonMap = JsonMapper.json2Map(json);
        Integer kp2 = (Integer) jsonMap.get("knowledgepointid");
        Integer type = (Integer) jsonMap.get("relationshiptype");

        KnowledgePointRelationshipVO info = new KnowledgePointRelationshipVO();
        switch (type) {
            case 0:
                info.setKnowledgePointId1(kp2);
                info.setKnowledgePointId2(kp1);
                info.setKnowledgePointRelationshipType(0);
                break;
            case 1:
                info.setKnowledgePointId1(kp1);
                info.setKnowledgePointId2(kp2);
                info.setKnowledgePointRelationshipType(0);
                break;
            case 2:
                info.setKnowledgePointId1(kp2);
                info.setKnowledgePointId2(kp1);
                info.setKnowledgePointRelationshipType(1);
                break;
            case 3:
                info.setKnowledgePointId1(kp1);
                info.setKnowledgePointId2(kp2);
                info.setKnowledgePointRelationshipType(1);
                break;
            default:
                return ResponseResult.newErrorInstance(ResponseErrorEnum.BAD_REQUEST, null);
        }

        knowledgePointRelationshipService.createKnowledgePointRelationship(info);

        return ResponseResult.newSucceedInstance("Created Succeed", null);
    }

    /**
     * 知识网络地图关系删除接口
     *
     * @param kp1     当前知识点
     * @param request 请求报文
     * @return 关系数量或报错信息
     */
    @AuthorizationPermission(permissions = {"KNOWLEDGE_POINT_DELETE"}, checkCurrentLoginUser = true)
    @RequestMapping(value = "/delete/{knowledgepointid}", method = RequestMethod.DELETE)
    public ResponseResult<String> deleteRelationship(@PathVariable int kp1, HttpServletRequest request) {
        String json = IOUtils.readDataFromHttpServletRequest(request);
        Map<String, Object> jsonMap = JsonMapper.json2Map(json);
        int kp2 = (int) jsonMap.get("knowledgepointid");
        int type = (int) jsonMap.get("relationshiptype");

        KnowledgePointRelationshipVO info = new KnowledgePointRelationshipVO();
        switch (type) {
            case 0:
            case 2:
                knowledgePointRelationshipService.deleteKnowledgePointRelationship(kp2, kp1);
                break;
            case 1:
            case 3:
                knowledgePointRelationshipService.deleteKnowledgePointRelationship(kp1, kp2);
                break;
            default:
                return ResponseResult.newErrorInstance(ResponseErrorEnum.BAD_REQUEST, null);
        }

        return ResponseResult.newSucceedInstance("Delete RelationShip Successfully", null);
    }
}