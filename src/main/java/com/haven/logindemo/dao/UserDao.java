package com.haven.logindemo.dao;

import com.haven.logindemo.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author HavenTong
 * @date 2019/11/19 8:36 下午
 */
@Mapper
@Repository
public interface UserDao {

    @Insert("INSERT INTO user(username, email, is_registered, password, created_at) " +
            "VALUES (#{username}, #{email}, #{isRegistered}, #{password}, now()) ")
    int saveUser(User user);

    @Select("SELECT * FROM user")
    List<User> findAllUsers();

    @Select("SELECT * FROM user WHERE email = #{email}")
    User findUserByEmail(@Param("email") String email);
}
