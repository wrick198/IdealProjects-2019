package com.knowledge_network.knowledge_network.service.impl;

import com.knowledge_network.knowledge_network.dao.ResourceTypeDAO;
import com.knowledge_network.knowledge_network.entity.ResourceType;
import com.knowledge_network.knowledge_network.service.ResourceTypeService;
import com.knowledge_network.support.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pentonbin on 18-4-18
 */
@Service
public class ResourceTypeServiceImpl implements ResourceTypeService {

    @Autowired
    private ResourceTypeDAO resourceTypeDAO;

    @Override
    public ResourceType getResourceTypeById(Integer id) {
        if (id != null) {
            return resourceTypeDAO.findResourceTypeById(id);
        }
        return null;
    }

    @Override
    public ResourceType getResourceTypeByType(String type) {
        if (StringUtils.isNullOrEmpty(type)) {
            return null;
        }
        return resourceTypeDAO.findResourceTypeByType(type);
    }
}
