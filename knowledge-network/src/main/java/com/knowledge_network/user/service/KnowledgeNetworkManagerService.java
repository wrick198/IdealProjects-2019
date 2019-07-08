package com.knowledge_network.user.service;

import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.KnowledgeNetworkManager;
import com.knowledge_network.user.vo.KnowledgeNetworkManagerInfoVO;

import java.util.List;

/**
 * Created by pentonbin on 17-12-14
 */
public interface KnowledgeNetworkManagerService {

    /**
     * 根据知识网络管理员用户id获取知识网络管理员对象
     *
     * @param id 知识网络管理员用户id
     * @return 知识网络管理员对象
     */
    KnowledgeNetworkManager getKnowledgeNetworkManagerById(Integer id);

    /**
     * 更新知识网络管理员的个人信息，将同步到数据库
     *
     * @param knowledgeNetworkManager       知识网络管理员
     * @param knowledgeNetworkManagerInfoVO 需要修改的知识网络管理员信息
     */
    void updateKnowledgeNetworkManagerInfo(KnowledgeNetworkManager knowledgeNetworkManager,
                                           KnowledgeNetworkManagerInfoVO knowledgeNetworkManagerInfoVO);

    /**
     * 初始化知识网络管理员的个人信息，将不同步到数据库
     *
     * @param knowledgeNetworkManager       知识网络管理员
     * @param knowledgeNetworkManagerInfoVO 需要修改的知识网络管理员信息
     */
    void initKnowledgeNetworkManagerInfo(KnowledgeNetworkManager knowledgeNetworkManager,
                                         KnowledgeNetworkManagerInfoVO knowledgeNetworkManagerInfoVO);

    /**
     * 创建新的知识网络管理员用户
     *
     * @param knowledgeNetworkManager 知识网络管理员用户
     */
    void createKnowledgeNetworkManager(KnowledgeNetworkManager knowledgeNetworkManager);

    /**
     * 获取所有知识网络管理员分页
     *
     * @param start 起始下标
     * @param rows  每一页行数
     * @return 分页知识网络管理员
     */
    List<KnowledgeNetworkManager> getAllKnowledgeNetworkManagerPage(int start, int rows);

    /**
     * 获取所有知识网络管理员人数
     *
     * @return 人数
     */
    long getAllKnowledgeNetworkManagerCount();

    /**
     * 根据条件获取学生分页
     *
     * @param start      起始下标
     * @param rows       每一页行数
     * @param conditions 条件
     * @return
     */
    List<KnowledgeNetworkManager> getKnowledgeNetworkManagerPageByConditions(int start,
                                                                             int rows,
                                                                             List<BaseHibernateDAO.Condition> conditions);

    /**
     * 根据条件获取学生人数
     *
     * @param conditions 条件
     * @return
     */
    long getKnowledgeNetworkManagerCountByConditions(List<BaseHibernateDAO.Condition> conditions);
}
