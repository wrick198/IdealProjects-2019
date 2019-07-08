package com.knowledge_network.quize.service.Impl;

import com.knowledge_network.quize.dao.DifficultyDAO;
import com.knowledge_network.quize.entity.Difficulty;
import com.knowledge_network.quize.service.DifficultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ** Created by gongjiangtao on 2018/4/25
 **/
@Service
public class DifficultyServiceImpl implements DifficultyService {
    @Autowired
    DifficultyDAO difficultyDAO;

    @Override
    public Difficulty getDifficultyByDifficultyLevel(int level) {
        return difficultyDAO.findDifficultyById(level);
    }

    @Override
    public Difficulty getDifficultyById(int id) {
        return difficultyDAO.findDifficultyById(id);
    }

    @Override
    public Difficulty getDifficultyByName(String name) {
        return difficultyDAO.findDIfficultyByDifficultyName(name);
    }
}
