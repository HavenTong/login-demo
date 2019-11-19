package com.haven.logindemo.service;

import com.haven.logindemo.dao.MailDao;
import com.haven.logindemo.dao.UserDao;
import com.haven.logindemo.domain.Mail;
import com.haven.logindemo.domain.User;
import com.haven.logindemo.utils.CheckCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

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
        String checkCode = CheckCodeUtil.getCheckCode();
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

}
