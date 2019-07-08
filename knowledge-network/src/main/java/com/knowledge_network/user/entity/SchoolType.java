package com.knowledge_network.user.entity;

import javax.persistence.*;

/**
 * Created by pentonbin on 18-1-22
 * <p>
 * 学校类型（id：类型名称）
 * 1：公立小学
 * 2：公立中学
 * 3：公立九年一贯制
 * 8：公立十二年一贯制
 * 4：私立小学
 * 5：私立中学
 * 6：私立九年一贯制
 * 9：私立十二年一贯制
 * 7：培训机构
 */
@Entity
@Table(name = "school_type")
public class SchoolType {

    /**
     * 学校类型id
     * 与业务无关，自动生成（递增）
     */
    private Integer id;
    /**
     * 学校类型名称
     */
    private String name;

    public SchoolType() {
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
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        SchoolType schoolType = (SchoolType) obj;

        if (id != schoolType.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }
}
