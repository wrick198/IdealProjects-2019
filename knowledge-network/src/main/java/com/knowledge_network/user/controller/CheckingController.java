package com.knowledge_network.user.controller;

import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.support.utils.Asserts;
import com.knowledge_network.user.service.UserService;
import com.knowledge_network.user.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pentonbin on 17-12-28
 * 检查接口（不需要登录认证）
 */
@RestController
@RequestMapping(value = "/check")
public class CheckingController {

    @Autowired
    private UserService userService;

    /**
     * 用户名检查
     *
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/username", method = RequestMethod.POST)
    public ResponseResult<String> checkUsername(@RequestBody UserInfoVO userInfo) {
        Asserts.notNull(userInfo, ResponseErrorEnum.BAD_REQUEST);

        String username = userInfo.getUsername();
        if (userService.checkUsernameExists(username)) {
            return ResponseResult.newErrorInstance(ResponseErrorEnum.USERNAME_EXISTS, null);
        }
        return ResponseResult.newSucceedInstance("Not Exists", null);
    }
}
