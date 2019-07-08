package com.knowledge_network.circle.vo.web;

import com.knowledge_network.circle.vo.web.ConditionVO;

import java.util.List;

/**
 * Created by wshish000 on 18-3-4
 */
public class PageVO {

    /**
     * 分页：起始下标
     */
    private Integer start;
    /**
     * 分页：每一页的数量
     */
    private Integer rows;

    private List<ConditionVO> conditions;

    public PageVO() {
    }

    public PageVO(Integer start, Integer row, List<ConditionVO> conditions) {
        this.start = start;
        this.rows = row;
        this.conditions = conditions;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public List<ConditionVO> getConditions() {
        return conditions;
    }

    public void setConditions(List<ConditionVO> conditions) {
        this.conditions = conditions;
    }
}
