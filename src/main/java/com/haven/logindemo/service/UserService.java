package com.haven.logindemo.service;

import com.haven.logindemo.dao.MailDao;
import com.haven.logindemo.dao.UserDao;
import com.haven.logindemo.domain.Mail;
import com.haven.logindemo.domain.User;
import com.haven.logindemo.utils.CheckCodeUtils;
import com.haven.logindemo.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HavenTong
 * @date 2019/11/19 8:34 下午
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MailDao mailDao;


    @Autowired
    private MailService mailService;

    @Autowired
    private BCryptPasswordEncoder encoder;


    public void sendCheckCode(String email){
        String checkCode = CheckCodeUtils.getCheckCode();
        mailDao.saveEmail(email, checkCode);
        String subject = "This is a registration email from Haven";
        String content = "<h1>Welcome to Note Hub!</h1><br><p>Your check code is "
                         + checkCode
                         + ".</p>";
        mailService.sendHtmlMail(email, subject,content);
        log.info("邮件发送成功");
    }

    public void register(User user, String checkCode){
        Mail mail = mailDao.findMailByEmail(user.getEmail());
        String code = mail.getCheckCode();
        if (checkCode.equals(code)){
            user.setIsRegistered(true);
            user.setPassword(encoder.encode(user.getPassword()));
            log.info("user{}", user);
            userDao.saveUser(user);
            log.info("用户注册成功");
        }
    }

    public List<User> findAllUsers(){
        return userDao.findAllUsers();
    }

    public Map<String, String> login(String email, String password){
        User user = userDao.findUserByEmail(email);
        Map<String, String> loginInfo = new HashMap<>();
        if (user != null && encoder.matches(password, user.getPassword())){
            String token = JwtUtils.createJwt(user);
            loginInfo.put("token", token);
            loginInfo.put("userName", user.getUsername());
            loginInfo.put("email", user.getEmail());
        } else {
            loginInfo.put("message", "login failed");
        }
        return loginInfo;
    }

}
