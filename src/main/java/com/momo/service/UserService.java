package com.momo.service;

import com.momo.dao.UserMapper;
import com.momo.pojo.User;
import com.momo.util.sqlsessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserService {

    final SqlSessionFactory sqlSessionFactory = sqlsessionFactoryUtils.getSqlSessionFactory();

    public User login(String username, String password) {

        /**
         * 登录
         */
        final SqlSession sqlSession = sqlSessionFactory.openSession();
        final UserMapper usrMapper = sqlSession.getMapper(UserMapper.class);
        final User user = usrMapper.select(username, password);

        sqlSession.close();

        return user;
    }

    public boolean register(User user) {

        /**
         * 注册
         */
        final SqlSession sqlSession = sqlSessionFactory.openSession();
        final UserMapper usrMapper = sqlSession.getMapper(UserMapper.class);
        final User u = usrMapper.selectByUsername(user.getUsername());
        if (u== null) {//用户名不存在  注册成功
            usrMapper.add(user);
            sqlSession.commit();
        }
        sqlSession.close();
        return u==null;
    }





    }
