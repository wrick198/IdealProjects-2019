package com.knowledge_network.quize.dao;

import com.knowledge_network.quize.entity.Paper;
import com.knowledge_network.quize.entity.PaperType;

import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/4/17
 * 提供访问发PaperType的DAO
 **/
public interface PaperTypeDAO {
    /**
     * 通过id查找试卷类型
     * @param id
     * @return id对应的试卷类型
     */
    PaperType findPaperTypeById(int id);

    /**
     * 通过名称查找试卷类型
     * @param name
     * @return name对应的试卷类型
     */
    PaperType findPaperTypesByName(String name);

    /**
     * 通过类型查找试卷类型
     * @param type
     * @return type对应的试卷类型
     */
    PaperType findPaperTypesByType(int type);

    /**
     * 通过试卷查找试卷类型
     * @param paper
     * @return 试卷对应的试卷类型
     */
    List<PaperType> findPaperTypeByPaper(Paper paper);

    /**
     * 更新试卷类型
     * @param paperType
     */
    void updatePaperType(PaperType paperType);

    /**
     * 删除试卷类型
     * @param paperType
     */
    void deletePaperType(PaperType paperType);
}
