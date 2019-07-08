package com.knowledge_network.quize.vo;

import com.knowledge_network.quize.entity.Difficulty;

/**
 * ** Created by gongjiangtao on 2018/4/24
 **/
public class DifficultyInfoVO {
    private int id;

    private int difficultyLevel;

    private String name;

    public DifficultyInfoVO() {
    }

    public DifficultyInfoVO(Difficulty difficulty) {
        id = difficulty.getId();
        difficultyLevel = difficulty.getDifficultyLevel();
        name = difficulty.getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
