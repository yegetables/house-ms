package com.xupt.house.service.impl;

import com.xupt.house.service.MailService;
import com.xupt.house.utils.SensUtil;
import io.github.biezhi.ome.OhMyEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

/**
 * 邮件发送业务逻辑实现类
 */
@Service
public class MailServiceImpl implements MailService {

    @Value("${mail.smtp.host}")
    private String host;

    @Value("${mail.smtp.username}")
    private String username;

    @Value("${mail.smtp.password}")
    private String password;

    @Value("${mail.from.name}")
    private String fromName;

    /**
     * 发送邮件
     *
     * @param to      to 接收者
     * @param title   subject 标题
     * @param content content 内容
     */
    @Override
    public void sendMail(String to, String title, String content) throws MessagingException {
        //配置邮件服务器
        SensUtil.configMail(host, username, password);
        OhMyEmail.subject(title)
                .from(fromName)
                .to(to)
                .text(content)
                .send();
    }
}