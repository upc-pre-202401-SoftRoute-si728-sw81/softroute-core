package edu.pe.softroute.packageservice.infrastructure.google.services.maps.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.pe.softroute.packageservice.domain.models.entities.Location;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GeoLocationService {

  @Value("${google.maps.api.key}")
  private String apiKey;

  private final RestTemplate restTemplate;
  private final ObjectMapper objectMapper;

  public Location getLocationByAddress(String addressTo) {
    String url = String.format(
        "https://maps.googleapis.com/maps/api/geocode/json?address=%s&location_type=geometric_center&key=%s",
        addressTo, apiKey);

    try {
      String response = restTemplate.getForObject(url, String.class);
      JsonNode jsonNode = objectMapper.readTree(response);
      System.out.println(url);
      System.out.println(response);
      System.out.println(apiKey);
      System.out.println(addressTo);

      if (jsonNode.has("results") && jsonNode.get("results").size() > 0) {
        JsonNode result = jsonNode.get("results").get(0);
        String address = result.get("formatted_address").asText();
        Double latitude = result.path("geometry").path("location").path("lat").asDouble();
        Double longitude = result.path("geometry").path("location").path("lng").asDouble();

        return Location.builder()
            .address(address)
            .latitude(latitude)
            .longitude(longitude)
            .timestamp(LocalDateTime.now())
            .build();
      } else {
        throw new RuntimeException(String.format("No results found for address: %s", addressTo));
      }
    } catch (Exception e) {
      throw new RuntimeException("Error processing geocoding data: " + e.getMessage(), e);
    }
  }

  public Location getLocationByLatLng(Double lat, Double lng) {
    String url = String.format(
        "https://maps.googleapis.com/maps/api/geocode/json?latlng=%f,%f&location_type=geometric_center&key=%s",
        lat, lng, apiKey);

    try {
      String response = restTemplate.getForObject(url, String.class);
      JsonNode jsonNode = objectMapper.readTree(response);

      if (jsonNode.has("results") && jsonNode.get("results").size() > 0) {
        JsonNode result = jsonNode.get("results").get(0);
        String address = result.get("formatted_address").asText();

        return Location.builder()
            .address(address)
            .latitude(lat)
            .longitude(lng)
            .timestamp(LocalDateTime.now())
            .build();
      } else {
        throw new RuntimeException(String.format("No results found for coordinates: %f, %f", lat, lng));
      }
    } catch (Exception e) {
      throw new RuntimeException("Error processing geocoding data: " + e.getMessage(), e);
    }
  }

}
