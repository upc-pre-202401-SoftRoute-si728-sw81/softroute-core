package edu.pe.softroute.trackingservice.domain.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.pe.softroute.trackingservice.domain.models.enums.StatusEvent;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
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

  private String street;

  private String district;

  private String province;

  private String country;

  private Double latitude;

  private Double longitude;

  private String encodedPolyline;

  private int currentStep;

  private int lastStep;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime timestamp;

  @Enumerated(EnumType.STRING)
  private StatusEvent status;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime statusDate;
}
