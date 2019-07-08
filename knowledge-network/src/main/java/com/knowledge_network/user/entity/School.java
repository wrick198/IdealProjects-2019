package com.knowledge_network.user.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by pentonbin on 17-12-1
 */
@Entity
@Table(name = "school")
public class School implements Serializable {
    /**
     * 学校id
     * 与业务无关，自动生成（递增）
     */
    private Integer id;
    /**
     * 学校名字
     */
    private String name;
    /**
     * 类型，无用字段
     * TODO 后期删除
     */
    private Integer type;
    /**
     * 拼音简写（首字母）
     */
    private String shortSpell;
    /**
     * 拼音全拼
     */
    private String spell;
    /**
     * 所在的省份
     */
    private City province;
    /**
     * 所在的城市
     */
    private City city;
    /**
     * 所在的县/区
     */
    private City county;
    /**
     * 状态
     * TODO 后期删除
     */
    private Integer status;
    /**
     * 学校类型
     */
    private SchoolType schoolType;
    /**
     * 无用字段
     * TODO 后期删除
     */
    private Integer userDefine;
    /**
     * 该学校的所有学生
     */
    private Collection<Student> students;
    /**
     * 该学校所有的教师
     */
    private Collection<Teacher> teachers;
    /**
     * 该学校所有的班级
     */
    private Collection<Clazz> clazzes;

    public School() {
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

    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "short_spell", nullable = false)
    public String getShortSpell() {
        return shortSpell;
    }

    public void setShortSpell(String shortSpell) {
        this.shortSpell = shortSpell;
    }

    @Basic
    @Column(name = "spell", nullable = false)
    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    @ManyToOne(targetEntity = City.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "county_id", referencedColumnName = "id")
    public City getCounty() {
        return county;
    }

    public void setCounty(City county) {
        this.county = county;
    }

    @ManyToOne(targetEntity = City.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @ManyToOne(targetEntity = City.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "province_id", referencedColumnName = "id")
    public City getProvince() {
        return province;
    }

    public void setProvince(City province) {
        this.province = province;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @ManyToOne(targetEntity = SchoolType.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "school_type_id", referencedColumnName = "id")
    public SchoolType getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(SchoolType schoolType) {
        this.schoolType = schoolType;
    }

    @Basic
    @Column(name = "user_define")
    public Integer getUserDefine() {
        return userDefine;
    }

    public void setUserDefine(Integer userDefine) {
        this.userDefine = userDefine;
    }

    @OneToMany(targetEntity = Student.class, cascade = CascadeType.ALL, mappedBy = "school")
    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    @OneToMany(targetEntity = Teacher.class, cascade = CascadeType.ALL, mappedBy = "school")
    public Collection<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Collection<Teacher> teachers) {
        this.teachers = teachers;
    }

    @OneToMany(targetEntity = Clazz.class, cascade = CascadeType.ALL, mappedBy = "school")
    public Collection<Clazz> getClazzes() {
        return clazzes;
    }

    public void setClazzes(Collection<Clazz> clazzes) {
        this.clazzes = clazzes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        School school = (School) o;

        if (id != school.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }
}
