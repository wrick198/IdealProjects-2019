package com.demo;

import com.mapper.RoleMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;


public class RoleTest {
    public static void main(String[] args) {
        Logger log=Logger.getLogger(RoleTest.class);
        SqlSessionFactory sqlSessionFactory=null;
        String resource="mybatisConfig";
        InputStream inputStream;
        SqlSession sqlSession=null;
        try{
            inputStream=Resources.getResourceAsStream(resource);
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession=sqlSessionFactory.openSession();
            RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);

            Role role=roleMapper.getRoleByID(1L);
            log.info(role.getRoleName());

/*          Role role1=new Role();
            role1.setNote("as");
            role1.setRoleName("ac");
            int i=roleMapper.insertRole(role1);
            System.out.println(i);*/

/*            Role role1=new Role();
            role1.setId(1L);
            role1.setNote("aa");
            role1.setRoleName("aa1");
            int i=roleMapper.updateRole(role1);
            System.out.println(i);
            log.info(role1.getRoleName());*/

            /*int i=roleMapper.deleteRole(2L);
            log.info(i);*/

            sqlSession.commit();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
    }
}
