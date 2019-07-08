package com.knowledge_network.quize.dao.Impl;

import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.knowledge_network.entity.Resource;
import com.knowledge_network.quize.dao.PaperDAO;
import com.knowledge_network.quize.entity.Paper;
import com.knowledge_network.quize.entity.PaperType;
import com.knowledge_network.quize.entity.Quize;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.Teacher;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/4/17
 * 提供访问Paper的DAO
 **/
@Repository
public class PaperDAOImpl extends BaseHibernateDAO<Paper> implements PaperDAO {
    /**
     * 通过id查找paper
     * @param id
     * @return
     */
    @Override
    public Paper findPaperById(int id) {
        return findById(id);
    }

    /**
     * 通过name查找papers
     * @param name
     * @return
     */
    @Override
    public List<Paper> findPapersByName(String name) {
        return findBy("name", name);
    }

    /**
     * 通过createTime查找papers
     * @param createTime
     * @return
     */
    @Override
    public List<Paper> findPapersByCreateTime(Date createTime) {
        return findBy("createTime", createTime);
    }

    /**
     * 通过creator查找papers
     * @param creator
     * @return
     */
    @Override
    public List<Paper> findPapersByCreator(Teacher creator) {
        return findBy("creator", creator);
    }

    /**
     * 通过knowledgePoint查找papers
     * @param knowledgePoint
     * @return
     */
    @Override
    public List<Paper> findPapersByKnowledgePoint(KnowledgePoint knowledgePoint) {
        return findBy("knowledgePoints", knowledgePoint);
    }

    /**
     * 通过paperType查找试题
     * @param paperType
     * @return
     */
    @Override
    public List<Paper> findPapersByPaperType(PaperType paperType) {
        return findBy("paperType", paperType);
    }

    /**
     * 通过resource查找papers
     * @param resource
     * @return
     */
    @Override
    public List<Paper> findPapersByResource(Resource resource) {
        return findBy("resources", resource);
    }

    /**
     * 查找免费papers
     * @return
     */
    @Override
    public List<Paper> findFreePapers() {
        return findBy("status", 1);
    }

    /**
     * 查找收费papers
     * @return
     */
    @Override
    public List<Paper> findNotFreePapers() {
        return findBy("status", 0);
    }

    /**
     * 通过试题查找papers
     * @param quize
     * @return
     */
    @Override
    public List<Paper> findPapersByQuize(Quize quize) {
        return findBy("quizes", quize);
    }

    /**
     * 获取符合条件的分页Paper
     * @param start 开始行号
     * @param rows 每页行数
     * @param order 顺序
     * @param conditions 条件
     * @return
     */
    @Override
    public List<Paper> findPapersByConditionOrderPage(Integer start, Integer rows, Order order, List<Condition> conditions) {
        return findByOrderConditionsPage(start, rows, order, conditions);
    }

    /**
     * 获取分页Paper
     * @param start 开始行号
     * @param rows 每页行数
     * @param order 顺序
     * @return
     */
    @Override
    public List<Paper> findPapersByOrderPage(Integer start, Integer rows, Order order) {
        return  findByOrderPage(order, start, rows);
    }

    /**
     * 获取符合条件的Paper数量
     * @param conditions 条件
     * @return
     */
    @Override
    public long findPaperCountByCondition(List<Condition> conditions) {
        return findCountByConditions(conditions);
    }

    /**
     * 更新试题
     * @param paper
     */
    @Override
    public void updatePaper(Paper paper) {
        save(paper);
    }

    /**
     * 删除试题
     * @param paper
     */
    @Override
    public void deletePaper(Paper paper) {
        delete(paper);
    }
}
