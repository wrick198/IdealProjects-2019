package com.knowledge_network.knowledge_network.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by pentonbin on 17-12-1
 */
@Entity
@Table(name = "resource_type")
public class ResourceType implements Serializable {

    /**
     * 资源类型id
     * 与业务无关，自动生成（递增）
     */
    private Integer id;
    /**
     * 资源类型字段，例如mp4，ppt，doc等
     */
    private String type;
    /**
     * 资源类型为为当前资源类型字段的所有资源
     */
    private Collection<Resource> resources = new ArrayList<>();

    public ResourceType() {
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type", nullable = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @OneToMany(targetEntity = Resource.class, cascade = CascadeType.ALL, mappedBy = "resourceType")
    public Collection<Resource> getResources() {
        return resources;
    }

    public void setResources(Collection<Resource> resources) {
        this.resources = resources;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceType that = (ResourceType) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }
}
