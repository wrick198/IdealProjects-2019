package com.knowledge_network.quize.dao.Impl;

import com.knowledge_network.quize.dao.PaperTypeDAO;
import com.knowledge_network.quize.entity.Paper;
import com.knowledge_network.quize.entity.PaperType;
import com.knowledge_network.support.base.BaseHibernateDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/4/17
 * 提供访问PaperType的DAO
 **/
@Repository
public class PaperTypeDAOImpl extends BaseHibernateDAO<PaperType> implements PaperTypeDAO {
    /**
     * 通过id查找paperType
     * @param id
     * @return
     */
    @Override
    public PaperType findPaperTypeById(int id) {
        return findById(id);
    }

    /**
     * 通过name查找paperType
     * @param name
     * @return
     */
    @Override
    public PaperType findPaperTypesByName(String name) {
        return findByUnique("name", name);
    }

    /**
     * 通过type查找paperType
     * @param type
     * @return
     */
    @Override
    public PaperType findPaperTypesByType(int type) {
        return findByUnique("type", type);
    }

    /**
     * 通过paper查找paperType
     * @param paper
     * @return
     */
    @Override
    public List<PaperType> findPaperTypeByPaper(Paper paper) {
        return findBy("paper", paper);
    }

    /**
     * 更新paperType
     * @param paperType
     */
    @Override
    public void updatePaperType(PaperType paperType) {
        save(paperType);
    }

    /**
     * 删除paperType
     * @param paperType
     */
    @Override
    public void deletePaperType(PaperType paperType) {
        delete(paperType);
    }
}
