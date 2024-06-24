package edu.pe.softroute.notificationservice.interfaces.rest.dto;

import lombok.Data;

@Data
public class EmailReq {

  private String fromName;

  private String to;

  private String subject;

  private String body;
}
