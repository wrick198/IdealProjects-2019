package com.knowledge_network.circle.service;

import com.knowledge_network.circle.entity.Collect;
import com.knowledge_network.circle.entity.Topic;
import com.knowledge_network.user.entity.User;

import java.util.List;

/**
 * Created by wshish000 on 18-3-4
 */
public interface CollectService {

    /**
     * 查询用户收藏的话题
     *
     * @param p
     * @param size
     * @param user
     * @return
     */
    List<Collect> findByUser(int p, int size, User user);

    /**
     * 查询用户共收藏了多少篇话题
     *
     * @param user
     * @return
     */
    long countByUser(User user);

    /**
     * 查询话题共被多少用户收藏
     *
     * @param topic
     * @return
     */
    long countByTopic(Topic topic);

    /**
     * 根据用户和话题查询收藏记录
     *
     * @param user
     * @param topic
     * @return
     */
    Collect findByUserAndTopic(User user, Topic topic);

    /**
     * 收藏话题
     *
     * @param collect
     */
    void save(Collect collect);

    void deleteById(int id);

    /**
     * 删除用户的收藏
     *
     * @param user
     */
    void deleteByUser(User user);

    /**
     * 删除话题的收藏
     *
     * @param topic
     */
    void deleteByTopic(Topic topic);
}
