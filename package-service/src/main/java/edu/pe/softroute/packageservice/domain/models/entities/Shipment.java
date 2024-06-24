package edu.pe.softroute.packageservice.domain.models.entities;

import edu.pe.softroute.packageservice.domain.models.enums.ShipmentStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Shipment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private String code;

  private UUID carrierId;

  private int numPackages;

  private Integer packagesDelivered;

  @Enumerated(EnumType.STRING)
  private ShipmentStatus status;

  private UUID companyId;

  private LocalDateTime startDate;

  private String encodedPolyline;

  private String address;

  private Double latitude;

  private Double longitude;

  private LocalDateTime timestamp;

  private Integer currentStep;

  private Integer lastStep;

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "shipment", cascade = CascadeType.ALL)
  private List<Package> packages;

  public void updateCurrentLocation(Location location) {
    this.address = location.getAddress();
    this.latitude = location.getLatitude();
    this.longitude = location.getLongitude();
    this.timestamp = LocalDateTime.now();
  }
}
