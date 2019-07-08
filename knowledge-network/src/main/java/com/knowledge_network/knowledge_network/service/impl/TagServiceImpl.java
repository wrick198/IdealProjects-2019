package com.knowledge_network.knowledge_network.service.impl;

import com.knowledge_network.knowledge_network.dao.TagDAO;
import com.knowledge_network.knowledge_network.entity.Tag;
import com.knowledge_network.knowledge_network.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: wjk
 * Date: 18-1-1
 * Time: 下午10:27
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagDAO tagDAO;

    @Override
    public Tag getTagById(int id) {
        return tagDAO.findTagById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDAO.findTagByName(name);
    }
}
