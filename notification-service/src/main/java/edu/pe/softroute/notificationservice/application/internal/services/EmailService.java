package edu.pe.softroute.notificationservice.application.internal.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  @Autowired
  private JavaMailSender mailSender;

  public void sendEmail(String fromName, String to, String subject, String body) throws MessagingException, UnsupportedEncodingException {
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

    helper.setFrom("diegodelacruz1104@gmail.com", fromName);
    helper.setTo(to);
    helper.setSubject(subject);
    helper.setText(body, false);

    mailSender.send(mimeMessage);
  }
}
