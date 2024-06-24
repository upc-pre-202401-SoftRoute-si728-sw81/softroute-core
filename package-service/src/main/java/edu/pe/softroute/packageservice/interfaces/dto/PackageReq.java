package edu.pe.softroute.packageservice.interfaces.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PackageReq {

  @NotEmpty
  private String description;

  @NotNull
  @Positive
  private Double weight;

  @NotNull
  @Positive
  private Double height;

  @NotNull
  @Positive
  private Double width;

  @NotNull
  @Positive
  private Double length;

  @NotNull
  private Double minTemperature;

  @NotNull
  private Double maxTemperature;

  @NotNull
  @Positive
  private Double minHumidity;

  @NotNull
  @Positive
  private Double maxHumidity;

  @NotNull
  private String destinationAddress;

  @NotNull
  private UUID customerId;
}
