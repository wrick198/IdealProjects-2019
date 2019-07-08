package com.knowledge_network.user.entity;

import com.knowledge_network.knowledge_network.entity.KnowledgePoint;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by pentonbin on 17-12-1
 */
@Entity
@Table(name = "teaching_plan")
public class TeachingPlan implements Serializable {

    /**
     * 教案id
     * 与业务无关，自动生成（递增）
     */
    private Integer id;
    /**
     * 教案内容
     */
    private String content;
    /**
     * 教案所包含的知识点集合
     */
    private Collection<KnowledgePoint> knowledgePoints = new ArrayList<>();

    public TeachingPlan() {
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
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ManyToMany(targetEntity = KnowledgePoint.class, cascade = CascadeType.ALL)
    @JoinTable(name = "teaching_plan_has_knowledge_point",
            joinColumns = @JoinColumn(name = "teaching_plan_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "knowledge_point_id", referencedColumnName = "id")
    )
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

        TeachingPlan that = (TeachingPlan) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }
}
