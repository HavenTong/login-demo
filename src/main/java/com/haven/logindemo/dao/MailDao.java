package com.haven.logindemo.dao;

import com.haven.logindemo.domain.Mail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author HavenTong
 * @date 2019/11/19 8:50 下午
 */
@Mapper
@Repository
public interface MailDao {
    @Insert("INSERT INTO mail (email, check_code) VALUES(#{email}, #{checkCode})")
    int saveEmail(@Param("email") String email, @Param("checkCode")String checkCode);

    @Select("SELECT * FROM mail where email=#{email}")
    Mail findMailByEmail(@Param("email") String email);
}
