package com.knowledge_network.circle.controller;

import com.knowledge_network.circle.entity.Collect;
import com.knowledge_network.circle.entity.NotificationEnum;
import com.knowledge_network.circle.entity.Topic;
import com.knowledge_network.circle.service.CollectService;
import com.knowledge_network.circle.service.NotificationService;
import com.knowledge_network.circle.service.TopicService;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.support.exceptions.UserNotFoundException;
import com.knowledge_network.user.entity.User;
import com.knowledge_network.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by wshish000 on 18-3-8
 */
@RestController
@RequestMapping(value = "/circle/collect/")
public class CollectController {

    @Autowired
    private CollectService collectService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{userId}/add/{topicId}", method = RequestMethod.GET)
    public ResponseResult<String> addCollect(@PathVariable String userId, @PathVariable String topicId){
        User loginUser = userService.getUserById(Integer.parseInt(userId));
        if (loginUser == null || loginUser.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }

        Topic topic = topicService.findById(Integer.parseInt(topicId));
        if(topic == null){
            return ResponseResult.newErrorInstance(ResponseErrorEnum.TOPIC_NOT_NULL, null);
        }
        Collect collect = collectService.findByUserAndTopic(loginUser, topic);
        if(collect != null){
            return ResponseResult.newErrorInstance(ResponseErrorEnum.COLLECT_EXISTS, null);
        }

        collect = new Collect();
        collect.setInTime(new Date());
        collect.setTopic(topic);
        collect.setUser(loginUser);
        collectService.save(collect);

        notificationService.sendNotification(loginUser, topic.getUser(), NotificationEnum.COLLECT.name(), topic, "");

        ResponseResult<String> response = ResponseResult.newSucceedInstance("Add Collect Successfully", null);
        return response;
    }

    @RequestMapping(value = "/{userId}/delete/{topicId}", method = RequestMethod.GET)
    public ResponseResult<String> deleteCollect(@PathVariable String userId, @PathVariable String topicId){
        User loginUser = userService.getUserById(Integer.parseInt(userId));
        if (loginUser == null || loginUser.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }

        Topic topic = topicService.findById(Integer.parseInt(topicId));
        if(topic == null){
            return ResponseResult.newErrorInstance(ResponseErrorEnum.TOPIC_NOT_NULL, null);
        }
        Collect collect = collectService.findByUserAndTopic(loginUser, topic);
        if(collect == null){
            return ResponseResult.newErrorInstance(ResponseErrorEnum.COLLECT_NOT_NULL, null);
        }

        collectService.deleteById(collect.getId());

        ResponseResult<String> response = ResponseResult.newSucceedInstance("Delete Collect Successfully", null);
        return response;
    }
}
