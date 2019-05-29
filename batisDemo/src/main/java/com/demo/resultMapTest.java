package com.demo;

import com.mapper.ProvinceMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.List;

public class resultMapTest {
    public static void main(String[] args) {
        Logger log=Logger.getLogger(RoleTest.class);
        SqlSessionFactory sqlSessionFactory=null;
        String resource="mybatisConfig";
        InputStream inputStream;
        SqlSession sqlSession=null;

        try {
            inputStream=Resources.getResourceAsStream(resource);
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            ProvinceMapper pm = sqlSession.getMapper(ProvinceMapper.class);

            List<Province> list = pm.getProvince();
            for (Province province : list) {
                System.out.println("查询结果为："+province.getId()+province.getName()+province.getArea()+province.getAlias().getName());
                log.info(province);
            }
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
