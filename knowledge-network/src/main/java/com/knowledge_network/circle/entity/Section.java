package com.knowledge_network.circle.entity;

import javax.persistence.*;

/**
 * Created by wshish000 on 18-3-5
 */
@Entity
@Table(name = "section")
public class Section {

    private int id;

    private String name;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
