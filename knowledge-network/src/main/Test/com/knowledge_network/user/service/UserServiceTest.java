package com.knowledge_network.user.service;

import com.knowledge_network.support.base.BaseServiceTest;
import com.knowledge_network.user.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by pentonbin on 17-12-6
 * <p>
 * 用户模块相关的服务接口UserService的测试类
 */
public class UserServiceTest extends BaseServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void getUserById() {
        int id = 3;
        User user = userService.getUserById(id);
        if (user != null) {
            print("Found user, userId=" + id + ", userName=" + user.getUsername());
        } else {
            print("Cannot find user: userId=" + id);
        }

        id = 1;
        user = userService.getUserById(id);
        if (user != null) {
            print("Found user, userId=" + id + ", userName=" + user.getUsername());
        } else {
            print("Cannot find user: userId=" + id);
        }
    }
}
