package com.knowledge_network.support.utils;

import com.knowledge_network.user.vo.web.PageVO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by pentonbin on 17-12-31
 */
public class PageUtils {

    public static <T> List<T> getPageListFromCollection(Collection<T> datas, PageVO page) {
        List<T> pageList = new ArrayList<>();
        if (datas == null
                || page == null
                || page.getStart() < 0
                || page.getRows() < 0
                || page.getStart() >= datas.size()) {
            return pageList;
        }
        Object[] dataArr = datas.toArray();
        for (int i = page.getStart(); i < Math.min(page.getStart() + page.getRows(), dataArr.length); ++i) {
            pageList.add((T) dataArr[i]);
        }
        return pageList;
    }
}
