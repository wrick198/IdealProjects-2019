package com.knowledge_network.support.common;

/**
 * Created by pentonbin on 17-12-14
 * <p>
 * 系统请求的所有错误
 */
public enum ResponseErrorEnum {

    // HTTP
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    // 业务错误
    // 用户模块1xxx
    USER_NOT_FOUND(1001, "User Not Found"),
    USER_RESET_PASSWORD_NOT_PATCH(1002, "Inconsistent Password "),
    PARENT_NOT_FOUND(1003, "Parent Not Found"),
    CHILD_NOT_FOUND(1004, "Child Not Found"),
    CREATE_USER_FAILED(1005, "Create Failed"),
    REGISTER_FAILED(1006, "Register Failed"),
    USERNAME_EXISTS(1007, "Username Exists"),
    USERNAME_OR_PASSWORD_ERROR(1008, "Username Or Password Error"),
    USERNAME_NOT_NULL(1009, "Username Not Null"),
    USER_PASSWORD_NOT_NULL(1010, "User Password Not null"),
    USER_ROLE_NOT_NULL(1011, "User Role Not Null"),
    TEACHER_CERT_NUM_NOT_NULL(1011, "Teacher Certificate Number Not Null"),
    USER_ID_NOT_NULL(1012, "User Id Not Null"),
    SCHOOL_ID_NOT_NULL(1013, "School Id Not Null"),
    USER_NEW_PASSWORD_NOT_NULL(1014, "User New Password Not Null"),
    QUIZE_OR_PAPER_CREATOR_MUST_NOT_A_STUDENT_OR_PARENT(1015, "Quize or Paper Creator Must Not a Student or Parent"),

    // 知识网络地图2xxx
    KNOWLEDGEPOINT_NOT_FOUND(2001, "KnowledgePoint Not Found"),
    KNOWLEDGEPOINT_EXIST(2002, "KnowledgePoint Has Exist"),
    NO_MATCHING_KNOWLEDGEPOINT(2003, "No Matching KnowledgePoint"),
    KNOWLEDGEPOINT_NOT_NULL(2004, "KnowledgePoint Not Null"),


    // 资源3×××
    RESOURCE_FILE_EMPTY(3001, "File Is Empty"),
    RESOURCE_SUFFIX_ERROR(3002, "Suffix Is Invalid"),
    RESOURCE_FILE_TYPE_ERROR(3003, "File Type Not Supported!"),
    RESOURCE_FILE_UNKNOWN_ERROR(3004, "Unknown Error"),
    RESOURCE_FILE_NAME_EMPTY(3005, "Resource's File Name Cannot Be Empty"),
    RESOURCE_FILE_GRADE_EMPTY(3006, "Resource's Grade Cannot Be Empty"),
    RESOURCE_FILE_URL_EMPTY(3007, "Resource's URL Cannot Be Empty"),
    RESOURCE_NOT_EXISTS(3008, "Resource NOT Exists"),
    RESOURCE_ILLEGAL_URL(3009, "Illegal Resource URL"),
    RESOURCE_DOWNLOAD_URL_EMPTY(3010, "Empty Resource URL"),
    RESOURCE_FILE_SUBJECT_EMPTY(3011, "Empty Subject Id"),
    RESOURCE_NOT_NULL(3012, "Resource Not Null"),

    //课程4xxx
    SUBJECT_NOT_FOUND(4001, "Subject Not Found"),
    COURSE_NOT_FOUND(4002, "Course Not Found"),
    SUBJECT_ID_NOT_NULL(4003, "Subject Id Not Null"),
    CREATE_COURSE_FAIL(4004, "Create Failed"),
    EDIT_COURSE_FAIL(4005, "Edit Failed"),
    DELETE_COURSE_FAIL(4006, "Delete Failed"),
    COURSE_HAS_NO_KNOWLEDGEPOINT(4007, "Course has no KnowledgePoint"),
    ENTRY_COURSE_FAIL(4008, "Entry course failed"),
    EXIT_COURSE_FAIL(4009, "Exit course failed"),
    EVALUATE_COURSE_FAIL(4010, "Evaluate course failed"),
    ADD_COURSE_QUESTION_FAIL(4011, "Add course question failed"),
    EDIT_COURSE_QUESTION_FAIL(4012, "Edit course question failed"),
    SUBJECT_NAME_NOT_NULL(4013, "Subject Name Not Null"),

    //圈子讨论区5xxx
    TITLE_NOT_NULL(5001, "Title Not Null"),
    TAB_NOT_NULL(5002, "Tab Not Null"),
    TITLE_EXISTS(5003, "Title Exists"),
    TOPIC_NOT_NULL(5004, "Topic Not Null"),
    ILLEGAL_OPERATION(5005, "Illegal Operation"),
    COLLECT_EXISTS(5006, "Collect Exists"),
    COLLECT_NOT_NULL(5007, "Collect Not Null"),
    SEARCH_STRING_NOT_NULL(5008, "Search String Not Null"),
    LABEL_NOT_NULL(5009, "Label Not Null"),
    RELATE_EXISTS(5010, "Relate Exists"),
    LABEL_EXISTS(5011, "Label Exists"),
    CONTENT_NOT_NULL(5012, "Content Not Null"),
    REPLY_NOT_NULL(5013, "Reply Not Null"),
    UP_ERROR(5014, "Up Error"),
    REPLY_EXIST_ERROR(5015, "Reply Exist Error"),

    //测试题6XXX
    QUIZE_NOT_NULL(6000, "Quize Not Null"),
    QUIZE_NAME_NOT_NULL(6001, "Quize Name Not Null"),
    QUIZE_ID_NOT_NULL(6002, "Quize Id Not Null"),
    NOT_A_LEGAL_QUIZE(6009, "Quize is Not Legal"),
    CREATOR_NOT_NULL(6003, "Creator Not Null"),
    NO_MATCHING_USER(6004, "No Matching User"),
    EXAMING_POINT_NOT_NULL(6005, "Examing Point Not Null"),
    KEYWORD_NOT_NULL(6006, "Keyword Not Null"),
    NOT_A_LEGAL_ANSWER(6007, "Not A Legal Answer"),
    ANSWER_NOT_NULL(6008, "Answer Not Null"),
    DIFFICULTY_NOT_NULL(6010, "Difficulty Not Null"),
    QUIZE_URL_NOT_NULL(6011, "Quize Url Not Null"),
    PAPER_ID_NOT_NULL(6012, "Paper Id Not Null"),
    PAPER_NOT_NULL(6013, "Paper Not Null"),
    PAPER_NAME_NOT_NULL(6014, "Paper Name Not Null"),
    PAPER_TYPE_NOT_NULL(6015, "Paper Type Not Null"),
    PAPER_TYPE_INVALID(6016, "Paper Type Invalid"),
    PASSPOINT_NOT_NULL(6020, "PassPoint Not Null"),
    TOTALPOINT_NOT_NULL(6021, "Total Point Not Null"),
    TOTAL_POINT_NOT_LARGER_THAN_PASS_POINT(6022, "Total Point Should Larger Than Or Equal Pass Point"),
    PAPER_NOT_FOUND(6023, "Paper Not Found"),
    PAPER_TYPE_NAME_NOT_NULL(6024, "Paper Type Name Not Null"),
    PAPER_TYPE_ID_NOT_NULL(6025, "Paper Type Id Not Null"),
    PAPER_TYPE_NOT_FOUND(6026, "Paper Type Not Found"),
    WRONG_NOTE_NOT_NULL(6027, "Wrong Note Not Null"),
    WRONG_NOTE_ID_NOT_NULL(6028, "Wrong Note Id Not Null"),
    WRONG_NOTE_NAME_NOT_NULL(6029, "Wrong Note Name Not Null"),
    STUDENT_DO_PAPER_NOT_NULL(6030, "Student Do Paper Record Not Null"),
    DO_PAPER_START_TIME_NOT_NULL(6031, "Do Paper Start Time Not Null"),
    DO_PAPER_END_TIME_NOT_NULL(6032, "Do Paper End Time Not Null"),
    PAPER_SCORE_NOT_NULL(6033, "Paper Score Not Null"),
    STUDENT_WRONG_NOTE_SUBJECT_NOT_NULL(6033, "Student Wrong Note Subject Not Null"),
    WRONG_NOTE_COVER_URL_NOT_NULL(6034, "Wrong Note Cover Url Not Null"),
    QUIZE_NOT_FOUND(6035, "Quize Not Found"),
    QUESTION_TYPE_NOT_NULL(6036, "Question Type Not Null"),
    WRONG_NOTE_NOT_FOUND(6037, "Wrong Note Not Found"),
    STUDENT_HAS_DONE_QUIZE_ID_NOT_NULL(6038, "Student Has Done Quize Id Not Null"),
    STUDENT_HAS_DONE_QUIZE_NOT_FOUND(6039, "Student Has Done Quize Not Found"),
    STUDENT_HAS_DONE_QUIZE_NOT_NULL(6040, "Student Has Done Quize Not Null"),
    ANSWER_SHEET_ID_NOT_NULL(6041, "Answer Sheet Id Not Null"),
    ANSWER_SHEET_NOT_NULL(6042, "Answer Sheet Not Null"),
    NO_MATCHING_ANSWER_SHEET(6043, "No Matching Answer Sheet"),
    STUDENT_ANSWER_ID_NOT_NULL(6044, "Student Answer Id Not Null"),
    STUDENT_ANSWER_NOT_NULL(6045, "Student Answer Not Null"),
    ANSWER_SHEET_NOT_FOUND(6046, "Answer Sheet Not Found"),
    STUDENT_ANSWER_NOT_FOUND(6047, "Student Answer Not Found"),

    // 安全8xxx
    CLIENT_SEQUENCE_EXISTS(8001, "Client Sequence Exists"),
    ILLEGAL_REQUEST(8002, "Illegal Request, Rejected"),


    // 其他9xxx
    OTHER_START_NOT_NULL(9001, "Start Index Not Null"),
    OTHER_ROWS_NOT_NULL(9002, "Paging Rows Not Null"),
    CONDITION_NOT_NULL(9003, "Condition Not Null"),
    TAG_NOT_NULL(9004, "Tag Not Null");


    private int errorCode;
    private String errorMessage;

    ResponseErrorEnum(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
