package com.knowledge_network.circle.controller;

import com.knowledge_network.circle.entity.Topic;
import com.knowledge_network.circle.service.CollectService;
import com.knowledge_network.circle.service.LabelService;
import com.knowledge_network.circle.service.SectionService;
import com.knowledge_network.circle.service.TopicService;
import com.knowledge_network.circle.vo.CollectVO;
import com.knowledge_network.circle.vo.TopicVO;
import com.knowledge_network.circle.vo.web.PageVO;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.support.exceptions.UserNotFoundException;
import com.knowledge_network.support.utils.Asserts;
import com.knowledge_network.support.utils.IOUtils;
import com.knowledge_network.support.utils.JsonMapper;
import com.knowledge_network.support.utils.StringUtils;
import com.knowledge_network.user.entity.User;
import com.knowledge_network.user.service.UserService;
import com.knowledge_network.user.vo.ListVO;
import com.knowledge_network.user.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wshish000 on 18-3-5
 */
@RestController
@RequestMapping(value = "/circle/topic/")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private CollectService collectService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private UserService userService;

    @Autowired
    private LabelService labelService;

    @RequestMapping(value = "/{userId}/create", method = RequestMethod.PUT)
    public ResponseResult<String> createTopicInfo(@PathVariable String userId, HttpServletRequest request) {
        Integer id = Integer.parseInt(userId);
        User loginUser = userService.getUserById(id);
        if (loginUser == null || loginUser.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }

        String json = IOUtils.readDataFromHttpServletRequest(request);

        Asserts.hasText(json, ResponseErrorEnum.BAD_REQUEST);

        Map<String, Object> datas = JsonMapper.json2Map(json);

        Topic topic = new Topic();
        Asserts.hasText((String) datas.get("title"), ResponseErrorEnum.TITLE_NOT_NULL);
        Asserts.hasText((String) datas.get("tab"), ResponseErrorEnum.TAB_NOT_NULL);
        if(topicService.findByTitle((String)datas.get("title")) != null){
            return ResponseResult.newErrorInstance(ResponseErrorEnum.TITLE_EXISTS, null);
        }
        topic.setLabelId(labelService.dealLabels((String)datas.get("labels")));
        topic.setTab((String)datas.get("tab"));
        topic.setTitle((String)datas.get("title"));
        topic.setContent((String)datas.get("content"));
        topic.setInTime(new Date());
        topic.setUser(loginUser);
        topic.setGood(false);
        topic.setTop(false);
        topic.setLock(false);
        topicService.save(topic);

        return ResponseResult.newSucceedInstance("Create Successfully", null);
    }

    @RequestMapping(value = "/{userId}/edit/{id}", method = RequestMethod.PUT)
    public ResponseResult<String> editTopicInfo(@PathVariable String userId, @PathVariable String id, HttpServletRequest request){
        User loginUser = userService.getUserById(Integer.parseInt(userId));
        if (loginUser == null || loginUser.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }
        Topic topic = topicService.findById(Integer.parseInt(id));
        if(topic == null){
            return ResponseResult.newErrorInstance(ResponseErrorEnum.TOPIC_NOT_NULL, null);
        }
        if(topic.getUser().getId() != loginUser.getId()){
            return ResponseResult.newErrorInstance(ResponseErrorEnum.ILLEGAL_OPERATION, null);
        }
        String json = IOUtils.readDataFromHttpServletRequest(request);

        Asserts.hasText(json, ResponseErrorEnum.BAD_REQUEST);

        Map<String, Object> datas = JsonMapper.json2Map(json);

        Asserts.hasText((String) datas.get("tab"), ResponseErrorEnum.TAB_NOT_NULL);

        labelService.dealEditTopicOldLabels(topic.getLabelId());
        topic.setLabelId(labelService.dealLabels((String)datas.get("labels")));
        topic.setTab((String)datas.get("tab"));
        topic.setTitle((String)datas.get("title"));
        topic.setContent((String)datas.get("content"));
        topic.setModifyTime(new Date());
        topicService.save(topic);

        return ResponseResult.newSucceedInstance("Create Successfully", null);
    }

    @RequestMapping(value = "/{userId}/detail/{id}", method = RequestMethod.GET)
    public ResponseResult<TopicVO> topicInfoOverview(@PathVariable String userId, @PathVariable String id){
        User loginUser = userService.getUserById(Integer.parseInt(userId));
        if (loginUser == null || loginUser.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }
        Topic topic = topicService.findById(Integer.parseInt(id));
        if(topic == null){
            return ResponseResult.newErrorInstance(ResponseErrorEnum.TOPIC_NOT_NULL, null);
        }
        topic.setView(topic.getView() + 1);
        topicService.save(topic);
        TopicVO topicVO = new TopicVO(topic);
        topicVO.setCollectVO(new CollectVO(collectService.findByUserAndTopic(loginUser, topic)));
        topicVO.setCollectCount((int)collectService.countByTopic(topic));
        ResponseResult<TopicVO> response = ResponseResult.newSucceedInstance(null, topicVO);
        return response;
    }

    @RequestMapping(value = "/{userId}/delete/{id}", method = RequestMethod.GET)
    public ResponseResult<String> deleteTopic(@PathVariable String userId, @PathVariable String id){
        User loginUser = userService.getUserById(Integer.parseInt(userId));
        if (loginUser == null || loginUser.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }
        topicService.deleteById(Integer.parseInt(id));
        ResponseResult<String> response = ResponseResult.newSucceedInstance("Delete Successfully", null);
        return response;
    }

    @RequestMapping(value = "/{userId}/list", method = RequestMethod.PUT)
    public ResponseResult<ListVO<TopicVO>> listTopic(@PathVariable String userId, @RequestBody PageVO pageVO){

        Asserts.notNull(pageVO, ResponseErrorEnum.BAD_REQUEST);
        Asserts.notNull(pageVO.getStart(), ResponseErrorEnum.OTHER_START_NOT_NULL);
        Asserts.notNull(pageVO.getRows(), ResponseErrorEnum.OTHER_ROWS_NOT_NULL);

        User loginUser = userService.getUserById(Integer.parseInt(userId));
        if (loginUser == null || loginUser.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }

        long totalCount = loginUser.getTopics().size();
        List<Topic> topics = topicService.findByUser(pageVO.getStart(), pageVO.getRows(), loginUser);
        List<TopicVO> topicVOS = new ArrayList<>();

        if(topics != null){
            for(Topic topic: topics){
                topicVOS.add(new TopicVO(topic));
            }
        }

        return ResponseResult.newSucceedInstance(null, new ListVO<TopicVO>(totalCount, pageVO.getStart(), topicVOS.size(), topicVOS));
    }

    @RequestMapping(value = "/{userId}/good/{id}", method = RequestMethod.GET)
    public ResponseResult<String> goodTopic(@PathVariable String userId, @PathVariable String id){
        //TODO 此处需要验证身份

        Topic topic = topicService.findById(Integer.parseInt(id));
        topic.setGood(!topic.isGood());
        topicService.save(topic);
        ResponseResult<String> response = ResponseResult.newSucceedInstance("Good Successfully", null);
        return response;
    }

    @RequestMapping(value = "/{userId}/top/{id}", method = RequestMethod.GET)
    public ResponseResult<String> topTopic(@PathVariable String userId, @PathVariable String id){
        //TODO 此处需要验证身份

        Topic topic = topicService.findById(Integer.parseInt(id));
        topic.setTop(!topic.isTop());
        topicService.save(topic);
        ResponseResult<String> response = ResponseResult.newSucceedInstance("Top Successfully", null);
        return response;
    }

    @RequestMapping(value = "/{userId}/lock/{id}", method = RequestMethod.GET)
    public ResponseResult<String> lockTopic(@PathVariable String userId, @PathVariable String id){
        //TODO 此处需要验证身份

        Topic topic = topicService.findById(Integer.parseInt(id));
        topic.setLock(!topic.isLock());
        topicService.save(topic);
        ResponseResult<String> response = ResponseResult.newSucceedInstance("Lock Successfully", null);
        return response;
    }
}
