package com.knowledge_network.knowledge_network.service;

import com.knowledge_network.knowledge_network.entity.*;
import com.knowledge_network.knowledge_network.vo.web.AddKnowledgePointInfoVO;
import com.knowledge_network.support.base.BaseHibernateDAO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by wjk on 17-12-7.
 * <p>
 * 知识点功能相关服务接口
 */
public interface KnowledgePointService {

    /**
     * 通过知识点ID查号知识点
     *
     * @param id 要查找的知识点ID
     * @return 查找到的知识点对象
     */
    KnowledgePoint getKnowledgePointByID(int id);

    /**
     * 通过名称查找知识点
     *
     * @param name 要查找的知识点名称
     * @return 查找到的知识点对象
     */
    List<KnowledgePoint> getKnowledgePointsByName(String name);

    /**
     * 根据知识点名称查找知识点
     *
     * @param name 知识点名称
     * @return 查找到的知识点对象
     */
    KnowledgePoint getKnowledgePointByName(String name);

    /**
     * 通过简介查找知识点
     *
     * @param statement 要查找的知识点简介
     * @return 查找到的知识点对象
     */

    List<KnowledgePoint> getKnowledgePointByStatement(String statement);

    /**
     * 通过Tag查找知识点
     *
     * @param tagname 要查找的tag名称
     * @return 查找到的知识点列表
     */

    List<KnowledgePoint> getKnowledgePointByTag(String tagname);

    /**
     * 通过年级查找知识点
     *
     * @param grade 要查找的知识点所属年级
     * @return 查找到的知识点列表
     */
    List<KnowledgePoint> getKnowledgePointByGrade(int grade);

    /**
     * 通过父知识点查找子知识点
     *
     * @param name 父知识点的名称
     * @return 查找到的子知识点列表
     */
    List<KnowledgePoint> getSubKnowledgePoints(String name);

    /**
     * 通过子知识点查找父知识点
     *
     * @param name 子知识点名称
     * @return 查找到的父知识点对象
     */
    KnowledgePoint getSupKnowledgePoints(String name);

    /**
     * 查找知识点的前驱知识点
     *
     * @param name 知识点的名称
     * @return 查找到的前驱知识点对象
     */
    List<KnowledgePoint> getPreKnowledgePoints(String name);

    /**
     * 查找知识点的后继知识点
     *
     * @param name 知识点名称
     * @return 查找到的知识点对象
     */
    List<KnowledgePoint> getPostKnowledgePoints(String name);

    /**
     * 创建知识点
     *
     * @param KPinfo 要创建的知识点信息
     */
    void creatKnowledgePoint(Map KPinfo);

    /**
     * 删除知识点
     *
     * @param name 要删除的知识点名称
     */
    void deleteKnowledgePointName(String name);

    void deleteKnowledgePointByID(int id);

    /**
     * 修改知识点信息
     *
     * @param name   要修改的知识点名称
     * @param KPinfo 要修改的知识点信息
     */
    void modifyKnowledgePoint(String name, Map KPinfo);

    /**
     * 获取知识点资源
     *
     * @param name 知识点名称
     * @return 查找到的知识点资源
     */
    List<Resource> getResoources(String name);

    /**
     * 修改知识点名称
     *
     * @param name   要修改的知识点名称
     * @param KPname 修改后的知识点名称
     */
    void updateKnowledgePointname(String name, String KPname);

    /**
     * 修改知识点简介
     *
     * @param KPname     要修改的知识点名称
     * @param KPsatement 修改后的知识点简介
     */
    void updateKnowledgePointStatment(String KPname, String KPsatement);

    /**
     * 修改知识点内容
     *
     * @param KPname    要修改的知识点名称
     * @param KPcontent 修改的知识点名称
     */

    void updateKnowledgePointContent(String KPname, String KPcontent);

    /**
     * 修改知识点标签
     *
     * @param KPname 要修改的知识点名称
     * @param set    修改后的知识点标签
     */
    void updateKnowledgePointTag(String KPname, Set<Tag> set);

    /**
     * 修改知识点重要程度
     *
     * @param name                     要修改的知识点名称
     * @param knowledgePointImportance 修改后的知识点重要程度
     */
    void updateKnowledgePointImportance(String name, KnowledgePointImportance knowledgePointImportance);

    /**
     * 修改知识点难度
     *
     * @param name                    要修改的知识点名称
     * @param knowledgePointDifficult 修改后的知识点难度
     */
    void updateKnowledgePointDiffcult(String name, KnowledgePointDifficult knowledgePointDifficult);

    /**
     * 保存知识点
     *
     * @param knowledgePoint 要保存的知识点
     */
    void saveKnowledgePoint(KnowledgePoint knowledgePoint);

    /**
     * 根据条件查找知识点 (分頁)
     *
     * @param start      起始索引
     * @param rows       获取的行数
     * @param conditions 查询条件
     * @return
     */
    List<KnowledgePoint> getKnowledgePointPageByCondition(int start, int rows, List<BaseHibernateDAO.Condition> conditions);

    /**
     * 获取根据条件查找到的数据条数
     *
     * @param conditions 查询条件
     * @return
     */
    long getKnowledgePointCountByCondition(List<BaseHibernateDAO.Condition> conditions);

    /**
     * 删除知识点
     *
     * @param knowledgePoint
     */
    void deleteKnowledgePoint(KnowledgePoint knowledgePoint);

    List<KnowledgePoint> getKnowlegePointBySubject(Subject subject);

    /**
     * 根据AddKnowledgePointInfoVO设置知识点属性
     * @param knowledgePoint
     * @param addKnowledgePointInfoVO
     */
    void setKnowledgePointByAddKnowledgePointInfoVO(KnowledgePoint knowledgePoint,AddKnowledgePointInfoVO addKnowledgePointInfoVO);

    /**
     * 通过名称和Tag 查找知识点
     * @param name
     * @return
     */
    List<KnowledgePoint>  getKnowledgePointsByTagOrName(String name);
}
