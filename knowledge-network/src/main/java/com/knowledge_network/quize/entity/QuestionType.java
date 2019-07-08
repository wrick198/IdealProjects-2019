package com.knowledge_network.quize.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * ** Created by gongjiangtao on 2018/4/12
 * 问题类型实体类
 **/

@Entity
@Table(name = "question_type")
public class QuestionType implements Serializable {
    /**
     * 定义了选择题 = 1
     * 多选题 = 2
     * 判断题 = 3
     * 填空题 = 4
     * 问答题 = 5
     * 后续可根据需要添加更多类型
     */
    public static final int SINGLE_CHOSEN_QUESTION = 1;
    public static final int MULTIPLE_CHOSENS_QUESTION = 2;
    public static final int TRUE_OR_FALSE_QUESTION = 3;
    public static final int FILL_IN_BLANKS_QUESTION = 4;
    public static final int ASK_AND_ANSWER_QUESTION = 5;

    /**
     * id属性，唯一标识一个QuestionType
     */
    private Integer id;

    /**
     * 问题类型，值为上面的类型值
     */
    private int questionType;

    /**
     * 问题类型名称
     */
    private String name;

    /**
     * 与Quize的关联属性，
     * 为一对多关联
     */
    private Collection<Quize> quizes = new ArrayList<>();

    public QuestionType() {}

    @Id
    @Column(name = "question_type_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "question_type", nullable = false, unique = true)
    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    @Basic
    @Column(name = "question_type_name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(targetEntity = Quize.class, cascade = CascadeType.ALL, mappedBy = "questionType")
    public Collection<Quize> getQuizes() {
        return quizes;
    }

    public void setQuizes(Collection<Quize> quizes) {
        this.quizes = quizes;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != getClass()) return false;

        QuestionType that = (QuestionType) obj;
        if (that.getId() != id) return false;
        else if (that.getQuestionType() != getQuestionType()) return false;

        return true;
    }
}
