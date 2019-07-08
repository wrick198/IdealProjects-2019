package com.knowledge_network.quize.service;

import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.knowledge_network.entity.Subject;
import com.knowledge_network.knowledge_network.entity.Tag;
import com.knowledge_network.quize.entity.Difficulty;
import com.knowledge_network.quize.entity.QuestionType;
import com.knowledge_network.quize.entity.Quize;
import com.knowledge_network.quize.vo.QuizeInfoVO;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.Teacher;

import java.util.Date;
import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/4/16
 * Quize的Service接口
 **/
public interface QuizeService {
    /**
     * 通过id获取Quize
     *
     * @param id 待查id
     * @return id对应的Quize
     */
    Quize getQuizeById(int id);

    /**
     * 通过名称查找Quize
     *
     * @param name 待查的name
     * @return name对应的Quize
     */
    List<Quize> getQuizesByName(String name);

    /**
     * 通过问题类型查找Quize
     *
     * @param questionType 待查的questionType
     * @return questionType对应的Quize对象
     */
    List<Quize> getQuizesByQuestionType(QuestionType questionType);

    /**
     * 创建时间对应的Quize
     *
     * @param createTime 待查的createTime
     * @return createTime对应的Quize对象
     */
    List<Quize> getQuizesByCreateTime(Date createTime);

    /**
     * 获取可用的Quize
     * 即isAvailable属性为1的Quize
     *
     * @return
     */
    List<Quize> getAvailabelQuizes();

    /**
     * 通过难度等级difficulty获取Quizes
     *
     * @param difficulty 待查的难度
     * @return difficulty对应的Quize对象
     */
    List<Quize> getQuizesByDifficulty(Difficulty difficulty);

    /**
     * 通过分值查询Quize
     *
     * @param examingPoint 待查的分值
     * @return 分值对应的Quize对象
     */
    List<Quize> getQuizesByExamingPoint(float examingPoint);

    /**
     * 通过科目subject获取Quize
     *
     * @param subject 待查的科目
     * @return subject对应的Quize对象
     */
    List<Quize> getQuizesBySubject(Subject subject);

    /**
     * 通过知识点获取Quize
     *
     * @param knowledgePoint 待查的知识点
     * @return knowledgePoint对应的Quize
     */
    List<Quize> getQuizesByKnowledgePoint(KnowledgePoint knowledgePoint);

    /**
     * 通过出题人获取Quize
     *
     * @param creator 待查的出题人
     * @return creator对应的Quize对象
     */
    List<Quize> getQuizesByCreator(Teacher creator);

    /**
     * 通过标签获取Quize
     *
     * @param tag 待查的标签
     * @return tag对应的Quize对象
     */
    List<Quize> getQuizesByTag(Tag tag);

    /**
     * 根据条件获取Quize
     *
     * @param conditions 条件列表
     * @return 符合条件的Quize
     */
    List<Quize> getQuizesByConditions(List<BaseHibernateDAO.Condition> conditions);

    /**
     * 根据QuizeInfoVO修改quize
     *
     * @param quizeId     待修改的quize的id
     * @param quizeInfoVO 携带修改信息的quizeInfoVO
     * @return 是否成功更新
     */
    boolean updateQuizeByQuizeInfoVO(Integer quizeId, QuizeInfoVO quizeInfoVO);

    /**
     * 做题次数加一
     *
     * @param quize
     */
    void updateQuizeExposeTimesPlusOne(Quize quize);

    /**
     * quize做对次数加一
     *
     * @param quize 待更新的quize对象
     */
    void updateQuizeRightTimesPlusOne(Quize quize);

    /**
     * quize做错次数加一
     *
     * @param quize 待更新的quize对象
     */
    void updateQuizeWrongTimesPlusOne(Quize quize);

    /**
     * 写入数据库
     *
     * @param quize
     */
    void updateQuize(Quize quize);

    /**
     * 检查是否是合法的Quize
     *
     * @param quize
     * @return
     */
    boolean checkLegalQuize(Quize quize);

    /**
     * 新建Quize写入数据库
     *
     * @param quize 待写入的quize
     * @return 是否成功创建
     */
    boolean createQuize(Quize quize);

    /**
     * 获取所有Quize
     *
     * @return
     */
    List<Quize> getAllQuize();

    /**
     * 根据Quize列表生成VO列表
     *
     * @param quizes
     * @return
     */
    List<QuizeInfoVO> generateVOs(List<Quize> quizes);

    /**
     * 根据条件获取随机Quize
     *
     * @param count      数量
     * @param conditions 条件
     * @return
     */
    List<Quize> getRandomQuizeByCondition(Integer count, List<BaseHibernateDAO.Condition> conditions);

    /**
     * 删除试题
     *
     * @param quize 待删除的试题
     * @return
     */
    void deleteQuize(Quize quize);

    /**
     * 通过页面信息创建Quize
     *
     * @param quizeInfoVO 页面信息
     * @return 由页面信息生成的Quize对象
     */
    Quize createQuizeByInfoVO(QuizeInfoVO quizeInfoVO);
}
