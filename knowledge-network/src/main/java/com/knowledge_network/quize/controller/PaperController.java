package com.knowledge_network.quize.controller;

import com.knowledge_network.quize.entity.AnswerSheet;
import com.knowledge_network.quize.entity.Paper;
import com.knowledge_network.quize.entity.StudentAnswer;
import com.knowledge_network.quize.service.AnswerSheetService;
import com.knowledge_network.quize.service.PaperService;
import com.knowledge_network.quize.service.QuizeService;
import com.knowledge_network.quize.service.StudentAnswerService;
import com.knowledge_network.quize.vo.BeginDoPaperVO;
import com.knowledge_network.quize.vo.PaperInfoVO;
import com.knowledge_network.quize.vo.StudentAnswerInfoVO;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.support.utils.Asserts;
import com.knowledge_network.support.utils.DateUtils;
import com.knowledge_network.user.entity.Student;
import com.knowledge_network.user.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/5/3
 * 试卷控制器
 **/
@RestController
@RequestMapping("/paper")
public class PaperController {
    @Autowired
    private PaperService paperService;
    @Autowired
    private QuizeService quizeService;
    @Autowired
    private AnswerSheetService answerSheetService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentAnswerService studentAnswerService;

    /**
     * 教师创建试卷
     *
     * @param paperInfoVO
     * @return
     */
    @RequestMapping(value = "/creatPaper", method = RequestMethod.POST)
    ResponseResult<String> createPaper(@RequestBody PaperInfoVO paperInfoVO) {
        Asserts.notNull(paperInfoVO, ResponseErrorEnum.PAPER_NOT_NULL);
        Paper paper = paperService.createPaperByInfoVO(paperInfoVO);
        if (paper != null) {
            paperService.updateOrCreatePaper(paper);
            return ResponseResult.newSucceedInstance(
                    "Successfully create paper " + paperInfoVO.getName(), null);
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.PAPER_NOT_NULL, null);
    }

    /**
     * 开始做试卷，初始化一份答题卡，把开始答题时间设置为前端传递过来的时间
     *
     * @param beginDoPaperVO
     * @return
     */
    @RequestMapping(value = "/beginDoPaper", method = RequestMethod.POST)
    ResponseResult<String> beginDoPaper(@RequestBody BeginDoPaperVO beginDoPaperVO) {
        Asserts.notNull(beginDoPaperVO, ResponseErrorEnum.BAD_REQUEST);
        Student student = studentService.getStudentById(beginDoPaperVO.getStudentId());
        Paper paper = paperService.getPaperById(beginDoPaperVO.getPaperId());
        AnswerSheet answerSheet = answerSheetService.createEmptyAnswerSheet(
                student, paper, beginDoPaperVO.getStartTime());
        if (answerSheet != null)
            return ResponseResult.newSucceedInstance(null, String.valueOf(answerSheet.getId()));
        return ResponseResult.newErrorInstance(ResponseErrorEnum.ANSWER_SHEET_NOT_NULL, null);
    }

    /**
     * 批量提交答案，交卷时使用
     * 前端交卷时需要注意，在学生全部题目答完后提交
     * 当答题时间超出试卷的duration时，马上提交
     *
     * @param answerSheetId
     * @param studentAnswerInfoVOList
     * @return
     */
    @RequestMapping(value = "/postAnswer/{answerSheetId}", method = RequestMethod.POST)
    ResponseResult<String> postAnswer(@PathVariable String answerSheetId, @RequestBody List<StudentAnswerInfoVO> studentAnswerInfoVOList) throws ParseException {
        Asserts.notNull(answerSheetId, ResponseErrorEnum.ANSWER_SHEET_ID_NOT_NULL);
        Asserts.notNull(studentAnswerInfoVOList, ResponseErrorEnum.ANSWER_NOT_NULL);
        AnswerSheet answerSheet = answerSheetService.getAnswerSheetById(Integer.parseInt(answerSheetId));
        List<StudentAnswer> studentAnswers = new ArrayList<>();

        for (StudentAnswerInfoVO vo : studentAnswerInfoVOList)
            studentAnswers.add(studentAnswerService.createStudentAnswerByInfoVO(vo, answerSheet));
        answerSheetService.updateStudentAnswer(answerSheet, studentAnswers);

        // 更新结束时间
        answerSheetService.updateAnswerSheetEndTime(answerSheet, DateUtils.getCurrentDay());

        if (!studentAnswers.isEmpty())
            return ResponseResult.newSucceedInstance("Successfully Post Answers", null);
        return ResponseResult.newErrorInstance(ResponseErrorEnum.STUDENT_ANSWER_NOT_NULL, null);
    }

    /**
     * 删除试卷
     *
     * @param paperId
     * @return
     */
    @RequestMapping(value = "/delete/{paperId}", method = RequestMethod.DELETE)
    ResponseResult<String> deletePaper(@PathVariable String paperId) {
        Asserts.notNull(paperId, ResponseErrorEnum.PAPER_ID_NOT_NULL);
        Paper paper = paperService.getPaperById(Integer.parseInt(paperId));
        if (paper != null) {
            paperService.deletePaper(paper);
            return ResponseResult.newSucceedInstance("Successfully Delete Paper " + paperId, null);
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.PAPER_NOT_FOUND, null);
    }

    /**
     * 编辑试卷
     *
     * @param paperId
     * @param paperInfoVO
     * @return
     */
    @RequestMapping(value = "/edit/{paperId}", method = RequestMethod.POST)
    ResponseResult<String> editPaper(@PathVariable String paperId, @RequestBody PaperInfoVO paperInfoVO) {
        Asserts.notNull(paperId, ResponseErrorEnum.PAPER_ID_NOT_NULL);
        Asserts.notNull(paperInfoVO, ResponseErrorEnum.PAPER_NOT_NULL);
        if (paperService.updatePaperByInfoVO(Integer.parseInt(paperId), paperInfoVO)) {
            return ResponseResult.newSucceedInstance("Successfully Update Paper " + paperId, null);
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.BAD_REQUEST, null);
    }

}
