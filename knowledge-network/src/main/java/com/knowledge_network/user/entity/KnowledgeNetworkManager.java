package com.knowledge_network.user.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created by pentonbin on 17-12-1
 */
@Entity
@Table(name = "knowledge_network_manager")
@PrimaryKeyJoinColumn(name = "teacher_user_id")
public class KnowledgeNetworkManager extends Teacher {

    public KnowledgeNetworkManager() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KnowledgeNetworkManager that = (KnowledgeNetworkManager) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
