package com.knowledge_network.knowledge_network.service.impl;

import com.knowledge_network.knowledge_network.dao.ResourceDAO;
import com.knowledge_network.knowledge_network.dao.ResourceTypeDAO;
import com.knowledge_network.knowledge_network.service.inner.InnerResourceService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wshish000 on 17-12-13
 */
public class InnerResourceServiceImpl implements InnerResourceService {

    @Autowired
    private ResourceDAO resourceDAO;

    @Autowired
    private ResourceTypeDAO resourceTypeDAO;

    @Override
    public ResourceDAO getResourceDAO() {
        return resourceDAO;
    }

    @Override
    public ResourceTypeDAO getResourceTypeDAO() {
        return resourceTypeDAO;
    }
}
