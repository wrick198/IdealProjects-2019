package com.knowledge_network.quize.service;

import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.knowledge_network.entity.Resource;
import com.knowledge_network.quize.entity.Paper;
import com.knowledge_network.quize.entity.PaperType;
import com.knowledge_network.quize.entity.Quize;
import com.knowledge_network.quize.vo.PaperInfoVO;
import com.knowledge_network.user.entity.Teacher;

import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/4/17
 * Peper的Service接口
 **/
public interface PaperService {
    /**
     * 根据id获取Paper
     *
     * @param id id
     * @return id对应的Paper
     */
    Paper getPaperById(int id);

    /**
     * 根据名称获取Paper
     *
     * @param name paper的名称，不能为null
     * @return name对应的Paper
     */
    List<Paper> getPapersByName(String name);

    /**
     * 根据类型获取Paper
     *
     * @param paperType 试卷类型
     * @return paperType对应的Paper
     */
    List<Paper> getPapersByPaperType(PaperType paperType);

    /**
     * 根据知识点获取Paper
     *
     * @param knowledgePoint 知识点
     * @return 知识点对应恩Paper
     */
    List<Paper> getPapersByKnowledgePoint(KnowledgePoint knowledgePoint);

    /**
     * 根据Quize获取Paper
     *
     * @param quize 试题
     * @return 试题出现过的Paper
     */
    List<Paper> getPapersByQuize(Quize quize);

    /**
     * 根据出题人获取Paper
     *
     * @param teacher 出题教师
     * @return 出题教师出的Paper
     */
    List<Paper> getPapersByCreator(Teacher teacher);

    /**
     * 根据资源类型获取Paper
     *
     * @param resource 资源
     * @return resource对应的Paper
     */
    List<Paper> getPapersByResource(Resource resource);

    /**
     * 更新Paper的名称
     *
     * @param paper 要更新的paper对象
     * @param name  新的名称
     */
    void updatePaperName(Paper paper, String name);

    /**
     * 更新Paper的内容
     *
     * @param paper   要更新的paper对象
     * @param content 新的内容
     */
    void updatePaperContent(Paper paper, String content);

    /**
     * 更新Paper的总分
     *
     * @param paper      要更新的paper对象
     * @param totalPoint 新的总分
     */
    void updatePaperTotalPoint(Paper paper, int totalPoint);

    /**
     * 更新合格分
     *
     * @param paper     要更新的paper对象
     * @param passPoint 新的合格分
     */
    void updatePaperPassPoint(Paper paper, int passPoint);

    /**
     * 切换免费、收费
     *
     * @param paper 要更新的paper对象
     * @param free  0为免费，其他数值为价格
     */
    void togglePaperFree(Paper paper, int free);

    /**
     * 更新总结
     *
     * @param paper   要更新的paper对象
     * @param summary 新的总结
     */
    void updatePaperSummary(Paper paper, String summary);

    /**
     * 更新答案
     *
     * @param paper  要更新的paper对象
     * @param answer 新的答案
     */
    void updatePaperAnswer(Paper paper, String answer);

    /**
     * 通过页面信息更新试卷
     *
     * @param paperId
     * @param paperInfoVO
     * @return
     */
    boolean updatePaperByInfoVO(Integer paperId, PaperInfoVO paperInfoVO);

    /**
     * 删除Paper
     *
     * @param paper 要删除的paper对象
     */
    void deletePaper(Paper paper);

    /**
     * 通过页面信息创建Paper，不会把生成的paper写入数据库
     *
     * @param paperInfoVO
     * @return
     */
    Paper createPaperByInfoVO(PaperInfoVO paperInfoVO);

    /**
     * 写入修改进数据库
     *
     * @param paper
     */
    void updateOrCreatePaper(Paper paper);
}
