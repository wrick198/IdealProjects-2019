package com.knowledge_network.quize.dao.Impl;

import com.knowledge_network.quize.dao.QuestionTypeDAO;
import com.knowledge_network.quize.entity.QuestionType;
import com.knowledge_network.support.base.BaseHibernateDAO;
import org.springframework.stereotype.Repository;

/**
 * ** Created by gongjiangtao on 2018/4/16
 * 提供访问QuestionType的DAO
 **/
@Repository
public class QuestionTypeDAOImpl extends BaseHibernateDAO<QuestionType> implements QuestionTypeDAO {
    /**
     * 通过id查找questionType
     * @param id
     * @return
     */
    @Override
    public QuestionType findQuestionTypeById(int id) {
        return findById(id);
    }

    /**
     * 通过name查询问题类型
     * @param name 待查名称
     * @return
     */
    @Override
    public QuestionType findQuestionTypeByName(String name) {
        return findByUnique("name", name);
    }

    /**
     * 通过类型查询问题类型
     * @param type 待查类型
     * @return
     */
    @Override
    public QuestionType findQuestionTypeByType(int type) {
        return findByUnique("questionType", type);
    }
}
