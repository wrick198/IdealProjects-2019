package com.knowledge_network.circle.controller;

import com.knowledge_network.circle.entity.Collect;
import com.knowledge_network.circle.entity.Label;
import com.knowledge_network.circle.entity.Topic;
import com.knowledge_network.circle.service.LabelService;
import com.knowledge_network.circle.service.TopicService;
import com.knowledge_network.circle.vo.LabelVO;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wshish000 on 18-3-8
 */
@RestController
@RequestMapping(value = "/circle/label/")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

    @RequestMapping(value = "/{userId}/list", method = RequestMethod.PUT)
    public ResponseResult<ListVO<LabelVO>> listLabel(@PathVariable String userId, @RequestBody PageVO pageVO){
        User loginUser = userService.getUserById(Integer.parseInt(userId));
        if (loginUser == null || loginUser.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }

        List<Label> labels = labelService.findAll(pageVO.getStart(), pageVO.getRows());
        long totalCount = labels.size();
        List<LabelVO> labelVOS = new ArrayList<>();

        if(labels != null){
            for(Label label: labels){
                labelVOS.add(new LabelVO(label));
            }
        }

        return ResponseResult.newSucceedInstance(null, new ListVO<LabelVO>(totalCount, pageVO.getStart(), labelVOS.size(), labelVOS));

    }

    @RequestMapping(value = "/{userId}/search", method = RequestMethod.PUT)
    public ResponseResult<ListVO<LabelVO>> searchLabel(@PathVariable String userId, HttpServletRequest request){
        User loginUser = userService.getUserById(Integer.parseInt(userId));
        if (loginUser == null || loginUser.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }

        String json = IOUtils.readDataFromHttpServletRequest(request);

        Asserts.hasText(json, ResponseErrorEnum.BAD_REQUEST);

        Map<String, Object> datas = JsonMapper.json2Map(json);

        Asserts.hasText((String) datas.get("searchString"), ResponseErrorEnum.SEARCH_STRING_NOT_NULL);

        List<Label> labels = labelService.findByNameLike((String)datas.get("searchString"));
        long totalCount = labels.size();
        List<LabelVO> labelVOS = new ArrayList<>();

        if(labels != null){
            for(Label label: labels){
                labelVOS.add(new LabelVO(label));
            }
        }

        return ResponseResult.newSucceedInstance(null, new ListVO<LabelVO>(totalCount, 0, labelVOS.size(), labelVOS));

    }

    @RequestMapping(value = "/{userId}/topic", method = RequestMethod.PUT)
    public ResponseResult<ListVO<TopicVO>> listTopic(@PathVariable String userId, @RequestBody PageVO pageVO){
        User loginUser = userService.getUserById(Integer.parseInt(userId));
        if (loginUser == null || loginUser.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }

        Asserts.notNull(pageVO, ResponseErrorEnum.BAD_REQUEST);


        //TODO 日后需要完善
        String label = (String)pageVO.getConditions().get(0).getValue();

        List<Topic> topics = topicService.fuzzyTopicByLabel(labelService.findByName(label).getId());

        long totalCount = topics.size();

        List<TopicVO> topicVOS = new ArrayList<>();

        if(topics != null){
            for(Topic topic: topics){
                topicVOS.add(new TopicVO(topic));
            }
        }

        return ResponseResult.newSucceedInstance(null, new ListVO<TopicVO>(totalCount, pageVO.getStart(), topicVOS.size(), topicVOS));
    }

    @RequestMapping(value = "/{userId}/delete/{labelId}", method = RequestMethod.GET)
    public ResponseResult<String> deleteCollect(@PathVariable String userId, @PathVariable String labelId){
        User loginUser = userService.getUserById(Integer.parseInt(userId));
        if (loginUser == null || loginUser.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }

        Label label = labelService.findById(Integer.parseInt(labelId));

        if(label.getTopicCount() > 0){
            return ResponseResult.newErrorInstance(ResponseErrorEnum.RELATE_EXISTS, null);
        }

        labelService.delete(label);
        ResponseResult<String> response = ResponseResult.newSucceedInstance("Delete Label Successfully", null);
        return response;
    }

    @RequestMapping(value = "/{userId}/update/{id}", method = RequestMethod.PUT)
    public ResponseResult<String> updateLabel(@PathVariable String userId, @PathVariable String id, HttpServletRequest request){
        User loginUser = userService.getUserById(Integer.parseInt(userId));
        if (loginUser == null || loginUser.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }
        Label label = labelService.findById(Integer.parseInt(id));

        String json = IOUtils.readDataFromHttpServletRequest(request);

        Asserts.hasText(json, ResponseErrorEnum.BAD_REQUEST);

        Map<String, Object> datas = JsonMapper.json2Map(json);

        if(datas.get("name") != null){
            if(!((String)datas.get("name")).equals(label.getName())){
                Label _label = labelService.findByName((String)datas.get("name"));
                if(_label != null){
                    return ResponseResult.newErrorInstance(ResponseErrorEnum.LABEL_EXISTS, null);
                }
            }
        }

        List<Topic> topics = topicService.fuzzyTopicByLabel(Integer.parseInt(id));

        label.setName((String)datas.get("name"));
        label.setIntro((String)datas.get("intro"));
        label.setModifyTime(new Date());
        label.setTopicCount(topics.size());
        //TODO 添加标签的图片
        labelService.save(label);

        return ResponseResult.newSucceedInstance("Update Label Successfully", null);
    }
}
