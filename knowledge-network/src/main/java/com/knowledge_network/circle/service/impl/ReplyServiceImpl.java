package com.knowledge_network.circle.service.impl;

import com.knowledge_network.circle.dao.ReplyDAO;
import com.knowledge_network.circle.entity.Reply;
import com.knowledge_network.circle.entity.Topic;
import com.knowledge_network.circle.service.ReplyService;
import com.knowledge_network.circle.service.TopicService;
import com.knowledge_network.user.entity.User;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wshish000 on 18-3-5
 */
@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyDAO replyDAO;

    @Autowired
    private TopicService topicService;

    @Override
    public Reply findById(int id) {
        return replyDAO.findReplyById(id);
    }

    @Override
    public void save(Reply reply) {
        replyDAO.updateReply(reply);
    }

    @Override
    public Map delete(int id) {
        Map map = new HashMap();
        Reply reply = findById(id);
        if(reply != null){
            map.put("topicId", reply.getTopic().getId());
            topicService.reduceOneReplyCount(reply.getTopic().getId());
            replyDAO.deleteById(id);
        }
        return map;
    }

    @Override
    public void deleteByUser(User user) {
        replyDAO.deleteByUser(user);
    }

    @Override
    public void deleteByTopic(Topic topic) {
        replyDAO.deleteByTopic(topic);
    }

    @Override
    public Reply up(int userId, Reply reply) {
        String upIds = reply.getUpIds();
        if(upIds == null){
            upIds = ",";
        }
        if(!upIds.contains("," + userId + ",") && !upIds.contains(userId + ",")){
            reply.setUp(reply.getUp() + 1);
            reply.setUpIds(upIds + userId + ",");

            String downIds = reply.getDownIds();
            if(downIds == null){
                downIds = ",";
            }
            if(downIds.contains("," + userId + ",")){
                reply.setDown(reply.getDown() - 1);
                downIds = downIds.replace("," + userId + ",", ",");
                reply.setDownIds(downIds);
            }
            int count = reply.getUp() - reply.getDown();
            reply.setUpDown(count > 0 ? count : 0);
            save(reply);
        }
        return reply;
    }

    @Override
    public Reply cancelUp(int userId, int replyId) {
        Reply reply = findById(replyId);
        if(reply != null){
            String upIds = reply.getUpIds();
            if(upIds == null){
                upIds = ",";
            }
            if(upIds.contains("," + userId + ",")){
                reply.setUp(reply.getUp() - 1);
                upIds = upIds.replace("," + userId + ",", ",");
                reply.setUpIds(upIds);

                int count = reply.getUp() - reply.getDown();
                reply.setUpDown(count > 0 ? count : 0);
                save(reply);
            }
            if(upIds.contains(userId + ",")){
                reply.setUp(reply.getUp() - 1);
                upIds = upIds.replace(userId + ",", ",");
                reply.setUpIds(upIds);

                int count = reply.getUp() - reply.getDown();
                reply.setUpDown(count > 0 ? count : 0);
                save(reply);
            }
        }
        return reply;
    }

    @Override
    public Reply down(int userId, Reply reply) {
        String downIds = reply.getDownIds();
        if (downIds == null) downIds = ",";
        if (!downIds.contains("," + userId + ",") && !downIds.contains(userId + ",")) {
            reply.setDown(reply.getDown() + 1);
            reply.setDownIds(downIds + userId + ",");

            String upIds = reply.getUpIds();
            if (upIds == null) upIds = ",";
            if (upIds.contains("," + userId + ",")) {
                reply.setUp(reply.getUp() - 1);
                upIds = upIds.replace("," + userId + ",", ",");
                reply.setUpIds(upIds);
            }
            int count = reply.getUp() - reply.getDown();
            reply.setUpDown(count > 0 ? count : 0);
            save(reply);
        }
        return reply;
    }

    @Override
    public Reply cancelDown(int userId, int replyId) {
        Reply reply = findById(replyId);
        if (reply != null) {
            String downIds = reply.getDownIds();
            if (downIds == null) downIds = ",";
            if (downIds.contains("," + userId + ",")) {
                reply.setDown(reply.getDown() - 1);
                downIds = downIds.replace("," + userId + ",", ",");
                reply.setDownIds(downIds);

                int count = reply.getUp() - reply.getDown();
                reply.setUpDown(count > 0 ? count : 0);
                save(reply);
            }
            if (downIds.contains(userId + ",")) {
                reply.setDown(reply.getDown() - 1);
                downIds = downIds.replace(userId + ",", ",");
                reply.setDownIds(downIds);

                int count = reply.getUp() - reply.getDown();
                reply.setUpDown(count > 0 ? count : 0);
                save(reply);
            }
        }
        return reply;
    }

    @Override
    public List<Reply> findByTopic(Topic topic) {
        return replyDAO.findByTopicOrderByUpDownDescDownAscInTimeAsc(topic);
    }

    @Override
    public List<Reply> page(int p, int size) {
        Order order = Order.desc("inTime");
        return replyDAO.findAll(p, size, order);
    }

    @Override
    public List<Reply> findByUser(int p, int size, User user) {
        Order order = Order.desc("inTime");
        return replyDAO.findByUser(user, p, size, order);
    }
}
