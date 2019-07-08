package com.knowledge_network.course;

import com.knowledge_network.knowledge_network.controller.CourseController;
import com.knowledge_network.knowledge_network.entity.Course;
import com.knowledge_network.knowledge_network.service.CourseService;
import com.knowledge_network.knowledge_network.vo.CourseInfoVO;
import com.knowledge_network.support.base.BaseServiceTest;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.user.entity.User;
import com.knowledge_network.user.vo.ListVO;
import com.knowledge_network.user.vo.web.ConditionVO;
import com.knowledge_network.user.vo.web.PageVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class courseTest extends BaseServiceTest {

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseController courseController;

    @Test
    public void getCourseList() {

    }


}
