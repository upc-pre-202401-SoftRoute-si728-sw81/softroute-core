package edu.pe.softroute.notificationservice.interfaces.rest;

import edu.pe.softroute.notificationservice.application.internal.services.EmailService;
import edu.pe.softroute.notificationservice.interfaces.rest.dto.EmailReq;
import jakarta.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

  private final EmailService emailService;

  @PostMapping("/emails")
  public void sendEmail(@RequestBody EmailReq req) throws MessagingException, UnsupportedEncodingException {
    emailService.sendEmail(req.getFromName(), req.getTo(), req.getSubject(), req.getBody());
  }
}
