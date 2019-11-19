package com.haven.logindemo.dao;

import com.haven.logindemo.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author HavenTong
 * @date 2019/11/19 8:36 下午
 */
@Mapper
@Repository
public interface UserDao {

    @Insert("INSERT INTO user(username, email, is_registered, password) " +
            "VALUES (#{username}, #{email}, #{isRegistered}, #{password}) ")
    int saveUser(User user);
}
