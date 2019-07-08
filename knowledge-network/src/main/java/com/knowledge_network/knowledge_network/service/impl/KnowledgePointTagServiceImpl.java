package com.knowledge_network.knowledge_network.service.impl;

import com.knowledge_network.knowledge_network.dao.KnowledgePointDAO;
import com.knowledge_network.knowledge_network.dao.TagDAO;
import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.knowledge_network.entity.Tag;
import com.knowledge_network.knowledge_network.service.KnowledgePointTagService;
import com.knowledge_network.knowledge_network.vo.TagInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wshish000 on 17-12-6
 */
@Service
public class KnowledgePointTagServiceImpl implements KnowledgePointTagService {

    @Autowired
    private TagDAO tagDao;

    @Autowired
    private KnowledgePointDAO knowledgePointDao;

    @Override
    public List<Tag> getKnowledgePointTags(int knowledgePointId) {
        KnowledgePoint newKp = knowledgePointDao.findKnowledgePointByID(knowledgePointId);
        return (List<Tag>) newKp.getTags();
    }

    @Override
    public void addKnowledgePointTags(int knowledgePointId, Set<Tag> tag) {
        KnowledgePoint oldKp = knowledgePointDao.findKnowledgePointByID((knowledgePointId));
        Set<Tag> tags = (Set<Tag>) oldKp.getTags();
        tags.addAll(tag);
        oldKp.setTags(tags);
    }


    @Override
    public void deleteKnowledgePointTags(int knowledgePointId, Set<Tag> tag) {
        KnowledgePoint oldKp = knowledgePointDao.findKnowledgePointByID((knowledgePointId));
        Set<Tag> tags = (Set<Tag>) oldKp.getTags();
        tags.removeAll(tag);
        oldKp.setTags(tags);
    }

    @Override
    public void createTag(TagInfoVO tagInfo) {
        Tag newTag = new Tag();
        if (tagInfo.getName() != null) {
            newTag.setName(tagInfo.getName());
        }
        tagDao.addTag(newTag);
    }

    @Override
    public List<Tag> getTagByNames(Set<String> names) {
        if (names.size() == 0) return null;
        List<Tag> results = new ArrayList<>();
        for (String name : names) {
            results.add(tagDao.findTagByName(name));
        }
        return results;
    }

    @Override
    public Tag getTagByName(String name) {
        if (name.isEmpty()) return null;
        return tagDao.findTagByName(name);
    }


    @Override
    public void updateTag(Tag oldTag, TagInfoVO tagInfo) {
        if (oldTag != null) {
            oldTag.setName(tagInfo.getName());
        }
        tagDao.updateTag(oldTag);
    }

    @Override
    public void deleteTag(int tagId) {
        Tag oldTag = tagDao.findTagById(tagId);
        if (oldTag != null) {
            tagDao.deleteTag(oldTag);
        }
    }
}
