package com.knowledge_network.user.service;

import com.knowledge_network.user.entity.School;

/**
 * Created by pentonbin on 17-12-16
 */
public interface SchoolService {

    /**
     * 根据学校id获取学校对象
     *
     * @param id 学校id
     * @return 学校对象
     */
    public School getSchoolById(Integer id);
}
