package com.knowledge_network.knowledge_network.vo;

import com.knowledge_network.knowledge_network.entity.ResourceType;

/**
 * Created by pentonbin on 18-4-18
 */
public class ResourceTypeInfoVO {

    private Integer id;
    private String type;

    public ResourceTypeInfoVO() {
    }

    public ResourceTypeInfoVO(ResourceType resourceType) {
        id = resourceType.getId();
        type = resourceType.getType();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
