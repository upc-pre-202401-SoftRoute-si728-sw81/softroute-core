package edu.pe.softroute.trackingservice.domain.models.entities;

import edu.pe.softroute.trackingservice.domain.models.enums.StatusEvent;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrackingStatus implements Serializable {

  @Enumerated(EnumType.STRING)
  private StatusEvent status;

  private String statusDetails;

  private LocalDateTime statusDate;

}
