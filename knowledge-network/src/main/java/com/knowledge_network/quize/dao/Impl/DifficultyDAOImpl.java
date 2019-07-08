package com.knowledge_network.quize.dao.Impl;

import com.knowledge_network.quize.dao.DifficultyDAO;
import com.knowledge_network.quize.entity.Difficulty;
import com.knowledge_network.support.base.BaseHibernateDAO;
import org.springframework.stereotype.Repository;

/**
 * ** Created by gongjiangtao on 2018/4/16
 * 提供访问Difficulty的访问
 **/
@Repository
public class DifficultyDAOImpl extends BaseHibernateDAO<Difficulty> implements DifficultyDAO {
    /**
     * 通过id查找Difficulty
     * @param id
     * @return
     */
    @Override
    public Difficulty findDifficultyById(int id) {
        return findById(id);
    }

    @Override
    public Difficulty findDifficultyByDifficultyLevel(int difficultyLevel) {
        return findByUnique("difficultyLevel", difficultyLevel);
    }

    @Override
    public Difficulty findDIfficultyByDifficultyName(String name) {
        return findByUnique("name", name);
    }
}
