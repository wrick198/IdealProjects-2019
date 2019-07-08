package com.knowledge_network.quize.service.Impl;

import com.knowledge_network.quize.dao.PaperTypeDAO;
import com.knowledge_network.quize.entity.Paper;
import com.knowledge_network.quize.entity.PaperType;
import com.knowledge_network.quize.service.PaperTypeService;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.exceptions.PaperTypeNotFoundException;
import com.knowledge_network.support.utils.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ** Created by gongjiangtao on 2018/4/18
 * PaperTypeService接口的实现
 **/
@Service
public class PaperTypeServiceImpl implements PaperTypeService {
    @Autowired
    PaperTypeDAO paperTypeDAO;

    /**
     * 通过名称获取PaperType
     *
     * @param name 待查的名称
     * @return
     */
    @Override
    public PaperType getPaperTypesByName(String name) {
        Asserts.notNull(name, ResponseErrorEnum.PAPER_TYPE_NAME_NOT_NULL);
        return paperTypeDAO.findPaperTypesByName(name);
    }

    /**
     * 通过类型获取PaperType
     *
     * @param type 待查的类型
     * @return
     */
    @Override
    public PaperType getPaperTypesByType(int type) {
        Asserts.notNull(type, ResponseErrorEnum.PAPER_TYPE_NOT_NULL);
        return paperTypeDAO.findPaperTypesByType(type);
    }

    /**
     * 通过id获取PaperType
     *
     * @param id 待查的id
     * @return
     */
    @Override
    public PaperType getPaperTypeById(int id) {
        Asserts.notNull(id, ResponseErrorEnum.PAPER_TYPE_NOT_NULL);
        return paperTypeDAO.findPaperTypeById(id);
    }

    /**
     * 获取与paper的类型相同的PaperType
     *
     * @param paper
     * @return
     */
    @Override
    public PaperType getSamePaperTypes(Paper paper) {
        Asserts.notNull(paper, ResponseErrorEnum.PAPER_NOT_NULL);
        PaperType paperType = paper.getPaperType();
        return getPaperTypesByType(paperType.getType());
    }

    /**
     * 更新paper的名称
     *
     * @param paperType 待更新的paperType对象
     * @param name      新的名称
     */
    @Override
    public void updatePaperTypeName(PaperType paperType, String name) {
        Asserts.notNull(paperType, ResponseErrorEnum.PAPER_TYPE_NOT_NULL);
        Asserts.notNull(name, ResponseErrorEnum.PAPER_TYPE_NAME_NOT_NULL);
        paperType.setName(name);
        paperTypeDAO.updatePaperType(paperType);
    }

    /**
     * 更新PaperType的类型
     *
     * @param paperType 待更新的paperType对象
     * @param type      新的类型
     */
    @Override
    public void updatePaperTypeType(PaperType paperType, int type) {
        Asserts.notNull(paperType, ResponseErrorEnum.PAPER_TYPE_NOT_NULL);
        Asserts.notNull(type, ResponseErrorEnum.PAPER_TYPE_NOT_NULL);
        paperType.setType(type);
        paperTypeDAO.updatePaperType(paperType);
    }

    /**
     * 检查是否是有效的PaperType
     *
     * @param paperType
     * @return
     */
    @Override
    public boolean checkValidPaperType(PaperType paperType) {
        // TODO:待完善
        return true;
    }

    /**
     * 取消Paper和PaperType的连接
     *
     * @param paper     paper对象
     * @param paperType paperType对象
     */
    @Override
    public void cancelPaperAndPaperTypeConnection(Paper paper, PaperType paperType) {

    }

    /**
     * 删除PaperType
     *
     * @param paperType 待删除的试卷类型
     */
    @Override
    public void deletePaperType(PaperType paperType) {
        Asserts.notNull(paperType, ResponseErrorEnum.PAPER_TYPE_NOT_NULL);
        PaperType pt = paperTypeDAO.findPaperTypeById(paperType.getId());
        if (pt == null) throw new PaperTypeNotFoundException(paperType.getId());
        paperTypeDAO.deletePaperType(paperType);
    }
}
