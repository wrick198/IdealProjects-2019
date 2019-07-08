package com.knowledge_network.quize.controller;

import com.knowledge_network.knowledge_network.service.SubjectService;
import com.knowledge_network.quize.entity.StudentWrongNoteSubject;
import com.knowledge_network.quize.entity.WrongNote;
import com.knowledge_network.quize.service.StudentWrongNoteSubjectService;
import com.knowledge_network.quize.service.WrongNoteService;
import com.knowledge_network.quize.vo.QuizeInfoVO;
import com.knowledge_network.quize.vo.WrongNoteCoverVO;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.support.utils.Asserts;
import com.knowledge_network.support.utils.VOUtils;
import com.knowledge_network.user.entity.Student;
import com.knowledge_network.user.service.StudentService;
import com.knowledge_network.user.vo.web.ConditionVO;
import com.knowledge_network.user.vo.web.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/4/27
 * 错题以及错题集控制器类
 **/
@RestController
@RequestMapping("/wrongNote")
public class WrongNoteController {
    @Autowired
    WrongNoteService wrongNoteService;
    @Autowired
    StudentWrongNoteSubjectService studentWrongNoteSubjectService;
    @Autowired
    StudentService studentService;
    @Autowired
    SubjectService subjectService;

    /**
     * 获取某学生所有错题集的封面信息以及名称
     *
     * @param studentId
     * @return
     */
    @RequestMapping(value = "/{studentId}", method = RequestMethod.GET)
    ResponseResult<WrongNoteCoverVO> getStudentAllWrongNote(@PathVariable String studentId) {
        Asserts.notNull(studentId, ResponseErrorEnum.USER_ID_NOT_NULL);
        Integer id = Integer.parseInt(studentId);
        Student student = studentService.getStudentById(id);
        List<StudentWrongNoteSubject> studentWrongNoteSubjects =
                studentWrongNoteSubjectService.getStudentWrongNoteSubjectRecordByStudent(student);

        if (studentWrongNoteSubjects != null) {
            String studentName = student.getUsername();
            List<String> subjectName = new ArrayList<>();
            List<String> wrongNoteName = new ArrayList<>();
            List<String> coverUrl = new ArrayList<>();

            for (StudentWrongNoteSubject s : studentWrongNoteSubjects) {
                subjectName.add(s.getSubject().getName());
                wrongNoteName.add(s.getName());
                coverUrl.add(s.getCoverUrl());
            }

            WrongNoteCoverVO wrongNoteCoverVO =
                    new WrongNoteCoverVO(studentName, subjectName, wrongNoteName, coverUrl);
            return ResponseResult.newSucceedInstance(null, wrongNoteCoverVO);
        }

        return ResponseResult.newErrorInstance(ResponseErrorEnum.STUDENT_WRONG_NOTE_SUBJECT_NOT_NULL, null);
    }

    /**
     * 根据pageVO中的条件（学生、科目）获取错题
     *
     * @param pageVO
     * @return
     */
    @RequestMapping(value = "/wrongQuizes", method = RequestMethod.POST)
    ResponseResult<List<QuizeInfoVO>> getStudentSubjectWrongNote(@RequestBody PageVO pageVO) {
        Asserts.notNull(pageVO, ResponseErrorEnum.BAD_REQUEST);
        Asserts.notNull(pageVO.getConditions(), ResponseErrorEnum.CONDITION_NOT_NULL);
        List<BaseHibernateDAO.Condition> conditions = VOUtils.conditionVOs2Conditions(pageVO.getConditions());
        List<WrongNote> wrongNotes = wrongNoteService.getWrongNotesByStudentSubjectPage(pageVO.getStart(),
                pageVO.getRows(), conditions);

        if (wrongNotes != null) {
            List<QuizeInfoVO> quizes = new ArrayList<>();
            for (WrongNote w : wrongNotes) {
                quizes.add(new QuizeInfoVO(w.getQuize()));
            }
            return ResponseResult.newSucceedInstance(null, quizes);
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.WRONG_NOTE_NOT_NULL, null);
    }

    /**
     * 根据条件（学生、科目、题目缺一不可）删除错题记录
     *
     * @param conditionVOS
     * @return
     */
    @RequestMapping(value = "/removeQuize", method = RequestMethod.POST)
    ResponseResult<String> removeQuize(@RequestBody List<ConditionVO> conditionVOS) {
        Asserts.notNull(conditionVOS, ResponseErrorEnum.CONDITION_NOT_NULL);
        List<BaseHibernateDAO.Condition> conditions = VOUtils.conditionVOs2Conditions(conditionVOS);
        List<WrongNote> wrongNotes = wrongNoteService.getWrongNoteByConditions(conditions);
        if (wrongNotes != null) {
            for (WrongNote wrongNote : wrongNotes)
                wrongNoteService.deleteWrongNote(wrongNote);
            return ResponseResult.newSucceedInstance("Successfully Deleted WrongNote", null);
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.WRONG_NOTE_NOT_NULL, null);
    }

    @RequestMapping(value = "/practice/{wrongNoteId}", method = RequestMethod.GET)
    ResponseResult<String> practice(@PathVariable String wrongNoteId) {
        Asserts.notNull(wrongNoteId, ResponseErrorEnum.WRONG_NOTE_ID_NOT_NULL);
        Integer id = Integer.parseInt(wrongNoteId);
        WrongNote wrongNote = wrongNoteService.getWrongNoteById(id);
        if (wrongNote != null) {
            wrongNoteService.updatePracticeTimePlusOne(wrongNote);
            return ResponseResult.newSucceedInstance("Successfully update practice time", null);
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.WRONG_NOTE_NOT_FOUND, null);
    }
}
