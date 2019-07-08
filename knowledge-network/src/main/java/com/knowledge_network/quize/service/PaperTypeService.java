package com.knowledge_network.quize.service;

import com.knowledge_network.quize.entity.Paper;
import com.knowledge_network.quize.entity.PaperType;

/**
 * ** Created by gongjiangtao on 2018/4/17
 * 试卷类型的Service接口
 **/
public interface PaperTypeService {
    /**
     * 通过id获取对应的apperType
     *
     * @param id 待查的id
     * @return id对应的PaperType
     */
    PaperType getPaperTypeById(int id);

    /**
     * 通过名称获取PaperType
     *
     * @param name 待查的名称
     * @return 名称对应的paperType对象
     */
    PaperType getPaperTypesByName(String name);

    /**
     * 通过类型获取paperType
     *
     * @param type 待查的类型
     * @return 类型对应的PaperType对象
     */
    PaperType getPaperTypesByType(int type);

    /**
     * 获取试卷试卷类型
     *
     * @param paper
     * @return
     */
    PaperType getSamePaperTypes(Paper paper);

    /**
     * 更新试卷类型的名称
     *
     * @param paperType 待更新的paperType对象
     * @param name      新的名称
     */
    void updatePaperTypeName(PaperType paperType, String name);

    /**
     * 更新试卷类型的类型
     *
     * @param paperType 待更新的paperType对象
     * @param type      新的类型
     */
    void updatePaperTypeType(PaperType paperType, int type);

    /**
     * 取消试卷类型和试卷的链接关系
     *
     * @param paper     paper对象
     * @param paperType paperType对象
     */
    void cancelPaperAndPaperTypeConnection(Paper paper, PaperType paperType);

    /**
     * 删除试卷类型
     *
     * @param paperType 待删除的试卷类型
     */
    void deletePaperType(PaperType paperType);

    /**
     * 检查试卷类型是否有效
     *
     * @param paperType
     * @return
     */
    boolean checkValidPaperType(PaperType paperType);
}
