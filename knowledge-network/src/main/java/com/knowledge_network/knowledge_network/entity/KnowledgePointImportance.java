package com.knowledge_network.knowledge_network.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by pentonbin on 17-12-1
 */
@Entity
@Table(name = "knowledge_point_importance")
public class KnowledgePointImportance implements Serializable {

    /**
     * 知识点重要程度id
     * 与业务无关，自动生成（递增）
     */
    private Integer id;
    /**
     * 知识点重要程度级别
     */
    private int level;
    /**
     * 知识点重要程度的级别名称
     */
    private String name;
    /**
     * 包含当前重要程度的知识点
     */
    private Collection<KnowledgePoint> knowledgePoints = new ArrayList<>();

    public KnowledgePointImportance() {
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
    @Column(name = "level", nullable = false, unique = true)
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Basic
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(targetEntity = KnowledgePoint.class, cascade = CascadeType.ALL, mappedBy = "knowledgePointImportance")

    public Collection<KnowledgePoint> getKnowledgePoints() {
        return knowledgePoints;
    }

    public void setKnowledgePoints(Collection<KnowledgePoint> knowledgePoints) {
        this.knowledgePoints = knowledgePoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KnowledgePointImportance that = (KnowledgePointImportance) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }
}
