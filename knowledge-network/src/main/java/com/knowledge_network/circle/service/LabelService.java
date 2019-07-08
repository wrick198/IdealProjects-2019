package com.knowledge_network.circle.service;

import com.knowledge_network.circle.entity.Label;

import java.util.List;

/**
 * Created by wshish000 on 18-3-4
 */
public interface LabelService {

    /**
     * delete label
     * @param label
     */
    void delete(Label label);

    /**
     * query label by id
     * @param id
     * @return
     */
    Label findById(int id);

    void save(Label label);

    /**
     * query label by name
     * @param name
     * @return
     */
    Label findByName(String name);

    /**
     * fuzzy query label by name
     * @param text
     * @return
     */
    List<Label> findByNameLike(String text);

    /**
     * search all nodes order by topicCount desc
     * @param p
     * @param size
     * @return
     */
    List<Label> findAll(int p, int size);

    /**
     * deal label from create topic
     * @param labels
     * @return
     */
    String dealLabels(String labels);

    /**
     * when update topic, first deal label's topicCount, then invoke method #dealLabels()
     * @param labels
     */
    void dealEditTopicOldLabels(String labels);
}
