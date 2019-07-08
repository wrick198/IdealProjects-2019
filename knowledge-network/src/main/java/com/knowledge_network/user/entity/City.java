package com.knowledge_network.user.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by pentonbin on 18-1-22
 * <p>
 * 城市列表，包括了省份、市、区/县
 */
@Entity
@Table(name = "city")
public class City implements Serializable {

    public static final int CITY_MUNICIPALITY = 1;
    public static final int CITY_NON_MUNICIPALITY = 0;

    public static final int CITY_LEVEL_PROVINCE = 1;
    public static final int CITY_LEVEL_CITY = 2;
    public static final int CITY_LEVEL_AREA = 3;

    public static final int CITY_HAS_SCHOOL = 1;
    public static final int CITY_HAS_NO_SCHOOL = 0;

    /**
     * 城市id
     * 与业务无关，自动生成（递增）
     */
    private Integer id;
    /**
     * 省、市、区/县名称
     */
    private String name;
    /**
     * 对应上一级的地域，例如市上一级为省、区/县上上一级为市
     */
    private City parent;
    /**
     * 当前地域级别
     * 1：省/直辖市，值为{@link City#CITY_LEVEL_PROVINCE}
     * 2：市，值为{@link City#CITY_LEVEL_CITY}
     * 3：县/区，值为{@link City#CITY_LEVEL_AREA}
     */
    private Integer level;
    /**
     * 地域首字母
     */
    private String first;
    /**
     * 是否为直辖市
     * 1：直辖市，值为{@link City#CITY_MUNICIPALITY}
     * 0：非直辖市，值为{@link City#CITY_NON_MUNICIPALITY}
     */
    private Integer municipality;
    /**
     * 当前地域是否存在学校
     * 0：没有，值为{@link City#CITY_HAS_NO_SCHOOL}
     * 1：有，值为{@link City#CITY_HAS_SCHOOL}
     */
    private Integer hasSchool;
    /**
     * 当前地域的下一级地域
     */
    private Collection<City> subCity = new ArrayList<>();

    public City() {
    }

    @Id
    @Column(name = "id", nullable = false)
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

    @ManyToOne(targetEntity = City.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    public City getParent() {
        return parent;
    }

    public void setParent(City parent) {
        this.parent = parent;
    }

    @Basic
    @Column(name = "level", nullable = false)
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Basic
    @Column(name = "first", nullable = false)
    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    @Basic
    @Column(name = "municipality", nullable = false)
    public Integer getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Integer municipality) {
        this.municipality = municipality;
    }

    @Basic
    @Column(name = "has_school", nullable = false)
    public Integer getHasSchool() {
        return hasSchool;
    }

    public void setHasSchool(Integer hasSchool) {
        this.hasSchool = hasSchool;
    }

    @OneToMany(targetEntity = City.class, cascade = CascadeType.ALL, mappedBy = "parent")
    public Collection<City> getSubCity() {
        return subCity;
    }

    public void setSubCity(Collection<City> subCity) {
        this.subCity = subCity;
    }
}
