package com.knowledge_network.support.utils;

import java.lang.reflect.Field;

/**
 * ** Created by gongjiangtao on 2018/5/7
 * 用于编辑xxx功能
 **/

public class EditUtils {
    /**
     * 发现两个对象之间值不同的属性，然后修改
     *
     * @param toEdit 待编辑的对象（在数据库中的）
     * @param data   利用前端VO生成的对象）
     * @return
     */
    public static Object findDifferenceAndModify(Object toEdit, Object data) throws Throwable {
        if (toEdit == null || data == null) return null;
        if (toEdit.getClass() != data.getClass()) return null;
        Field[] fields = toEdit.getClass().getDeclaredFields();
        for (Field f : fields) {
            String name = f.getName();

            // 跳过id属性
            if (name.equals("id")) continue;

            f.setAccessible(true);
            if (!f.get(toEdit).equals(f.get(data))) {
                f.set(toEdit, f.get(data));
            }
        }
        return toEdit;
    }
}
