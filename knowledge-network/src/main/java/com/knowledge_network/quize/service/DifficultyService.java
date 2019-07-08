package com.knowledge_network.quize.service;

import com.knowledge_network.quize.entity.Difficulty;

/**
 * ** Created by gongjiangtao on 2018/4/25
 **/
public interface DifficultyService {
    /**
     * 通过id获取难度
     *
     * @param id 待查id
     * @return 难度对象
     */
    Difficulty getDifficultyById(int id);

    /**
     * 通过名称获取难度
     *
     * @param name 待查名称
     * @return 难度对象
     */
    Difficulty getDifficultyByName(String name);

    /**
     * 通过难度等级获取难度
     *
     * @param level 待查难度等级
     * @return 难度对象
     */
    Difficulty getDifficultyByDifficultyLevel(int level);
}
