package com.knowledge_network.user.vo;

import java.util.List;

/**
 * Created by pentonbin on 17-12-25
 * <p>
 * 分页的列表显示
 */
public class ListVO<T> {

    /**
     * 查询结果总共页面数
     */
    private Long totalNum;
    /**
     * 查询结果的起始下标
     */
    private Integer start;
    /**
     * 返回的查询结果数量（注意：不一定等于原来页面的条目数）
     */
    private Integer num;
    /**
     * 查询结果
     */
    private List<T> items;

    public ListVO() {
    }

    public ListVO(Long totalNum, Integer start, Integer num, List<T> items) {
        this.totalNum = totalNum;
        this.start = start;
        this.num = num;
        this.items = items;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
