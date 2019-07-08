package com.knowledge_network.circle.vo.web;

/**
 * Created by wshish000 on 18-3-4
 */
public class ConditionVO {

    /**
     * 属性名称
     */
    private String property;
    /* *
     * 属性值
     */
    private Object value;
    /**
     * 属性名称与值的关系，例如“eq”、“like”、“gt”、“le”等
     * 可以参考：{@link com.knowledge_network.support.base.BaseHibernateDAO.ConditionType}
     */
    private String op;

    public ConditionVO() {
    }

    public ConditionVO(String property, Object value, String op) {
        this.property = property;
        this.value = value;
        this.op = op;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }
}
