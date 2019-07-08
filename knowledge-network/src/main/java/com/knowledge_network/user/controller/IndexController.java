package com.knowledge_network.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by pentonbin on 17-12-7
 */
@Controller
@RequestMapping(name = "/")
public class IndexController {

    @RequestMapping()
    public String index() {
        return "index";
    }
}
