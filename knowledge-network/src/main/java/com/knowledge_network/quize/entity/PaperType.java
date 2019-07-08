package com.knowledge_network.quize.entity;

import com.knowledge_network.knowledge_network.entity.KnowledgePoint;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * ** Created by gongjiangtao on 2018/4/16
 * 试卷类型实体类
 **/
@Entity
@Table(name = "paper_type")
public class PaperType implements Serializable {
    /**
     * 试卷类型，暂时分为考试卷和练习卷
     * 以后根据需要添加更多类型
     */
    public static final int EXERCISE = 1;
    public static final int EXAM = 2;

    /**
     * id属性，唯一标识一个PaperType
     */
    private int id;
    /**
     * 试卷类型名称
     */
    private String name;
    /**
     * 试卷类型，值为上面的试卷类型
     */
    private int type;

    private Collection<Paper> papers = new ArrayList<>();

    @Id
    @Column(name = "paper_type_id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "paper_type", nullable = false, unique = true)
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @OneToMany(targetEntity = Paper.class, cascade = CascadeType.ALL, mappedBy = "paperType")
    public Collection<Paper> getPapers() {
        return papers;
    }

    public void setPapers(Collection<Paper> papers) {
        this.papers = papers;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        PaperType that = (PaperType) obj;
        if (that.getId() != id) return false;
        else if (that.getType() != getType()) return false;

        return true;
    }
}
