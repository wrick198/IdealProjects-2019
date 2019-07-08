package com.knowledge_network.circle.service;

import com.knowledge_network.circle.entity.Reply;
import com.knowledge_network.circle.entity.Topic;
import com.knowledge_network.user.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by wshish000 on 18-3-4
 */
public interface ReplyService {

    Reply findById(int id);

    void save(Reply reply);

    Map delete(int id);

    void deleteByUser(User user);

    void deleteByTopic(Topic topic);

    Reply up(int userId, Reply reply);

    Reply cancelUp(int userId, int replyId);

    Reply down(int userId, Reply reply);

    Reply cancelDown(int userId, int replyId);

    List<Reply> findByTopic(Topic topic);

    List<Reply> page(int p, int size);

    List<Reply> findByUser(int p, int size, User user);
}
