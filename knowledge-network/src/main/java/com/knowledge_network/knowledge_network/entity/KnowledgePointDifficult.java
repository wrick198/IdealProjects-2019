package com.knowledge_network.knowledge_network.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by pentonbin on 17-12-1
 */
@Entity
@Table(name = "knowledge_point_difficult")
public class KnowledgePointDifficult implements Serializable {

    /**
     * 知识点困难程度id
     * 与业务无关，自动生成（递增）
     */
    private Integer id;
    /**
     * 知识点困难程度级别
     */
    private int level;
    /**
     * 知识点困难程度的级别名称
     */
    private String name;
    /**
     * 包含当前困难程度的知识点
     */
    private Collection<KnowledgePoint> knowledgePoints = new ArrayList<>();

    public KnowledgePointDifficult() {
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
    @Column(name = "level", nullable = false)
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

    @OneToMany(targetEntity = KnowledgePoint.class, cascade = CascadeType.ALL, mappedBy = "knowledgePointDifficult")
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

        KnowledgePointDifficult that = (KnowledgePointDifficult) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }
}
