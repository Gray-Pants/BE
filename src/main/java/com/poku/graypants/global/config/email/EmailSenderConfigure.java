package com.poku.graypants.global.config.email;

import java.util.Properties;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@AllArgsConstructor
public class EmailSenderConfigure {

    EmailProperties emailProperties;
    @Bean
    public JavaMailSender emailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(emailProperties.getHost());
        javaMailSender.setPort(emailProperties.getPort());
        javaMailSender.setUsername(emailProperties.getUsername());
        javaMailSender.setPassword(emailProperties.getPassword());
        javaMailSender.setDefaultEncoding("UTF-8");
        javaMailSender.setJavaMailProperties(getMailProperties());
        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", emailProperties.isAuth());
        properties.put("mail.smtp.starttls.enable", emailProperties.isStarttlsEnable());
        properties.put("mail.smtp.starttls.required", emailProperties.isStarttlsRequired());
        properties.put("mail.smtp.connectiontimeout", emailProperties.getConnectionTimeout());
        properties.put("mail.smtp.timeout", emailProperties.getTimeout());
        properties.put("mail.smtp.writetimeout", emailProperties.getWriteTimeout());
        return properties;
    }
}
