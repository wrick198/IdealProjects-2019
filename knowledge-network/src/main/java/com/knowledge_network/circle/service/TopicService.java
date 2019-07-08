package com.knowledge_network.circle.service;

import com.knowledge_network.circle.entity.Topic;
import com.knowledge_network.user.entity.User;

import java.util.Date;
import java.util.List;

/**
 * Created by wshish000 on 18-3-4
 */
public interface TopicService {

    /**
     * fuzzy topic by labelId
     * @param labelId
     * @return
     */
    List<Topic> fuzzyTopicByLabel(int labelId);

    /**
     * save topic
     * @param topic
     */
    void save(Topic topic);

    /**
     * query topic by id
     * @param id
     * @return
     */
    Topic findById(int id);

    /**
     * delete topic by id
     *
     * @param id
     */
    void deleteById(int id);

    /**
     * delete topic by user
     *
     * @param user
     */
    void deleteByUser(User user);

    /**
     * 分页查询话题列表
     *
     * @param p
     * @param size
     * @return
     */
    List<Topic> page(int p, int size, String tab, boolean lastest, Integer labelId);

    /**
     * 搜索
     *
     * @param p
     * @param size
     * @param q
     * @return
     */
    List<Topic> search(int p, int size, String q);

    /**
     * 增加回复数
     *
     * @param topicId
     */
    void addOneReplyCount(int topicId);

    /**
     * 减少回复数
     *
     * @param topicId
     */
    void reduceOneReplyCount(int topicId);

    /**
     * 查询用户的话题
     *
     * @param p
     * @param size
     * @param user
     * @return
     */
    List<Topic> findByUser(int p, int size, User user);

    /**
     * search topic count between date1 and date2
     *
     * @param date1
     * @param date2
     * @return
     */
    long countByInTimeBetween(Date date1, Date date2);

    /**
     * search by title to prevent title repeat
     *
     * @param title
     * @return
     */
    Topic findByTitle(String title);
}
