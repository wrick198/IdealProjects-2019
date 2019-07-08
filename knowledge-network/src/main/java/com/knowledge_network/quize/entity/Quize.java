package com.knowledge_network.quize.entity;

import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.knowledge_network.entity.Subject;
import com.knowledge_network.knowledge_network.entity.Tag;
import com.knowledge_network.user.entity.Teacher;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * ** Created by gongjiangtao on 2018/4/13
 * 试题实体类
 **/

@Entity
@Table(name = "quize")
public class Quize implements Serializable {
    /**
     * id属性，唯一标识一个Quize
     */
    private int id;
    /**
     * 试题的名称
     */
    private String quizeName;
    /**
     * 题目的题干部分
     */
    private String content;
    /**
     * 试题是否可用
     */
    private int isAvailable;
    /**
     * 试题的创建时间
     */
    private Date createTime;
    /**
     * 试题的最后修改时间
     */
    private Date lastModify;
    /**
     * 题目的答案
     */
    private String answer;
    /**
     * 题目的总做题次数
     * 不能为空
     * 初始值为0
     * 满足：exposeTime=rightTimes+wrongTime
     */
    private int exposeTimes = 0;
    /**
     * 题目答对的次数
     * 不能为空
     * 初始值为0
     */
    private int rightTimes = 0;
    /**
     * 题目答错次数
     * 不能为空
     * 初始值为0
     */
    private int wrongTimes = 0;
    /**
     * 题目的解析
     */
    private String analysis;
    /**
     * 题目的分值
     */
    private float examingPoint;
    /**
     * 题目的链接
     */
    private String url;
    /**
     * 与知识点的关联
     * 多对多
     */
    private Collection<KnowledgePoint> knowledgePoints = new ArrayList<>();
    /**
     * 与科目的关联
     * 一个科目下可以有很多试题，
     * 但一个试题只能属于一个科目
     */
    private Subject subject;
    /**
     * 与问题类型的关联
     * 一个问题类型可以有很多个问题
     * 但一个问题只能属于一个问题类型
     */
    private QuestionType questionType;
    /**
     * 与难度的关联
     * 一个难度下可以有多个试题
     * 但一个试题只能有一个难度
     */
    private Difficulty difficulty;
    /**
     * 试题的出题人
     * 一个问题对应一个出题人
     * 一个出题人可以出多个问题
     */
    private Teacher creator;
    /**
     * 与试卷的关联
     * 一份试卷由多个问题组成
     * 一个问题也可以出现在多个试卷中
     */
    private Collection<Paper> papers = new ArrayList<>();
    /**
     * 与错题集的关联
     * 这是学生-错题集-问题的三表关联
     */
    private Collection<WrongNote> wrongNotes = new ArrayList<>();
    /**
     * 与标签的关联
     * 一个问题可以有多个标签
     * 一个标签下也可以有多个问题
     */
    private Collection<Tag> tags = new ArrayList<>();
    /**
     * 与学生已做试题表的关联
     */
    private Collection<HasDoneQuize> hasDoneByStudent = new ArrayList<>();
    /**
     * 与学生提交的答案关联
     */
    private Collection<StudentAnswer> studentAnswers = new ArrayList<>();

    public Quize() {
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "quize_name", nullable = false)
    public String getQuizeName() {
        return quizeName;
    }

    public void setQuizeName(String quizeName) {
        this.quizeName = quizeName;
    }

    @Basic
    @Column(name = "content", nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "is_avaliable", nullable = false)
    public int getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(int isAvaliable) {
        this.isAvailable = isAvaliable;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "last_modify", nullable = false)
    public Date getLastModify() {
        return lastModify;
    }

    public void setLastModify(Date lastModify) {
        this.lastModify = lastModify;
    }

    @Basic
    @Column(name = "answer", nullable = false)
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Basic
    @Column(name = "expose_times", nullable = false)
    public int getExposeTimes() {
        return exposeTimes;
    }

    public void setExposeTimes(int exposeTime) {
        this.exposeTimes = exposeTime;
    }

    @Basic
    @Column(name = "right_times", nullable = false)
    public int getRightTimes() {
        return rightTimes;
    }

    public void setRightTimes(int rightTime) {
        this.rightTimes = rightTime;
    }

    @Basic
    @Column(name = "wrong_times", nullable = false)
    public int getWrongTimes() {
        return wrongTimes;
    }

    public void setWrongTimes(int wrongTimes) {
        this.wrongTimes = wrongTimes;
    }

    @Basic
    @Column(name = "anlysis")
    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    @Basic
    @Column(name = "examing_point", nullable = false)
    public float getExamingPoint() {
        return examingPoint;
    }

    public void setExamingPoint(float examingPoint) {
        this.examingPoint = examingPoint;
    }

    @Basic
    @Column(name = "url", nullable = false)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @ManyToMany(mappedBy = "quizes")
    public Collection<KnowledgePoint> getKnowledgePoints() {
        return knowledgePoints;
    }

    public void setKnowledgePoints(Collection<KnowledgePoint> knowledgepoint) {
        this.knowledgePoints = knowledgepoint;
    }

    @ManyToOne(targetEntity = Subject.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @ManyToOne(targetEntity = QuestionType.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_type_id", referencedColumnName = "question_type_id")
    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    @ManyToOne(targetEntity = Difficulty.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "difficulty_id", referencedColumnName = "difficulty_id")
    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @ManyToOne(targetEntity = Teacher.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "creator_id")
    public Teacher getCreator() {
        return creator;
    }

    public void setCreator(Teacher creator) {
        this.creator = creator;
    }

    @ManyToMany(targetEntity = Paper.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "quizes_in_papers",
            joinColumns = @JoinColumn(name = "quize_id"),
            inverseJoinColumns = @JoinColumn(name = "paper_id")
    )
    public Collection<Paper> getPapers() {
        return papers;
    }

    public void setPapers(Collection<Paper> papers) {
        this.papers = papers;
    }

    @OneToMany(targetEntity = WrongNote.class, mappedBy = "quize", cascade = CascadeType.ALL)
    public Collection<WrongNote> getWrongNotes() {
        return wrongNotes;
    }

    public void setWrongNotes(Collection<WrongNote> wrongNotes) {
        this.wrongNotes = wrongNotes;
    }

    @ManyToMany(targetEntity = Tag.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "quizes_has_tags",
            joinColumns = @JoinColumn(name = "quize_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    public Collection<Tag> getTags() {
        return tags;
    }

    public void setTags(Collection<Tag> tags) {
        this.tags = tags;
    }

    @OneToMany(targetEntity = HasDoneQuize.class, cascade = CascadeType.ALL, mappedBy = "quize")
    public Collection<HasDoneQuize> getHasDoneByStudent() {
        return hasDoneByStudent;
    }

    public void setHasDoneByStudent(Collection<HasDoneQuize> hasDoneByStudent) {
        this.hasDoneByStudent = hasDoneByStudent;
    }

    @OneToMany(targetEntity = StudentAnswer.class, cascade = CascadeType.ALL, mappedBy = "quize")
    public Collection<StudentAnswer> getStudentAnswers() {
        return studentAnswers;
    }

    public void setStudentAnswers(Collection<StudentAnswer> studentAnswers) {
        this.studentAnswers = studentAnswers;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Quize that = (Quize) obj;
        if (id != that.getId()) return false;

        return true;
    }
}
