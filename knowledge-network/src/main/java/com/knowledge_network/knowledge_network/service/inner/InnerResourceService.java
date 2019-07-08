package com.knowledge_network.knowledge_network.service.inner;

import com.knowledge_network.knowledge_network.dao.ResourceDAO;
import com.knowledge_network.knowledge_network.dao.ResourceTypeDAO;

/**
 * Created by wshish000 on 17-12-13
 */
public interface InnerResourceService {
    /**
     * 获取资源DAO
     *
     * @return ResourceDAO
     */
    ResourceDAO getResourceDAO();

    /**
     * 获取资源类型DAO
     *
     * @return ResourceTypeDAO
     */
    ResourceTypeDAO getResourceTypeDAO();
}
