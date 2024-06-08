package edu.pe.softroute.iotdeviceservice.infrastructure.google.maps.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.pe.softroute.iotdeviceservice.infrastructure.google.maps.decoders.Point;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RoutesApiService {

  @Value("${google.maps.api.key}")
  private String apiKey;

  private final RestTemplate restTemplate;
  private final ObjectMapper objectMapper;

  public List<Point> getRoute() {

  }
}
