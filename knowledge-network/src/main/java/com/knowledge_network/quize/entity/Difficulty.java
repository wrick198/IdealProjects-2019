package com.knowledge_network.quize.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * ** Created by gongjiangtao on 2018/4/12
 * 难度等级实体类
 **/
@Entity
@Table(name = "difficulty")
public class Difficulty implements Serializable {
    /**
     * 分为简单、中等、困难三个等级
     * 后续根据需要陆续添加
     */
    public static final int EASY = 1;
    public static final int MEDIUM = 2;
    public static final int DIFFICULTY = 3;

    /**
     * 难度等级Id
     */
    private Integer id;

    /**
     * 难度等级字段，值为上面的难度等级
     */
    private int difficultyLevel;

    /**
     * 名称描述字段
     */
    private String name;

    /**
     * 与Quize表的关联
     * 一个难度可以有多个Quize
     */
    private Collection<Quize> quizes = new ArrayList<>();

    public Difficulty() {}

    @Id
    @Column(name = "difficulty_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    @Basic
    @Column(name = "difficulty_level", nullable = false, unique = true)
    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    @Basic
    @Column(name = "difficulty_name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(targetEntity = Quize.class, cascade = CascadeType.ALL, mappedBy = "difficulty")
    public Collection<Quize> getQuizes() {
        return quizes;
    }

    public void setQuizes(Collection<Quize> quizes) {
        this.quizes = quizes;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;

        Difficulty that = (Difficulty) obj;
        if (that.getId() != getId()) return false;
        else if (that.getDifficultyLevel() != getDifficultyLevel()) return false;

        return true;
    }
}
