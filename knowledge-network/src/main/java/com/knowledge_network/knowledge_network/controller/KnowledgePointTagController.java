package com.knowledge_network.knowledge_network.controller;

import com.knowledge_network.auth.security.anno.AuthorizationPermission;
import com.knowledge_network.knowledge_network.service.KnowledgePointTagService;
import com.knowledge_network.knowledge_network.vo.TagInfoVO;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.support.utils.IOUtils;
import com.knowledge_network.support.utils.JsonMapper;
import com.knowledge_network.user.vo.ListVO;
import com.knowledge_network.user.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * # Created by HeartUnderBlade on 2017/12/10
 */
@Controller
@RequestMapping("/tag")
public class KnowledgePointTagController {
    @Autowired
    private KnowledgePointTagService knowledgePointTagService;

    /**
     * 知识点标签添加接口
     *
     * @param request 请求报文
     * @return 标签数量或报错信息
     */
    @AuthorizationPermission(permissions = {"TAG_CREATE"}, checkCurrentLoginUser = true)
    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public ResponseResult<String> addTags(HttpServletRequest request) {
        String json = IOUtils.readDataFromHttpServletRequest(request);
        Map<String, Object> jsonMap = JsonMapper.json2Map(json);
        String name = (String) jsonMap.get("name");

        TagInfoVO info = new TagInfoVO();
        info.setName(name);

        knowledgePointTagService.createTag(info);

        return ResponseResult.newSucceedInstance("Created Succeed", null);

    }

    /**
     * 知识点标签获取接口
     *
     * @param kp    要获取的知识点
     * @param request 请求报文
     * @return 标签数量或报错信息
     */
    @AuthorizationPermission(permissions = {"TAG_RETRIEVE"}, checkCurrentLoginUser = true)
    @RequestMapping(value = "/{knowledgepointid}", method = RequestMethod.GET)
    public ResponseResult<ListVO<? extends UserInfoVO>> readTags(@PathVariable int kp, HttpServletRequest request) {
        return null;
    }

    /**
     * 知识点标签编辑接口
     *
     * @param kp    要编辑的知识点
     * @param request 请求报文
     * @return 标签数量或报错信息
     */
    @AuthorizationPermission(permissions = {"TAG_UPDATE"}, checkCurrentLoginUser = true)
    @RequestMapping(value = "/{knowledgepointid}", method = RequestMethod.POST)
    public ResponseResult<String> editTags(@PathVariable int kp, HttpServletRequest request) {
        return null;
    }

    /**
     * 知识点标签删除接口
     *
     * @param kp    要编辑的知识点
     * @param request 请求报文
     * @return 标签数量或报错信息
     */
    @AuthorizationPermission(permissions = {"TAG_DELETE"}, checkCurrentLoginUser = true)
    @RequestMapping(value = "/{knowledgepointid}", method = RequestMethod.DELETE)
    public ResponseResult<String> deleteTags(@PathVariable int kp, HttpServletRequest request) {
        return null;
    }
}
