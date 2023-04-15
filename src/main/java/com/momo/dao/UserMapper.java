package com.momo.dao;

import com.momo.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User select(@Param("username") String username, @Param("password") String password);

    User selectByUsername(@Param("username") String username);

    void add(User user);
}
