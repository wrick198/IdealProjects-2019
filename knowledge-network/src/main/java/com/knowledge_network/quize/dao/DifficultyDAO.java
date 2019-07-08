package com.knowledge_network.quize.dao;

import com.knowledge_network.quize.entity.Difficulty;

/**
 * ** Created by gongjiangtao on 2018/4/16
 *  提供访问Difficulty的DAO
 **/
public interface DifficultyDAO {
    /**
     * 通过id查找难度
     * @param id
     * @return id对应难度对象
     */
    Difficulty findDifficultyById(int id);

    /**
     * 通过难度等级查询难度
     * @param difficultyLevel 待查难度等级
     * @return 难度等级为difficultyLevel的难度
     */
    Difficulty findDifficultyByDifficultyLevel(int difficultyLevel);

    /**
     * 通过难度等级查询难度
     * @param name 待查名称
     * @return 名称为name的难度
     */
    Difficulty findDIfficultyByDifficultyName(String name);
}
