package edu.pe.softroute.packageservice.domain.models.entities;

import edu.pe.softroute.packageservice.domain.models.enums.PackageStatus;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Package {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private String description;

  private Double weight;

  private Double height;

  private Double width;

  private Double length;

  private UUID ownerId;

  @Enumerated(EnumType.STRING)
  private PackageStatus status;

  private UUID trackingId;

  private UUID companyId;
}
