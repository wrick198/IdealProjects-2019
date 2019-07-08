package com.knowledge_network.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by pentonbin on 18-3-20
 */
@Controller
@RequestMapping(value = "")
public class LoginController {

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }
}
