package com.knowledge_network.user.controller;

import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.support.utils.Asserts;
import com.knowledge_network.support.utils.IOUtils;
import com.knowledge_network.support.utils.JsonMapper;
import com.knowledge_network.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by pentonbin on 17-12-28
 * 注册接口
 */
@RestController
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    /**
     * 注册接口（仅支持注册教师、家长、学生）
     * 提供username、userRole、password
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseResult<String> register(HttpServletRequest request) {
        String json = IOUtils.readDataFromHttpServletRequest(request);
        Asserts.hasText(json, ResponseErrorEnum.BAD_REQUEST);

        Map<String, Object> datas = JsonMapper.json2Map(json);

        String username = (String) datas.get("username");
        String password = (String) datas.get("password");
        String userRole = (String) datas.get("userRole");

        userService.register(userRole, username, password, datas);
        return ResponseResult.newSucceedInstance("Succeed", null);
    }

}
