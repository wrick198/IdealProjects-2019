package com.knowledge_network.quize.dao;

import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.knowledge_network.entity.Resource;
import com.knowledge_network.quize.entity.Paper;
import com.knowledge_network.quize.entity.PaperType;
import com.knowledge_network.quize.entity.Quize;
import com.knowledge_network.user.entity.Teacher;
import org.hibernate.criterion.Order;
import  com.knowledge_network.support.base.BaseHibernateDAO.Condition;

import java.util.Date;
import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/4/17
 *  提供访问Paper的DAO
 **/
public interface PaperDAO {
    /**
     * 通过id查找试卷
     * @param id
     * @return id对应的试卷
     */
    Paper findPaperById(int id);

    /**
     * 通过名称查找试卷
     * @param name
     * @return name对应的多个试卷
     */
    List<Paper> findPapersByName(String name);

    /**
     * 通过创建时间查询试卷
     * @param createTime
     * @return createTime对应的试卷
     */
    List<Paper> findPapersByCreateTime(Date createTime);

    /**
     * 通过试卷类型查找试卷
     * @param paperType
     * @return paperType对应的试卷
     */
    List<Paper> findPapersByPaperType(PaperType paperType);

    /**
     * 通过知识点查找试卷
     * @param knowledgePoint
     * @return knowledgePoint对应的试卷
     */
    List<Paper> findPapersByKnowledgePoint(KnowledgePoint knowledgePoint);

    /**
     * 通过出题人查找试卷
     * @param creator
     * @return creator对应的试卷
     */
    List<Paper> findPapersByCreator(Teacher creator);

    /**
     * 通过资源查找试卷
     * @param resource
     * @return resource对应的试卷
     */
    List<Paper> findPapersByResource(Resource resource);

    /**
     * 查找免费的试卷
     * @return 免费的试卷
     */
    List<Paper> findFreePapers();

    /**
     * 查找收费的试卷
     * @return 收费的试卷
     */
    List<Paper> findNotFreePapers();

    /**
     * 通过试题查找试卷
     * @param quize
     * @return quize对应的试卷
     */
    List<Paper> findPapersByQuize(Quize quize);

    /**
     * 根据条件获取Paper
     * @param start 开始行号
     * @param rows 每页行数
     * @param order 顺序
     * @param conditions 条件
     * @return 符合条件的Paper
     */
    List<Paper> findPapersByConditionOrderPage(Integer start, Integer rows, Order order, List<Condition> conditions);

    /**
     * 获取分页Paper
     * @param start 开始行号
     * @param rows 每页行数
     * @param order 顺序
     * @return 分页Paper
     */
    List<Paper> findPapersByOrderPage(Integer start, Integer rows, Order order);

    /**
     * 获取符合条件的Paper的数量
     * @param conditions 条件
     * @return 符合条件的Paper的数量
     */
    long findPaperCountByCondition(List<Condition> conditions);

    /**
     * 更新试卷
     * @param paper
     */
    void updatePaper(Paper paper);

    /**
     * 删除试卷
     * @param paper
     */
    void deletePaper(Paper paper);
}
