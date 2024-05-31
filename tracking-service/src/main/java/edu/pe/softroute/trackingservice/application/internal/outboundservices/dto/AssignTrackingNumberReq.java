package edu.pe.softroute.trackingservice.application.internal.outboundservices.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssignTrackingNumberReq {
  private String trackingNumber;
}
