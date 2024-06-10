package edu.pe.softroute.trackingservice.interfaces.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitTrackingReq {

  @NotNull
  private UUID deviceId;

  @NotNull
  private UUID packageId;
}
