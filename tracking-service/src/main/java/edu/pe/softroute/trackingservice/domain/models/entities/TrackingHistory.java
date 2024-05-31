package edu.pe.softroute.trackingservice.domain.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.pe.softroute.trackingservice.domain.models.enums.StatusEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrackingHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Enumerated(EnumType.STRING)
  private StatusEvent status;

  private String statusDetails;

  private LocalDateTime statusDate;

  @ManyToOne
  @JsonIgnore
  private Tracking tracking;
}
