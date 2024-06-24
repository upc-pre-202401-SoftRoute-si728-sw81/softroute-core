package edu.pe.softroute.packageservice.domain.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Location {

  private Double latitude;

  private Double longitude;

  private String address;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime timestamp;
}
