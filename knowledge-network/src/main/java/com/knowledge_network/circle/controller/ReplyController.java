package com.knowledge_network.circle.controller;

import com.knowledge_network.circle.entity.NotificationEnum;
import com.knowledge_network.circle.entity.Reply;
import com.knowledge_network.circle.entity.Topic;
import com.knowledge_network.circle.service.NotificationService;
import com.knowledge_network.circle.service.ReplyService;
import com.knowledge_network.circle.service.TopicService;
import com.knowledge_network.circle.vo.ReplyVO;
import com.knowledge_network.circle.vo.TopicVO;
import com.knowledge_network.circle.vo.web.PageVO;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.support.exceptions.UserNotFoundException;
import com.knowledge_network.support.utils.Asserts;
import com.knowledge_network.support.utils.IOUtils;
import com.knowledge_network.support.utils.JsonMapper;
import com.knowledge_network.user.entity.User;
import com.knowledge_network.user.service.UserService;
import com.knowledge_network.user.vo.ListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wshish000 on 18-3-9
 */
@RestController
@RequestMapping(value = "/circle/reply/")
public class ReplyController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{userId}/create", method = RequestMethod.PUT)
    public ResponseResult<String> createReplyInfo(@PathVariable String userId, HttpServletRequest request) {
        Integer id = Integer.parseInt(userId);
        User loginUser = userService.getUserById(id);
        if (loginUser == null || loginUser.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }

        String json = IOUtils.readDataFromHttpServletRequest(request);

        Asserts.hasText(json, ResponseErrorEnum.BAD_REQUEST);

        Map<String, Object> datas = JsonMapper.json2Map(json);

        Asserts.hasText((String) datas.get("content"), ResponseErrorEnum.CONTENT_NOT_NULL);
        String content = (String)datas.get("content");

        Topic topic = topicService.findById((Integer)datas.get("topicId"));
        if(topic != null){
            Reply reply = new Reply();
            reply.setUser(loginUser);
            reply.setTopic(topic);
            reply.setInTime(new Date());
            reply.setUp(0);
            reply.setDown(0);
            reply.setUpDown(0);
            reply.setContent(content);
            replyService.save(reply);

            //回复+1
            topic.setReplyCount(topic.getReplyCount() + 1);
            topic.setLastReplyTime(new Date());
            topicService.save(topic);

            //给话题作者发送通知
            if(loginUser.getId() != topic.getUser().getId()){
                notificationService.sendNotification(loginUser, topic.getUser(), NotificationEnum.REPLY.name(), topic, content);
            }

            //给At用户发送通知
            User atUser = userService.getUserById((Integer)datas.get("atUser") == null ? 0 : (Integer)datas.get("atUser"));
            if(atUser != null){
                notificationService.sendNotification(loginUser, atUser, NotificationEnum.AT.name(), topic, content);
            }
        }

        return ResponseResult.newSucceedInstance("Create Reply Successfully", null);
    }

    @RequestMapping(value = "/{userId}/up/{id}", method = RequestMethod.GET)
    public ResponseResult<ReplyVO> upReply(@PathVariable String userId, @PathVariable String id){
        User loginUser = userService.getUserById(Integer.parseInt(userId));
        if (loginUser == null || loginUser.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }

        Reply _reply = replyService.findById(Integer.parseInt(id));
        if(_reply == null){
            return ResponseResult.newErrorInstance(ResponseErrorEnum.REPLY_NOT_NULL, null);
        }
        if(loginUser.getId() == _reply.getUser().getId()){
            return ResponseResult.newErrorInstance(ResponseErrorEnum.UP_ERROR, null);
        }
        Reply reply = replyService.up(loginUser.getId(), _reply);
        return ResponseResult.newSucceedInstance(null, new ReplyVO(reply));

    }

    @RequestMapping(value = "/{userId}/cancelUp/{id}", method = RequestMethod.GET)
    public ResponseResult<ReplyVO> cancelUpReply(@PathVariable String userId, @PathVariable String id){
        User loginUser = userService.getUserById(Integer.parseInt(userId));
        if (loginUser == null || loginUser.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }

        Reply reply = replyService.cancelUp(Integer.parseInt(userId), Integer.parseInt(id));
        if(reply == null){
            return ResponseResult.newErrorInstance(ResponseErrorEnum.REPLY_NOT_NULL, null);
        }
        return ResponseResult.newSucceedInstance(null, new ReplyVO(reply));

    }

    @RequestMapping(value = "/{userId}/down/{id}", method = RequestMethod.GET)
    public ResponseResult<ReplyVO> downReply(@PathVariable String userId, @PathVariable String id){
        User loginUser = userService.getUserById(Integer.parseInt(userId));
        if (loginUser == null || loginUser.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }

        Reply _reply = replyService.findById(Integer.parseInt(id));
        if(_reply == null){
            return ResponseResult.newErrorInstance(ResponseErrorEnum.REPLY_NOT_NULL, null);
        }
        if(loginUser.getId() == _reply.getUser().getId()){
            return ResponseResult.newErrorInstance(ResponseErrorEnum.UP_ERROR, null);
        }
        Reply reply = replyService.down(loginUser.getId(), _reply);
        return ResponseResult.newSucceedInstance(null, new ReplyVO(reply));

    }

    @RequestMapping(value = "/{userId}/cancelDown/{id}", method = RequestMethod.GET)
    public ResponseResult<ReplyVO> cancelDownReply(@PathVariable String userId, @PathVariable String id){
        User loginUser = userService.getUserById(Integer.parseInt(userId));
        if (loginUser == null || loginUser.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }

        Reply reply = replyService.cancelDown(Integer.parseInt(userId), Integer.parseInt(id));
        if(reply == null){
            return ResponseResult.newErrorInstance(ResponseErrorEnum.REPLY_NOT_NULL, null);
        }
        return ResponseResult.newSucceedInstance(null, new ReplyVO(reply));

    }

    @RequestMapping(value = "/{userId}/delete/{id}", method = RequestMethod.GET)
    public ResponseResult<String> deleteReply(@PathVariable String userId, @PathVariable String id){
        User loginUser = userService.getUserById(Integer.parseInt(userId));
        if (loginUser == null || loginUser.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }

        Reply reply = replyService.findById(Integer.parseInt(id));
        if(reply.getUser().getId() != loginUser.getId()){
            return ResponseResult.newErrorInstance(ResponseErrorEnum.REPLY_EXIST_ERROR, null);
        }
        Map map = replyService.delete(Integer.parseInt(id));
        return ResponseResult.newSucceedInstance("Delete Reply Successfully", null);
    }

    @RequestMapping(value = "/{userId}/list", method = RequestMethod.PUT)
    public ResponseResult<ListVO<ReplyVO>> listTopic(@PathVariable String userId, @RequestBody PageVO pageVO){

        Asserts.notNull(pageVO, ResponseErrorEnum.BAD_REQUEST);
        Asserts.notNull(pageVO.getStart(), ResponseErrorEnum.OTHER_START_NOT_NULL);
        Asserts.notNull(pageVO.getRows(), ResponseErrorEnum.OTHER_ROWS_NOT_NULL);

        User loginUser = userService.getUserById(Integer.parseInt(userId));
        if (loginUser == null || loginUser.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }
        //TODO 此处还没有做分页处理
        Integer i = (Integer)pageVO.getConditions().get(0).getValue();
        Topic topic = topicService.findById(i);
        List<Reply> replies = replyService.findByTopic(topic);
        long totalCount = replies.size();
        List<ReplyVO> replyVOS = new ArrayList<>();

        if(replies != null){
            for(Reply reply: replies){
                replyVOS.add(new ReplyVO(reply));
            }
        }

        return ResponseResult.newSucceedInstance(null, new ListVO<ReplyVO>(totalCount, pageVO.getStart(), replyVOS.size(), replyVOS));
    }
}
