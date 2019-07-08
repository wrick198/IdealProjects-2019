package com.knowledge_network.quize.controller;

import com.knowledge_network.knowledge_network.entity.Subject;
import com.knowledge_network.knowledge_network.service.KnowledgePointService;
import com.knowledge_network.knowledge_network.service.SubjectService;
import com.knowledge_network.quize.entity.Difficulty;
import com.knowledge_network.quize.entity.HasDoneQuize;
import com.knowledge_network.quize.entity.QuestionType;
import com.knowledge_network.quize.entity.Quize;
import com.knowledge_network.quize.service.*;
import com.knowledge_network.quize.vo.AnswerVO;
import com.knowledge_network.quize.vo.QuizeInfoVO;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.support.utils.Asserts;
import com.knowledge_network.support.utils.VOUtils;
import com.knowledge_network.user.entity.Student;
import com.knowledge_network.user.entity.Teacher;
import com.knowledge_network.user.entity.User;
import com.knowledge_network.user.service.StudentService;
import com.knowledge_network.user.service.TeacherService;
import com.knowledge_network.user.service.UserService;
import com.knowledge_network.user.vo.web.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/4/24
 * Quize控制器
 **/
@RestController
@RequestMapping("/quize")
public class QuizeController {
    @Autowired
    QuizeService quizeService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    QuestionTypeService questionTypeService;
    @Autowired
    DifficultyService difficultyService;
    @Autowired
    SubjectService subjectService;
    @Autowired
    KnowledgePointService knowledgePointService;
    @Autowired
    UserService userService;
    @Autowired
    WrongNoteService wrongNoteService;
    @Autowired
    StudentService studentService;
    @Autowired
    HasDoneQuizeService hasDoneQuizeService;

    /**
     * 获取id为quizeId的Quize信息
     *
     * @param quizeId 待获取的quizeId
     * @return
     */
    @RequestMapping(value = "/{quizeId}", method = RequestMethod.GET)
    ResponseResult<QuizeInfoVO> userGetQuizeInfo(@PathVariable String quizeId) {
        Integer id = Integer.parseInt(quizeId);
        Quize quize = quizeService.getQuizeById(id);
        if (quize == null || quize.getIsAvailable() == 0) {
            return ResponseResult.newErrorInstance(ResponseErrorEnum.QUIZE_NOT_FOUND, null);
        } else {
            QuizeInfoVO quizeInfoVO = new QuizeInfoVO(quize);
            return ResponseResult.newSucceedInstance("success", quizeInfoVO);
        }
    }

    /**
     * 添加一个测试题
     *
     * @param quizeInfoVO
     * @return
     */
    @RequestMapping(value = "/addQuize", method = RequestMethod.POST)
    ResponseResult<String> addQuize(@RequestBody QuizeInfoVO quizeInfoVO) {
        Asserts.notNull(quizeInfoVO, ResponseErrorEnum.QUIZE_NOT_NULL);
        Quize quize = quizeService.createQuizeByInfoVO(quizeInfoVO);
        if (quize != null) {
            quizeService.createQuize(quize);
            return ResponseResult.newSucceedInstance("Successfully create quize" + quize.getId(), null);
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.QUIZE_NOT_NULL, null);
    }

    /**
     * 编辑测试题
     *
     * @param quizeId
     * @param quizeInfoVO
     * @return
     */
    @RequestMapping(value = "/edit/{quizeId}", method = RequestMethod.POST)
    ResponseResult<String> editQuize(@PathVariable String quizeId, @RequestBody QuizeInfoVO quizeInfoVO) {
        Asserts.notNull(quizeId, ResponseErrorEnum.QUIZE_ID_NOT_NULL);
        Asserts.notNull(quizeInfoVO, ResponseErrorEnum.QUIZE_NOT_NULL);
        if (quizeService.updateQuizeByQuizeInfoVO(Integer.parseInt(quizeId), quizeInfoVO)) {
            return ResponseResult.newSucceedInstance("Successfully Update Quize " + quizeId, null);
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.QUIZE_NOT_FOUND, null);
    }

    /**
     * 切换试题是否可用
     *
     * @param quizeId
     * @return
     */
    @RequestMapping(value = "/toggleAvaliable/{quizeId}", method = RequestMethod.POST)
    ResponseResult<String> toggleAvaliable(@PathVariable String quizeId) {
        Asserts.notNull(quizeId, ResponseErrorEnum.QUIZE_ID_NOT_NULL);
        Integer id = Integer.parseInt(quizeId);
        Quize quize = quizeService.getQuizeById(id);
        if (quize != null) {
            String msg = "";
            if (quize.getIsAvailable() == 0) {
                quize.setIsAvailable(1);
                msg = "successfully enable quize " + quizeId;
            } else {
                quize.setIsAvailable(0);
                msg = "successfully disable quize" + quizeId;
            }
            return ResponseResult.newSucceedInstance(msg, null);
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.QUIZE_NOT_FOUND, null);
    }

    /**
     * 做试题逻辑，前端显示题目信息并传回用户的答案，
     * 后端检查答案是否正确
     *
     * @param quizeId
     * @param answerVO 答案分页
     * @return
     */
    @RequestMapping(value = "/checkAnswer/{quizeId}", method = RequestMethod.POST)
    ResponseResult<String> checkAnswer(@PathVariable String quizeId, AnswerVO answerVO) {
        Asserts.notNull(quizeId, ResponseErrorEnum.QUIZE_ID_NOT_NULL);
        Asserts.notNull(answerVO, ResponseErrorEnum.ANSWER_NOT_NULL);
        Integer id = Integer.parseInt(quizeId);
        Quize quize = quizeService.getQuizeById(id);
        Student student = studentService.getStudentById(answerVO.getStudentId());

        if (quize != null) {
            //查询做题记录
            List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
            conditions.add(new BaseHibernateDAO.Condition(BaseHibernateDAO.ConditionType.EQ, "student", student));
            conditions.add(new BaseHibernateDAO.Condition(BaseHibernateDAO.ConditionType.EQ, "quize", quize));
            HasDoneQuize hasDoneQuize = hasDoneQuizeService.getHasDoneQuizeByConditions(conditions);

            // 若此学生从来没做过这题
            if (hasDoneQuize == null) {
                hasDoneQuize = new HasDoneQuize(student, quize);
                hasDoneQuizeService.addHasDoneQuizeRecord(hasDoneQuize);
            }

            quizeService.updateQuizeExposeTimesPlusOne(quize);
            hasDoneQuizeService.updatePracticeTimePlusOne(hasDoneQuize);
            if (quize.getAnswer().equals(answerVO.getAnswer())) {
                quizeService.updateQuizeRightTimesPlusOne(quize);
                hasDoneQuizeService.updateRightTimePlusOne(hasDoneQuize);
                return ResponseResult.newSucceedInstance("Right Answer", null);
            } else {
                quizeService.updateQuizeWrongTimesPlusOne(quize);
                hasDoneQuizeService.updateWrongTimePlusOne(hasDoneQuize);
                //加入错题集
                wrongNoteService.addQuizeToStudentSubject(quize, student, quize.getSubject());

                return ResponseResult.newSucceedInstance("Wrong Answer", null);
            }
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.QUIZE_NOT_FOUND, null);
    }

    @RequestMapping(value = "/allQuize", method = RequestMethod.GET)
    ResponseResult<List<QuizeInfoVO>> allQuize() {
        List<Quize> allQuize = new ArrayList<>();
        allQuize = quizeService.getAllQuize();
        List<QuizeInfoVO> quizes = quizeService.generateVOs(allQuize);
        if (quizes != null)
            return ResponseResult.newSucceedInstance(null, quizes);
        return ResponseResult.newErrorInstance(ResponseErrorEnum.QUIZE_NOT_FOUND, null);
    }

    /**
     * 通过科目搜索试题
     *
     * @param subjectName
     * @return
     */
    @RequestMapping(value = "/search/{subjectName}", method = RequestMethod.GET)
    ResponseResult<List<QuizeInfoVO>> searchQuizeBySubject(@PathVariable String subjectName) {
        Subject subject = subjectService.getSubjectByName(subjectName);
        Asserts.notNull(subject, ResponseErrorEnum.SUBJECT_ID_NOT_NULL);
        List<Quize> quizeBySubject = new ArrayList<>();
        quizeBySubject = quizeService.getQuizesBySubject(subject);
        List<QuizeInfoVO> quizes = quizeService.generateVOs(quizeBySubject);
        if (quizes != null)
            return ResponseResult.newSucceedInstance(null, quizes);
        return ResponseResult.newErrorInstance(ResponseErrorEnum.QUIZE_NOT_FOUND, null);
    }

    /**
     * 通过问题类型搜索试题
     *
     * @param questionTypeName
     * @return
     */
    @RequestMapping(value = "/search/{questionTypeName}", method = RequestMethod.GET)
    ResponseResult<List<QuizeInfoVO>> searchQuizeByQuestionName(@PathVariable String questionTypeName) {
        QuestionType questionType = questionTypeService.getQuestionTypeByName(questionTypeName);
        Asserts.notNull(questionType, ResponseErrorEnum.QUESTION_TYPE_NOT_NULL);
        List<Quize> quizeByQuestionType = new ArrayList<>();
        quizeByQuestionType = quizeService.getQuizesByQuestionType(questionType);
        List<QuizeInfoVO> quizes = quizeService.generateVOs(quizeByQuestionType);
        if (quizes != null)
            return ResponseResult.newSucceedInstance(null, quizes);
        return ResponseResult.newErrorInstance(ResponseErrorEnum.QUIZE_NOT_FOUND, null);
    }

    /**
     * 通过难度搜索试题
     *
     * @param difficultyName
     * @return
     */
    @RequestMapping(value = "/search/{difficultyName}", method = RequestMethod.GET)
    ResponseResult<List<QuizeInfoVO>> searchQuizeByDifficultyName(@PathVariable String difficultyName) {
        Difficulty difficulty = difficultyService.getDifficultyByName(difficultyName);
        Asserts.notNull(difficulty, ResponseErrorEnum.DIFFICULTY_NOT_NULL);
        List<Quize> quizeByDifficulty = quizeService.getQuizesByDifficulty(difficulty);
        List<QuizeInfoVO> quizes = quizeService.generateVOs(quizeByDifficulty);
        if (quizes != null)
            return ResponseResult.newSucceedInstance(null, quizes);
        return ResponseResult.newErrorInstance(
                ResponseErrorEnum.QUIZE_NOT_FOUND, null);
    }

    /**
     * 通过创建者搜索试题
     *
     * @param creatorName
     * @return
     */
    @RequestMapping(value = "search/{creatorName}", method = RequestMethod.GET)
    ResponseResult<List<QuizeInfoVO>> searchQuizeByCreatorName(@PathVariable String creatorName) {
        User user = userService.getUserByUsername(creatorName);
        Asserts.notNull(user, ResponseErrorEnum.USER_NOT_FOUND);
        Teacher creator = teacherService.getTeacherById(user.getId());
        List<Quize> quizeByCreator = quizeService.getQuizesByCreator(creator);
        List<QuizeInfoVO> quizes = quizeService.generateVOs(quizeByCreator);
        if (quizes != null) {
            return ResponseResult.newSucceedInstance(null, quizes);
        }
        return ResponseResult.newErrorInstance(
                ResponseErrorEnum.QUIZE_NOT_FOUND, null);
    }

    /**
     * 获取随机个数的试题
     * 试题个数为pageVO.rows
     *
     * @param pageVO
     * @return
     */
    @RequestMapping(value = "/random", method = RequestMethod.POST)
    ResponseResult<List<QuizeInfoVO>> getRandomQuizeByConditions(@RequestBody PageVO pageVO) {
        Asserts.notNull(pageVO, ResponseErrorEnum.BAD_REQUEST);
        Integer count = pageVO.getRows();
        List<BaseHibernateDAO.Condition> conditions = VOUtils.conditionVOs2Conditions(pageVO.getConditions());
        List<Quize> random = quizeService.getRandomQuizeByCondition(count, conditions);
        List<QuizeInfoVO> quizes = quizeService.generateVOs(random);
        if (random != null)
            return ResponseResult.newSucceedInstance(null, quizes);
        return ResponseResult.newErrorInstance(ResponseErrorEnum.QUIZE_NOT_FOUND, quizes);
    }

    /**
     * 删除试题
     *
     * @param quizeId
     * @return
     */
    @RequestMapping(value = "/delete/{quizeId}", method = RequestMethod.DELETE)
    ResponseResult<String> deleteQuize(@PathVariable String quizeId) {
        Asserts.notNull(quizeId, ResponseErrorEnum.QUIZE_ID_NOT_NULL);
        Integer id = Integer.parseInt(quizeId);
        Quize quize = quizeService.getQuizeById(id);
        if (quize != null) {
            quizeService.deleteQuize(quize);
            return ResponseResult.newSucceedInstance("Successfully Deleted Quize " + quizeId, null);
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.QUIZE_NOT_FOUND, null);
    }
}
