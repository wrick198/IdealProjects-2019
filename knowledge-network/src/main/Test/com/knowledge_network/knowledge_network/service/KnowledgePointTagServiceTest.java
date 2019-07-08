package com.knowledge_network.knowledge_network.service;

import com.knowledge_network.knowledge_network.entity.Tag;
import com.knowledge_network.knowledge_network.vo.TagInfoVO;
import com.knowledge_network.support.base.BaseServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * # Created by HeartUnderBlade on 2017/12/26
 */
public class KnowledgePointTagServiceTest extends BaseServiceTest {

    @Autowired
    private KnowledgePointTagService knowledgePointTagService;

    @Test
    public void testTag() {
        String testTagName = "test1_tag";
        TagInfoVO newTagInfo = new TagInfoVO();
        newTagInfo.setName(testTagName);
        knowledgePointTagService.createTag(newTagInfo);


        Tag result = knowledgePointTagService.getTagByName(testTagName);
        TagInfoVO newInfo = new TagInfoVO(result);
        newInfo.setName("new_test1_tag");


        knowledgePointTagService.updateTag(result, newInfo);

        knowledgePointTagService.deleteTag(result.getId());
    }
}
