package com.knowledge_network.knowledge_network.service;

import com.knowledge_network.knowledge_network.entity.Tag;

/**
 * Created with IntelliJ IDEA.
 * User: wjk
 * Date: 18-1-1
 * Time: 下午10:24
 */
public interface TagService {
    /**
     * 根据id获取tag
     * @param id 待获取的id
     * @return tag对象
     */
    Tag getTagById(int id);

    /**
     * 根据名称获取Tag
     * @param name 待查名称
     * @return tag对象
     */
    Tag getTagByName(String name);
}
