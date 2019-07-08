package com.knowledge_network.knowledge_network.service.impl;

import com.knowledge_network.auth.user.LoginUserHolder;
import com.knowledge_network.knowledge_network.dao.ResourceDAO;
import com.knowledge_network.knowledge_network.entity.Course;
import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.knowledge_network.entity.Resource;
import com.knowledge_network.knowledge_network.entity.ResourceType;
import com.knowledge_network.knowledge_network.service.*;
import com.knowledge_network.knowledge_network.vo.ResourceInfoVO;
import com.knowledge_network.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by wshish000 on 17-12-6
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDAO resourceDAO;
    @Autowired
    private KnowledgePointService knowledgePointService;
    @Autowired
    private UserService userService;
    @Autowired
    private ResourceTypeService resourceTypeService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private SubjectService subjectService;

    @Override
    public Resource getResourceById(Integer id) {
        return resourceDAO.findResourceById(id);
    }

    @Override
    public List<Resource> getResourceByType(ResourceType resourceType) {
        return null;
    }

    @Override
    public List<Resource> getResourceByDescription(String description) {
        return resourceDAO.findResourceByDescription(description);
    }

    @Override
    public List<Resource> getResourceByConditions(Map<String, String> conditions) {
        return resourceDAO.findResourceByConditions(conditions);
    }

    @Override
    public List<Resource> getResourceByUploader(String uploader) {
        return null;
    }

    @Override
    public List<Resource> getResourceByKnowledgePoint(KnowledgePoint knowledgePoint) {
        return null;
    }

    @Override
    public List<Resource> getResourceByCourse(Course course) {
        return null;
    }

    @Override
    public Resource createResource(ResourceInfoVO resourceInfoVO) {
        Resource resource = new Resource();
        String url = resourceInfoVO.getUrl();

        resource.setDownload(0);
        resource.setScoreTimes(0);
        resource.setScore(0.0);
        resource.setRank(0);
        resource.setUploader(userService.getUserById(LoginUserHolder.getInstance().getCurrentLoginUser().getUserId()));
        resource.setUrl(url);
        resource.setUploadDatetime(new Timestamp(System.currentTimeMillis()));
        resource.setEnable(Resource.RESOURCE_ENABLE);
        resource.setDeleted(Resource.RESOURCE_NON_DELETED);
        String type = url.substring(url.lastIndexOf(".") + 1).toLowerCase();
        resource.setResourceType(resourceTypeService.getResourceTypeByType(type));
        updateResource(resource, resourceInfoVO);
        resourceDAO.updateResource(resource);
        return resource;
    }

    @Override
    public void updateResource(Resource resource, ResourceInfoVO resourceInfoVO) {
        if (resource == null) {
            return;
        }

        resource.setName(resourceInfoVO.getName());
        resource.setGrade(resourceInfoVO.getGrade());
        resource.setDescription(resourceInfoVO.getDescription());
        // TODO：暂时非付费，后期决定
        resource.setNeedPay(Resource.RESOURCE_NON_NEED_PAY);
        // downloadUser, markedScoreUser通过另外的接口更新这两个数据
        resource.setSubject(subjectService.getSubjectById(resourceInfoVO.getSubject().getId()));
        resource.getCourses().clear();
        resource.getKnowledgePoints().clear();
        for (Integer cid : resourceInfoVO.getCourses()) {
            resource.getCourses().add(courseService.getCourseById(cid));
        }
        for (Integer kid : resourceInfoVO.getKnowledgePoints()) {
            resource.getKnowledgePoints().add(knowledgePointService.getKnowledgePointByID(kid));
        }
    }

    @Override
    public void modifyResource(Resource resource, Map<String, String[]> resourceInfo) {

    }

    @Override
    public void deleteResource(Resource resource) {
        resourceDAO.deleteResource(resource);
    }

    @Override
    public void downloadResource(String link) {

    }
}
