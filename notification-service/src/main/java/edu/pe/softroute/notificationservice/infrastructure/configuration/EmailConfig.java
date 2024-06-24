package edu.pe.softroute.notificationservice.infrastructure.configuration;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

  @Value("${spring.mail.username}")
  private String username;

  @Value("${spring.mail.password}")
  private String password;

  private Properties getProperties() {
    Properties properties = new Properties();
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.port", "587");
    properties.put("mail.smtp.starttls.enable", "true");

    return properties;
  }

  @Bean
  public JavaMailSender javaMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setJavaMailProperties(getProperties());
    mailSender.setDefaultEncoding("UTF-8");
    mailSender.setUsername(username);
    mailSender.setPassword(password);

    return mailSender;
  }

  @Bean
  public ResourceLoader resourceLoader() {
    return new DefaultResourceLoader();
  }
}
