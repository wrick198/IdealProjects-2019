package com.knowledge_network.user.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created by pentonbin on 17-12-1
 */
@Entity
@Table(name = "system_manager")
@PrimaryKeyJoinColumn(name = "user_id")
public class SystemManager extends User {

    public SystemManager() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SystemManager that = (SystemManager) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
