package edu.pe.softroute.packageservice.interfaces.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipmentReq {

  @NotNull
  private UUID carrierId;

  private List<UUID> packagesIds;
}
