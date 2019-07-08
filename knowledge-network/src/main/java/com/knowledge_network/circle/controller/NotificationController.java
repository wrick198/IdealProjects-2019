package com.knowledge_network.circle.controller;

import com.knowledge_network.circle.entity.Notification;
import com.knowledge_network.circle.service.NotificationService;
import com.knowledge_network.circle.vo.NotificationVO;
import com.knowledge_network.circle.vo.web.PageVO;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.support.exceptions.UserNotFoundException;
import com.knowledge_network.support.utils.Asserts;
import com.knowledge_network.user.entity.User;
import com.knowledge_network.user.service.UserService;
import com.knowledge_network.user.vo.ListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wshish000 on 18-3-8
 */
@RestController
@RequestMapping(value = "/circle/notification/")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{userId}/list", method = RequestMethod.PUT)
    public ResponseResult<ListVO<NotificationVO>> listNotification(@PathVariable String userId, @RequestBody PageVO pageVO){

        Asserts.notNull(pageVO, ResponseErrorEnum.BAD_REQUEST);
        Asserts.notNull(pageVO.getStart(), ResponseErrorEnum.OTHER_START_NOT_NULL);
        Asserts.notNull(pageVO.getRows(), ResponseErrorEnum.OTHER_ROWS_NOT_NULL);

        User loginUser = userService.getUserById(Integer.parseInt(userId));
        if (loginUser == null || loginUser.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }

        List<Notification> notifications = notificationService.findByTargetUserAndIsRead(pageVO.getStart(), pageVO.getRows(), loginUser,Boolean.parseBoolean((String)pageVO.getConditions().get(0).getValue()));

        long totalCount = notificationService.countByTargetUserAndIsRead(loginUser, Boolean.parseBoolean((String)pageVO.getConditions().get(0).getValue()));

        //long totalCount = notifications.size();

        List<NotificationVO> notificationVOS = new ArrayList<>();

        if(notifications != null){
            for(Notification notification: notifications){
                notificationVOS.add(new NotificationVO(notification));
            }
        }

        return ResponseResult.newSucceedInstance(null, new ListVO<NotificationVO>(totalCount, pageVO.getStart(), notificationVOS.size(), notificationVOS));
    }

    @RequestMapping(value = "/{userId}/notRead", method = RequestMethod.GET)
    public ResponseResult<Long> notReadNotification(@PathVariable String userId){

        User loginUser = userService.getUserById(Integer.parseInt(userId));
        if (loginUser == null || loginUser.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }

        long totalCount = notificationService.countByTargetUserAndIsRead(loginUser, false);

        ResponseResult<Long> response = ResponseResult.newSucceedInstance("Count Successfully", totalCount);
        return response;
    }
}
