package com.knowledge_network.user.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by pentonbin on 17-12-1
 */
@Entity
@Table(name = "parent")
@PrimaryKeyJoinColumn(name = "user_id")
public class Parent extends User {

    /**
     * 该家长所关联的学生
     */
    private Collection<Student> students = new ArrayList<>();

    public Parent() {
    }

    @ManyToMany(mappedBy = "parents")
    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parent parent = (Parent) o;

        if (id != parent.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
