package edu.pe.softroute.trackingservice.domain.models.entities;

import jakarta.persistence.Embeddable;
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
public class Location implements Serializable {

  private Double latitude;

  private Double longitude;

  private LocalDateTime timestamp;
}
