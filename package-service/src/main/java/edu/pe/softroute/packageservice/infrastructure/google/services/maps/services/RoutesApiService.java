package edu.pe.softroute.packageservice.infrastructure.google.services.maps.services;

import edu.pe.softroute.packageservice.infrastructure.google.services.maps.decoders.Point;
import edu.pe.softroute.packageservice.infrastructure.google.services.maps.decoders.PolylineDecoder;
import edu.pe.softroute.packageservice.infrastructure.google.services.maps.dto.OriginDestination;
import edu.pe.softroute.packageservice.infrastructure.google.services.maps.dto.RouteOtherDestinationReq;
import edu.pe.softroute.packageservice.infrastructure.google.services.maps.dto.RouteResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RoutesApiService {

  @Value("${google.maps.api.key}")
  private String apiKey;

  private static final String URL = "https://routes.googleapis.com/directions/v2:computeRoutes";

  private final RestTemplate restTemplate;

  public Pair<String, List<Point>> getRouteWithMoreDestinations(String origin, String destination, List<String> others) {
    PolylineDecoder decoder = new PolylineDecoder();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("X-Goog-Api-Key", apiKey);
    headers.set("X-Goog-FieldMask", "routes.polyline.encodedPolyline");

    List<OriginDestination> intermediates = others.stream()
        .map(OriginDestination::new).toList();

    RouteOtherDestinationReq req = RouteOtherDestinationReq.builder()
        .origin(new OriginDestination(origin))
        .destination(new OriginDestination(destination))
        .intermediates(intermediates)
        .build();

    HttpEntity<RouteOtherDestinationReq> entity = new HttpEntity<>(req, headers);

    ResponseEntity<RouteResponse> response = restTemplate.exchange(
        URL,
        HttpMethod.POST,
        entity,
        RouteResponse.class
    );

    if (response.getBody() != null && !response.getBody().getRoutes().isEmpty()) {
      String polyline = response.getBody().getRoutes().get(0).getPolyline().getEncodedPolyline();
      List<Point> route = decoder.decode(polyline);
      return Pair.of(polyline, route);
    }

    return Pair.of(null, null);
  }

}
