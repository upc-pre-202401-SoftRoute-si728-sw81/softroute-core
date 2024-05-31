package edu.pe.softroute.trackingservice.domain.models.entities;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tracking {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private String trackingNumber;

  @Embedded
  private TrackingStatus status;

  @Embedded
  private Location location;
}
