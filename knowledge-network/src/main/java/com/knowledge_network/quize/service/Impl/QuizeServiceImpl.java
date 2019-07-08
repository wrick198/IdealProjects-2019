package com.knowledge_network.quize.service.Impl;

import com.knowledge_network.auth.user.KnowledgeNetworkUserDetails;
import com.knowledge_network.auth.user.LoginUserHolder;
import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.knowledge_network.entity.Subject;
import com.knowledge_network.knowledge_network.entity.Tag;
import com.knowledge_network.knowledge_network.service.KnowledgePointService;
import com.knowledge_network.knowledge_network.service.SubjectService;
import com.knowledge_network.knowledge_network.service.TagService;
import com.knowledge_network.knowledge_network.vo.KnowledgePointInfoVO;
import com.knowledge_network.knowledge_network.vo.TagInfoVO;
import com.knowledge_network.quize.dao.QuizeDAO;
import com.knowledge_network.quize.entity.Difficulty;
import com.knowledge_network.quize.entity.QuestionType;
import com.knowledge_network.quize.entity.Quize;
import com.knowledge_network.quize.service.DifficultyService;
import com.knowledge_network.quize.service.QuestionTypeService;
import com.knowledge_network.quize.service.QuizeService;
import com.knowledge_network.quize.vo.QuizeInfoVO;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.exceptions.KnowledgePointNotFoundException;
import com.knowledge_network.support.exceptions.KnowledgePointNotMatchException;
import com.knowledge_network.support.exceptions.UserNotFoundException;
import com.knowledge_network.support.exceptions.UserNotMatchException;
import com.knowledge_network.support.utils.Asserts;
import com.knowledge_network.support.utils.DateUtils;
import com.knowledge_network.support.utils.EditUtils;
import com.knowledge_network.user.entity.Teacher;
import com.knowledge_network.user.entity.User;
import com.knowledge_network.user.service.TeacherService;
import com.knowledge_network.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ** Created by gongjiangtao on 2018/4/16
 * QuizeService接口的实现
 **/
@Service
public class QuizeServiceImpl implements QuizeService {
    @Autowired
    private QuizeDAO quizeDAO;
    @Autowired
    private UserService userService;
    @Autowired
    private KnowledgePointService knowledgePointService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private QuestionTypeService questionTypeService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private DifficultyService difficultyService;
    @Autowired
    private TagService tagService;

    /**
     * 检查是否是一个合法的测试题
     *
     * @param quize
     * @return
     */
    @Override
    public boolean checkLegalQuize(Quize quize) {
        // TODO:完善检查
        return true;
    }

    /**
     * 通过Id获取试题
     *
     * @param id 待查id
     * @return
     */
    @Override
    public Quize getQuizeById(int id) {
        Asserts.notNull(id, ResponseErrorEnum.QUIZE_ID_NOT_NULL);
        return quizeDAO.findQuizeById(id);
    }

    /**
     * 通过名称获取试题
     *
     * @param name 待查的name
     * @return
     */
    @Override
    public List<Quize> getQuizesByName(String name) {
        Asserts.hasText(name, ResponseErrorEnum.QUIZE_NAME_NOT_NULL);
        return quizeDAO.findQuizesByName(name);
    }

    /**
     * 通过创建时间获取试题
     *
     * @param createTime 待查的createTime
     * @return
     */
    @Override
    public List<Quize> getQuizesByCreateTime(Date createTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(format.format(createTime));
            return quizeDAO.findQuizesByCreateTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取可用的试题
     *
     * @return
     */
    @Override
    public List<Quize> getAvailabelQuizes() {
        return quizeDAO.findAvailableQuizes();
    }

    /**
     * 通过出题人获取试题
     *
     * @param creator 待查的出题人
     * @return
     */
    @Override
    public List<Quize> getQuizesByCreator(Teacher creator) {
        Asserts.notNull(creator, ResponseErrorEnum.CREATOR_NOT_NULL);
        User user = userService.getUserById(creator.getId());
        String userId = user.getId() + "";
        if (user == null) throw new UserNotFoundException(userId);
        if (!user.equals(creator)) throw new UserNotMatchException(userId);
        return quizeDAO.findQuizesByCreator(creator);
    }

    /**
     * 通过难度获取试题
     *
     * @param difficulty 待查的难度
     * @return
     */
    @Override
    public List<Quize> getQuizesByDifficulty(Difficulty difficulty) {
        // TODO:检查difficulty是否合法
        return quizeDAO.findQuizesByDifficulty(difficulty);
    }

    /**
     * 通过问题类型获取试题
     *
     * @param questionType 待查的questionType
     * @return
     */
    @Override
    public List<Quize> getQuizesByQuestionType(QuestionType questionType) {
        // TODO:检查questionType是否合法
        return quizeDAO.findQuizesByQuestionType(questionType);
    }

    /**
     * 通过分值获取试题
     *
     * @param examingPoint 待查的分值
     * @return
     */
    @Override
    public List<Quize> getQuizesByExamingPoint(float examingPoint) {
        Asserts.notNull(examingPoint, ResponseErrorEnum.EXAMING_POINT_NOT_NULL);
        return quizeDAO.findQuizesByExamingPoint(examingPoint);
    }

    /**
     * 通过标签获取试题
     *
     * @param tag 待查的标签
     * @return
     */
    @Override
    public List<Quize> getQuizesByTag(Tag tag) {
        Asserts.notNull(tag, ResponseErrorEnum.KEYWORD_NOT_NULL);
        return quizeDAO.findQuizesByTag(tag);
    }

    /**
     * 根据条件获取Quize
     *
     * @param conditions 条件列表
     * @return
     */
    @Override
    public List<Quize> getQuizesByConditions(List<BaseHibernateDAO.Condition> conditions) {
        Asserts.notNull(conditions, ResponseErrorEnum.CONDITION_NOT_NULL);
        return quizeDAO.findQuizeByConditions(conditions);
    }

    /**
     * 通过知识点获取试题
     *
     * @param knowledgePoint 待查的知识点
     * @return
     */
    @Override
    public List<Quize> getQuizesByKnowledgePoint(KnowledgePoint knowledgePoint) {
        Asserts.notNull(knowledgePoint, ResponseErrorEnum.KNOWLEDGEPOINT_EXIST);
        Integer id = knowledgePoint.getId();
        KnowledgePoint kp = knowledgePointService.getKnowledgePointByID(id);
        if (kp == null) throw new KnowledgePointNotFoundException(id);
        if (!kp.equals(knowledgePoint)) throw new KnowledgePointNotMatchException(id);
        return quizeDAO.findQuizesByKnowledgePoint(knowledgePoint);
    }

    /**
     * 通过科目获取试题
     *
     * @param subject 待查的科目
     * @return
     */
    @Override
    public List<Quize> getQuizesBySubject(Subject subject) {
        Asserts.notNull(subject, ResponseErrorEnum.SUBJECT_ID_NOT_NULL);
        Subject sj = subjectService.getSubjectById(subject.getId());
        Asserts.notNull(sj, ResponseErrorEnum.SUBJECT_NOT_FOUND);

        return quizeDAO.findQuizesBySubject(subject);
    }

    @Override
    public boolean updateQuizeByQuizeInfoVO(Integer quizeId, QuizeInfoVO quizeInfoVO) {
        Asserts.notNull(quizeId, ResponseErrorEnum.QUIZE_ID_NOT_NULL);
        Asserts.notNull(quizeInfoVO, ResponseErrorEnum.QUIZE_NOT_NULL);
        Quize quize = getQuizeById(quizeId);
//        if (quize != null) {
//            if (quizeInfoVO.getName() != null) {
//                quize.setQuizeName(quizeInfoVO.getName());
//            }
//            if (quizeInfoVO.getContent() != null) {
//                quize.setContent(quizeInfoVO.getContent());
//            }
//            if (quizeInfoVO.getAnswer() != null) {
//                quize.setAnswer(quizeInfoVO.getAnswer());
//            }
//            if (quizeInfoVO.getAnalysis() != null) {
//                quize.setAnalysis(quizeInfoVO.getAnalysis());
//            }
//            if (quizeInfoVO.getUrl() != null) {
//                quize.setUrl(quizeInfoVO.getUrl());
//            }
//            if (quizeInfoVO.getExamingPoint() != null) {
//                quize.setExamingPoint(quizeInfoVO.getExamingPoint());
//            }
//            if (quizeInfoVO.getCreator() != null) {
//                quize.setCreator(teacherService.getTeacherById(quizeInfoVO.getCreator().getId()));
//            }
//            if (quizeInfoVO.getSubject() != null) {
//                quize.setSubject(subjectService.getSubjectById(quizeInfoVO.getSubject().getId()));
//            }
//            if (quizeInfoVO.getQuestionType() != null) {
//                quize.setQuestionType(
//                        questionTypeService.getQuestionTypeByType(quizeInfoVO.getQuestionType().getType()));
//            }
//            if (quizeInfoVO.getDifficulty() != null) {
//                quize.setDifficulty(
//                        difficultyService.getDifficultyByDifficultyLevel(
//                                quizeInfoVO.getDifficulty().getDifficultyLevel()));
//            }
//            if (quizeInfoVO.getKnowledgePoints() != null) {
//                Collection<KnowledgePoint> knowledgePoints = new ArrayList<>();
//                for (KnowledgePointInfoVO kpVO : quizeInfoVO.getKnowledgePoints()) {
//                    knowledgePoints.add(knowledgePointService.getKnowledgePointByID(kpVO.getId()));
//                }
//                quize.setKnowledgePoints(knowledgePoints);
//            }
//            if (quizeInfoVO.getTags() != null) {
//                Collection<Tag> tags = new ArrayList<>();
//                for (TagInfoVO tagInfoVO : quizeInfoVO.getTags()) {
//                    tags.add(tagService.getTagById(tagInfoVO.getId()));
//                }
//            }
//            if (checkLegalQuize(quize)) {
//                updateQuize(quize);
//                return true;
//            }
//        }
        try {
            if (quize != null) {
                Quize quizeByInfoVO = createQuizeByInfoVO(quizeInfoVO);
                quize = (Quize) EditUtils.findDifferenceAndModify(quize, quizeByInfoVO);
                if (quize != null) {
                    createQuize(quize);
                    return true;
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 做题次数加一
     *
     * @param quize 待更新的quize对象
     */
    @Override
    public void updateQuizeExposeTimesPlusOne(Quize quize) {
        Asserts.notNull(quize, ResponseErrorEnum.QUIZE_NOT_NULL);
        quize.setExposeTimes(quize.getExposeTimes() + 1);
        updateQuize(quize);
    }

    /**
     * 做对次数加一
     *
     * @param quize 待更新的quize对象
     */
    @Override
    public void updateQuizeRightTimesPlusOne(Quize quize) {
        Asserts.notNull(quize, ResponseErrorEnum.QUIZE_NOT_NULL);
        quize.setRightTimes(quize.getRightTimes() + 1);
        updateQuize(quize);
    }

    /**
     * 做错次数加一
     *
     * @param quize 待更新的quize对象
     */
    @Override
    public void updateQuizeWrongTimesPlusOne(Quize quize) {
        Asserts.notNull(quize, ResponseErrorEnum.QUIZE_NOT_NULL);
        quize.setWrongTimes(quize.getWrongTimes() + 1);
        updateQuize(quize);
    }

    @Override
    public void updateQuize(Quize quize) {
        try {
            Date lastModify = DateUtils.getCurrentDay();
            quize.setLastModify(lastModify);
        } catch (ParseException e) {

        } finally {
            quizeDAO.updateQuize(quize);
        }
    }

    /**
     * 写入quize进数据库
     *
     * @param quize 待写入的quize
     */
    @Override
    public boolean createQuize(Quize quize) {
        Asserts.notNull(quize, ResponseErrorEnum.QUIZE_NOT_NULL);
        if (checkLegalQuize(quize)) {
            updateQuize(quize);
            return true;
        }
        return false;
    }

    /**
     * 获取所有Quize
     *
     * @return
     */
    @Override
    public List<Quize> getAllQuize() {
        return quizeDAO.findAll();
    }

    /**
     * 根据Quize生成VO
     *
     * @param quizes
     * @return
     */
    @Override
    public List<QuizeInfoVO> generateVOs(List<Quize> quizes) {
        if (!quizes.isEmpty()) {
            List<QuizeInfoVO> quizeInfoVOS = new ArrayList<>();
            for (Quize q : quizes) {
                quizeInfoVOS.add(new QuizeInfoVO(q));
            }
            return quizeInfoVOS;
        }
        return null;
    }

    /**
     * 根据条件生成随机Quize
     *
     * @param count      数量
     * @param conditions 条件
     * @return
     */
    @Override
    public List<Quize> getRandomQuizeByCondition(Integer count, List<BaseHibernateDAO.Condition> conditions) {
        List<Quize> quizes = getQuizesByConditions(conditions);
        List<Quize> randomQuizes = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int p = random.nextInt();
            randomQuizes.add(quizes.get(p));
        }
        return randomQuizes;
    }

    /**
     * 删除试题
     *
     * @param quize 待删除的试题
     */
    @Override
    public void deleteQuize(Quize quize) {
        Asserts.notNull(quize, ResponseErrorEnum.QUIZE_NOT_NULL);
        quizeDAO.deleteQuize(quize);
    }

    @Override
    public Quize createQuizeByInfoVO(QuizeInfoVO quizeInfoVO) {
        // 获取当前登录用户
        // TODO:检查用户是否是教师
        LoginUserHolder loginUserHolder = LoginUserHolder.getInstance();
        KnowledgeNetworkUserDetails userDetails = loginUserHolder.getCurrentLoginUser();
        Teacher creator = teacherService.getTeacherById(userDetails.getUserId());
        if (creator == null) return null;

        // 创建Quize
        Quize quize = new Quize();
        quize.setQuizeName(quizeInfoVO.getName());
        quize.setUrl(quizeInfoVO.getUrl());
        quize.setContent(quizeInfoVO.getContent());
        quize.setAnalysis(quizeInfoVO.getAnalysis());
        quize.setCreateTime(quizeInfoVO.getCreateTime());
        quize.setExposeTimes(0);
        quize.setRightTimes(0);
        quize.setWrongTimes(0);
        quize.setExamingPoint(0);

        quize.setQuestionType(
                questionTypeService.getQuestionTypeByType(quizeInfoVO.getQuestionType().getType()));
        quize.setDifficulty(
                difficultyService.getDifficultyByDifficultyLevel(
                        quizeInfoVO.getDifficulty().getDifficultyLevel()));
        quize.setSubject(subjectService.getSubjectById(quizeInfoVO.getSubject().getId()));
        quize.setCreator(creator);

        Collection<KnowledgePoint> knowledgePointList = quize.getKnowledgePoints();
        for (KnowledgePointInfoVO kpVO : quizeInfoVO.getKnowledgePoints()) {
            Integer id = kpVO.getId();
            knowledgePointList.add(knowledgePointService.getKnowledgePointByID(id));
        }
        return quize;
    }
}
