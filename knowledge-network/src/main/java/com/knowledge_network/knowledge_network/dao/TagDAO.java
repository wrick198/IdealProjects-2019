package com.knowledge_network.knowledge_network.dao;

import com.knowledge_network.knowledge_network.entity.Tag;

import java.awt.*;
import java.util.Collection;

/**
 * Created by wshish000 on 17-12-6
 * 提供访问标签表tag的DAO
 */
public interface TagDAO {

    /**
     * 根据标签ID获取对应的标签
     *
     * @param id 标签的ID
     * @return 查找的标签
     */
    Tag findTagById(Integer id);

    /**
     * 根据标签名name获取对应的标签
     *
     * @param name 标签的名字
     * @return 查找的标签
     */
    Tag findTagByName(String name);

    /**
     * 添加一条新的标签
     *
     * @param tag 新增的标签对象
     */
    void addTag(Tag tag);

    /**
     * 更新某条标签
     *
     * @param tag 想要更改的标签对象
     */
    void updateTag(Tag tag);

    /**
     * 根据标签ID删除对应的标签
     *
     * @param tag 标签的ID
     */
    void deleteTag(Tag tag);

    Collection<Tag> findTagsByName(String name);
}
