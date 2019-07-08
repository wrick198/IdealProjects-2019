package com.knowledge_network.quize.controller;

import com.knowledge_network.quize.entity.AnswerSheet;
import com.knowledge_network.quize.service.AnswerSheetService;
import com.knowledge_network.quize.vo.AnswerSheetInfoVO;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.support.utils.Asserts;
import com.knowledge_network.support.utils.VOUtils;
import com.knowledge_network.user.vo.web.ConditionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/5/5
 * 答题卡控制器，供教师改试卷
 **/
@RestController
@RequestMapping("/answerSheet")
public class AnswerSheetController {
    @Autowired
    private AnswerSheetService answerSheetService;

    /**
     * 获取某一答题卡
     *
     * @param answerSheetId
     * @return
     */
    @RequestMapping(value = "/{answerSheetId}", method = RequestMethod.GET)
    ResponseResult<AnswerSheetInfoVO> getAnswerSheet(@PathVariable String answerSheetId) {
        Asserts.notNull(answerSheetId, ResponseErrorEnum.ANSWER_SHEET_ID_NOT_NULL);
        AnswerSheet answerSheet = answerSheetService.getAnswerSheetById(Integer.parseInt(answerSheetId));
        if (answerSheet != null) {
            AnswerSheetInfoVO infoVO = new AnswerSheetInfoVO(answerSheet);
            return ResponseResult.newSucceedInstance(null, infoVO);
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.ANSWER_SHEET_NOT_FOUND, null);
    }

    /**
     * 根据条件搜索答题卡（试卷，学生等）
     *
     * @param conditionVOS
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    ResponseResult<List<AnswerSheetInfoVO>> searchAnswerSheetByConditions(@RequestBody List<ConditionVO> conditionVOS) {
        Asserts.notNull(conditionVOS, ResponseErrorEnum.CONDITION_NOT_NULL);
        List<BaseHibernateDAO.Condition> conditions = VOUtils.conditionVOs2Conditions(conditionVOS);
        List<AnswerSheet> answerSheets = answerSheetService.getAnswerSheetByConditions(conditions);
        if (!answerSheets.isEmpty()) {
            List<AnswerSheetInfoVO> infoVOS = new ArrayList<>();
            for (AnswerSheet as : answerSheets)
                infoVOS.add(new AnswerSheetInfoVO(as));
            return ResponseResult.newSucceedInstance(null, infoVOS);
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.ANSWER_SHEET_NOT_FOUND, null);
    }

    /**
     * 统计答题卡得分
     *
     * @param answerSheetId
     * @return
     */
    @RequestMapping(value = "/countScore/{answerSheetId}", method = RequestMethod.GET)
    ResponseResult<Double> countScore(@PathVariable String answerSheetId) {
        Asserts.notNull(answerSheetId, ResponseErrorEnum.ANSWER_SHEET_ID_NOT_NULL);
        AnswerSheet answerSheet = answerSheetService.getAnswerSheetById(Integer.parseInt(answerSheetId));

        if (answerSheet != null) {
            Double score = answerSheetService.updateAnswerSheetScore(answerSheet);
            return ResponseResult.newSucceedInstance(null, score);
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.ANSWER_SHEET_NOT_FOUND, null);
    }
}
