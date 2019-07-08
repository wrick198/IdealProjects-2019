package com.knowledge_network.circle.dao;

import com.knowledge_network.circle.entity.Reply;
import com.knowledge_network.circle.entity.Topic;
import com.knowledge_network.user.entity.User;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by wshish000 on 18-3-4
 */
public interface ReplyDAO {

    //通过id查找回复
    Reply findReplyById(Integer id);

    //保存回复
    void updateReply(Reply reply);

    void deleteReply(Reply reply);

    //将文章下的回复按下列顺序列出
    List<Reply> findByTopicOrderByUpDownDescDownAscInTimeAsc(Topic topic);

    //删除文章下的所有回复
    void deleteByTopic(Topic topic);

    //删除某用户的回复
    void deleteByUser(User user);

    //查找某用户的回复
    List<Reply> findByUser(User user, int start, int row, Order order);

    //查询所有
    List<Reply> findAll(int start, int row, Order order);

    //删除某条回复
    void deleteById(int id);
}
