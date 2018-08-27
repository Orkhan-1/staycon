package com.staycon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfiguration {

    @Value("${mail.smtp.port}")
    private Integer port;

    @Bean
    public JavaMailSender mailSender () {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setPort(port);
        return mailSender;
    }

}
