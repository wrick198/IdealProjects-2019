package com.knowledge_network.circle.dao;

import com.knowledge_network.circle.entity.Topic;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.User;
import org.hibernate.criterion.Order;


import java.util.Date;
import java.util.List;

/**
 * Created by wshish000 on 18-3-3
 */
public interface TopicDAO {

    //保存某话题
    void updateTopic(Topic topic);

    /*
    通过id进行查找
     */
    Topic findTopicById(Integer id);

    //通过标签进行查找
    List<Topic> findByTab(String tab, int start, int row, Order order);

    //通过用户进行查找
    List<Topic> findByUser(User user, int start, int row, Order order);

    //删除某用户的话题
    void deleteByUser(User user);

    //通过good查找
    List<Topic> findByGood(boolean b, int start, int row, Order order);

    //通过回复数进行查找
    List<Topic> findByReplyCount(int i, int start, int row, Order order);

    //通过标题或内容匹配进行查找
    List<Topic> findByTitleContainingOrContentContaining(String title, String content, int start, int row, Order order);

    //获取某时间段内的话题
    long countByInTimeBetween(Date date1, Date date2);

    //通过标题精确查找
    Topic findByTitle(String title);

    //通过标签进行模糊查找
    List<Topic> findByLabelIdLike(String labelId);

    //通过标签进行分页模糊查找
    List<Topic> findByLabelIdLike(String labelId, int start, int row, Order order);

    //删除某话题
    void deleteTopic(Topic topic);

    //按条件分页查找所有
    List<Topic> findAll(int start, int row, Order order);
}
