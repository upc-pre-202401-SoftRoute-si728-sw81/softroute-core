package edu.pe.softroute.trackingservice.domain.models.entities;

import edu.pe.softroute.trackingservice.domain.models.enums.StatusEvent;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrackingStatus {

  @Enumerated(EnumType.STRING)
  private StatusEvent status;

  private LocalDateTime statusDate;
}
