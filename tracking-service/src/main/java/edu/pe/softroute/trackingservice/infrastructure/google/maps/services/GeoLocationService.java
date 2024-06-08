package edu.pe.softroute.trackingservice.infrastructure.google.maps.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.pe.softroute.trackingservice.domain.models.entities.Location;
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

  public Location getLocation(Double latitude, Double longitude) {
    String url = String.format(
        "https://maps.googleapis.com/maps/api/geocode/json?latlng=%f,%f&location_type=ROOFTOP&result_type=street_address&key=%s",
        latitude, longitude, apiKey);

    try {
      String response = restTemplate.getForObject(url, String.class);
      JsonNode jsonNode = objectMapper.readTree(response);

      if (jsonNode.has("results") && jsonNode.get("results").size() > 0) {
        JsonNode result = jsonNode.get("results").get(0);
        String street = extractStreet(result.get("address_components"));
        String district = extractComponent(result.get("address_components"), "locality");
        String province = extractComponent(result.get("address_components"), "administrative_area_level_2");
        String country = extractComponent(result.get("address_components"), "country");

        return Location.builder()
            .street(street)
            .district(district)
            .province(province)
            .country(country)
            .latitude(latitude)
            .longitude(longitude)
            .timestamp(LocalDateTime.now())
            .build();
      } else {
        throw new RuntimeException(String.format("No results found for coordinates: %.6f, %.6f", latitude, longitude));
      }
    } catch (Exception e) {
      throw new RuntimeException("Error processing geocoding data: " + e.getMessage(), e);
    }
  }

  private String extractStreet(JsonNode components) {
    String streetNumber = "";
    String route = "";

    for (JsonNode component : components) {
      JsonNode types = component.get("types");
      if (types != null) {
        for (JsonNode type : types) {
          if (type.asText().equals("street_number")) {
            streetNumber = component.get("long_name").asText();
          } else if (type.asText().equals("route")) {
            route = component.get("long_name").asText();
          }
        }
      }
    }

    return route + " " + streetNumber;
  }

  private String extractComponent(JsonNode components, String componentType) {
    for (JsonNode component : components) {
      JsonNode types = component.get("types");
      if (types != null) {
        for (JsonNode type : types) {
          if (type.asText().equals(componentType)) {
            return component.get("long_name").asText(); // Usa short_name para district
          }
        }
      }
    }
    return "";
  }
}
