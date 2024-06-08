package edu.pe.softroute.trackingservice.infrastructure.google.maps.services;

import edu.pe.softroute.trackingservice.domain.models.entities.Tracking;
import edu.pe.softroute.trackingservice.infrastructure.google.maps.decoders.Point;
import edu.pe.softroute.trackingservice.infrastructure.google.maps.decoders.PolylineDecoder;
import edu.pe.softroute.trackingservice.infrastructure.persistence.jpa.repositories.TrackingRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoutesApiService {

  @Value("${google.maps.api.key}")
  private String apiKey;

  private final TrackingRepository trackingRepository;

  public Point getCurrentStep(String trackingNumber) {
    Tracking tracking = trackingRepository.findByTrackingNumber(trackingNumber);

    PolylineDecoder polylineDecoder = new PolylineDecoder();
    List<Point> points = polylineDecoder.decode(tracking.getEncodedPolyline());
    int currentStep = tracking.getCurrentStep();

    tracking.setCurrentStep(tracking.getCurrentStep() + 1);
    trackingRepository.save(tracking);

    return points.get(currentStep);
  }
}
