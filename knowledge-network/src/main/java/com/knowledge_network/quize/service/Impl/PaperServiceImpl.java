package com.knowledge_network.quize.service.Impl;

import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.knowledge_network.entity.Resource;
import com.knowledge_network.knowledge_network.service.KnowledgePointService;
import com.knowledge_network.quize.dao.PaperDAO;
import com.knowledge_network.quize.entity.Paper;
import com.knowledge_network.quize.entity.PaperType;
import com.knowledge_network.quize.entity.Quize;
import com.knowledge_network.quize.service.PaperService;
import com.knowledge_network.quize.service.PaperTypeService;
import com.knowledge_network.quize.service.QuizeService;
import com.knowledge_network.quize.vo.PaperInfoVO;
import com.knowledge_network.quize.vo.QuizeInfoVO;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.exceptions.PaperNotFoundException;
import com.knowledge_network.support.exceptions.PaperTypeInvalidException;
import com.knowledge_network.support.utils.Asserts;
import com.knowledge_network.support.utils.EditUtils;
import com.knowledge_network.user.entity.Teacher;
import com.knowledge_network.user.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/4/18
 * PaperService接口的实现
 **/
@Service
public class PaperServiceImpl implements PaperService {
    @Autowired
    private PaperDAO paperDAO;
    @Autowired
    private PaperTypeService paperTypeService;
    @Autowired
    private KnowledgePointService knowledgePointService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private QuizeService quizeService;

    /**
     * 通过id获取Paper
     *
     * @param id id
     * @return
     */
    @Override
    public Paper getPaperById(int id) {
        Asserts.notNull(id, ResponseErrorEnum.PAPER_ID_NOT_NULL);
        return paperDAO.findPaperById(id);
    }

    /**
     * 通过出题教师获取Paper
     *
     * @param teacher 出题教师
     * @return
     */
    @Override
    public List<Paper> getPapersByCreator(Teacher teacher) {
        Asserts.notNull(teacher, ResponseErrorEnum.USER_ID_NOT_NULL);
        return paperDAO.findPapersByCreator(teacher);
    }

    /**
     * 通过名称获取Paper
     *
     * @param name paper的名称，不能为null
     * @return
     */
    @Override
    public List<Paper> getPapersByName(String name) {
        Asserts.notNull(name, ResponseErrorEnum.PAPER_NAME_NOT_NULL);
        return paperDAO.findPapersByName(name);
    }

    /**
     * 通过试卷类型获取Paper
     *
     * @param paperType 试卷类型
     * @return
     */
    @Override
    public List<Paper> getPapersByPaperType(PaperType paperType) {
        Asserts.notNull(paperType, ResponseErrorEnum.PAPER_TYPE_NOT_NULL);
        if (!paperTypeService.checkValidPaperType(paperType))
            throw new PaperTypeInvalidException(paperType.getId());
        return paperDAO.findPapersByPaperType(paperType);
    }

    /**
     * 通过测试题获取Paper
     *
     * @param quize 试题
     * @return
     */
    @Override
    public List<Paper> getPapersByQuize(Quize quize) {
        Asserts.notNull(quize, ResponseErrorEnum.QUIZE_NOT_NULL);
        return paperDAO.findPapersByQuize(quize);
    }

    /**
     * 通过知识点获取Paper
     *
     * @param knowledgePoint 知识点
     * @return
     */
    @Override
    public List<Paper> getPapersByKnowledgePoint(KnowledgePoint knowledgePoint) {
        Asserts.notNull(knowledgePoint, ResponseErrorEnum.KNOWLEDGEPOINT_NOT_FOUND);
        return paperDAO.findPapersByKnowledgePoint(knowledgePoint);
    }

    /**
     * 通过资源获取Paper
     *
     * @param resource 资源
     * @return
     */
    @Override
    public List<Paper> getPapersByResource(Resource resource) {
        Asserts.notNull(resource, ResponseErrorEnum.RESOURCE_NOT_NULL);
        return paperDAO.findPapersByResource(resource);
    }

    /**
     * 更新paper的答案
     *
     * @param paper  要更新的paper对象
     * @param answer 新的答案
     */
    @Override
    public void updatePaperAnswer(Paper paper, String answer) {
        Asserts.notNull(paper, ResponseErrorEnum.PAPER_NOT_NULL);
        Asserts.notNull(answer, ResponseErrorEnum.ANSWER_NOT_NULL);
        paper.setAnswer(answer);
        paperDAO.updatePaper(paper);
    }

    @Override
    public boolean updatePaperByInfoVO(Integer paperId, PaperInfoVO paperInfoVO) {
        Asserts.notNull(paperId, ResponseErrorEnum.PAPER_NOT_NULL);
        Asserts.notNull(paperInfoVO, ResponseErrorEnum.PAPER_NOT_NULL);
        Paper paper = getPaperById(paperId);
        if (paper != null) {
            try {

                Paper paperByInfo = createPaperByInfoVO(paperInfoVO);
                paper = (Paper) EditUtils.findDifferenceAndModify(paper, paperByInfo);
                if (paper != null) {
                    paperDAO.updatePaper(paper);
                    return true;
                }
//                利用反射机制修改属性，待测试
//                Field[] fields = paper.getClass().getDeclaredFields();
//                for (Field f : fields) {
//                    String name = f.getName();
//                    // 首字母大写
//                    name = name.substring(0, 1).toUpperCase() + name.substring(1);
//
//                    // 跳过id
//                    if (name == "Id") continue;
//
//                    //获取get方法
//                    Method paperMethod = paper.getClass().getMethod("get" + name);
//                    Method paperInfoMethod = paperByInfo.getClass().getMethod("get" + name);
//                    //若发现值不同
//                    if (!paperMethod.invoke(paper).equals(paperInfoMethod.invoke(paperByInfo))) {
//                        Method m = paper.getClass().getMethod("set" + name);
//                        m.invoke(paper, paperInfoMethod.invoke(paperByInfo));
//                    }
//                }
//                paperDAO.updatePaper(paper);
//                return true;
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 更新Paper的内容
     *
     * @param paper   要更新的paper对象
     * @param content 新的内容
     */
    @Override
    public void updatePaperContent(Paper paper, String content) {
        Asserts.notNull(paper, ResponseErrorEnum.PAPER_NOT_NULL);
        Asserts.notNull(content, ResponseErrorEnum.CONTENT_NOT_NULL);
        paper.setContent(content);
        paperDAO.updatePaper(paper);
    }

    /**
     * 更新paper的名称
     *
     * @param paper 要更新的paper对象
     * @param name  新的名称
     */
    @Override
    public void updatePaperName(Paper paper, String name) {
        Asserts.notNull(paper, ResponseErrorEnum.PAPER_NOT_NULL);
        Asserts.notNull(name, ResponseErrorEnum.PAPER_NAME_NOT_NULL);
        paper.setName(name);
        paperDAO.updatePaper(paper);
    }

    /**
     * 更新paper的合格分
     *
     * @param paper     要更新的paper对象
     * @param passPoint 新的合格分
     */
    @Override
    public void updatePaperPassPoint(Paper paper, int passPoint) {
        Asserts.notNull(paper, ResponseErrorEnum.PAPER_NOT_NULL);
        Asserts.notNull(passPoint, ResponseErrorEnum.PASSPOINT_NOT_NULL);
        paper.setPassPoint(passPoint);
        paperDAO.updatePaper(paper);
    }

    /**
     * 更新paper的总结
     *
     * @param paper   要更新的paper对象
     * @param summary 新的总结
     */
    @Override
    public void updatePaperSummary(Paper paper, String summary) {
        Asserts.notNull(paper, ResponseErrorEnum.PAPER_NOT_NULL);
        paper.setSummary(summary);
        paperDAO.updatePaper(paper);
    }

    /**
     * 更新paper的总分
     *
     * @param paper      要更新的paper对象
     * @param totalPoint 新的总分
     */
    @Override
    public void updatePaperTotalPoint(Paper paper, int totalPoint) {
        Asserts.notNull(paper, ResponseErrorEnum.PAPER_NOT_NULL);
        Asserts.notNull(paper, ResponseErrorEnum.TOTALPOINT_NOT_NULL);
        Asserts.isLargerEqualThan(totalPoint, paper.getPassPoint(),
                ResponseErrorEnum.TOTAL_POINT_NOT_LARGER_THAN_PASS_POINT);
        paper.setTotalPoint(totalPoint);
        paperDAO.updatePaper(paper);
    }

    /**
     * 更新paper的价格
     *
     * @param paper 要更新的paper对象
     * @param free  0为免费，其他数值为价格
     */
    @Override
    public void togglePaperFree(Paper paper, int free) {
        Asserts.notNull(paper, ResponseErrorEnum.PAPER_NOT_NULL);
        paper.setStatus(free);
        paperDAO.updatePaper(paper);
    }

    /**
     * 删除paper
     *
     * @param paper 要删除的paper对象
     */
    @Override
    public void deletePaper(Paper paper) {
        Asserts.notNull(paper, ResponseErrorEnum.PAPER_NOT_NULL);
        Paper p = paperDAO.findPaperById(paper.getId());
        if (p == null) throw new PaperNotFoundException(paper.getId());
    }

    @Override
    public Paper createPaperByInfoVO(PaperInfoVO paperInfoVO) {
        Asserts.notNull(paperInfoVO, ResponseErrorEnum.PAPER_NOT_NULL);
        Asserts.notNull(paperInfoVO.getQuizes(), ResponseErrorEnum.QUIZE_NOT_NULL);
        Asserts.notNull(paperInfoVO.getKnowledgePoints(), ResponseErrorEnum.KNOWLEDGEPOINT_NOT_NULL);
        Asserts.notNull(paperInfoVO.getPaperType(), ResponseErrorEnum.PAPER_TYPE_NOT_NULL);
        Asserts.notNull(paperInfoVO.getCreatorNames(), ResponseErrorEnum.USER_ID_NOT_NULL);
        Paper paper = new Paper();
        paper.setName(paperInfoVO.getName());
        paper.setContent(paperInfoVO.getContent());
        paper.setTotalPoint(paperInfoVO.getTotalPoint());
        paper.setPassPoint(paperInfoVO.getPassPoint());
        paper.setCreateTime(paperInfoVO.getCreateTime());
        paper.setDuration(paperInfoVO.getDuration());
        paper.setPaperType(paperTypeService.getPaperTypesByName(paperInfoVO.getPaperType()));
        paper.setStatus(paperInfoVO.getStatus());

        List<KnowledgePoint> kps = new ArrayList<>();
        for (String kp : paperInfoVO.getKnowledgePoints())
            kps.add(knowledgePointService.getKnowledgePointByName(kp));
        paper.setKnowledgePoints(kps);

        List<Teacher> creators = new ArrayList<>();
        for (String tn : paperInfoVO.getCreatorNames())
            creators.add(teacherService.getTeacherByUserName(tn));
        paper.setCreators(creators);

        List<Quize> quizes = new ArrayList<>();
        for (QuizeInfoVO q : paperInfoVO.getQuizes())
            quizes.add(quizeService.getQuizeById(q.getId()));
        paper.setQuizes(quizes);

        //TODO: 为paper添加资源
        paper.setResources(null);
        paperDAO.updatePaper(paper);

        return paper;
    }

    @Override
    public void updateOrCreatePaper(Paper paper) {
        paperDAO.updatePaper(paper);
    }
}
