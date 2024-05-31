package edu.pe.softroute.trackingservice.application.internal.services;

import edu.pe.softroute.trackingservice.application.internal.outboundservices.dto.AssignTrackingNumberReq;
import edu.pe.softroute.trackingservice.domain.models.entities.Location;
import edu.pe.softroute.trackingservice.domain.models.entities.Tracking;
import edu.pe.softroute.trackingservice.domain.models.entities.TrackingHistory;
import edu.pe.softroute.trackingservice.domain.services.TrackingService;
import edu.pe.softroute.trackingservice.infrastructure.persistence.jpa.repositories.TrackingRepository;
import edu.pe.softroute.trackingservice.infrastructure.persistence.jpa.repositories.TrackingHistoryRepository;
import edu.pe.softroute.trackingservice.interfaces.dto.InitTrackingReq;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TrackingServiceImpl implements TrackingService {

  @Autowired
  TrackingRepository trackingRepository;

  @Autowired
  TrackingHistoryRepository trackingHistoryRepository;

  @Override
  public List<TrackingHistory> getAllTrackingHistoryByTrackingNumber(String trackingNumber) {

    Tracking tracking = trackingRepository.findByTrackingNumber(trackingNumber);

    return trackingHistoryRepository.findAllByTrackingId(tracking.getId());
  }

  @Override
  public Tracking getTrackingByTrackingNumber(String trackingNumber) {
    return trackingRepository.findByTrackingNumber(trackingNumber);
  }

  @Override
  public Tracking initTracking(InitTrackingReq request) {

    Tracking tracking = Tracking.builder()
        .trackingNumber(generateTrackingNumber())
        .build();

    Tracking trackingSaved = trackingRepository.save(tracking);

    RestTemplate restTemplate = new RestTemplate();
    String baseUrl = "http://localhost:8081/api/v1/packages/" + request.getPackageId() + "/tracking-number";

    AssignTrackingNumberReq req = AssignTrackingNumberReq.builder()
        .trackingNumber(trackingSaved.getTrackingNumber()).build();

    restTemplate.put(baseUrl, req, String.class);

    return trackingSaved;
  }

  @Override
  public Tracking updateLocationByTrackingNumber(String trackingNumber, Location location) {

    Tracking tracking = trackingRepository.findByTrackingNumber(trackingNumber);

    tracking.setLocation(location);

    return trackingRepository.save(tracking);
  }

  private String generateTrackingNumber() {
    Random random = new Random();
    String trackingNumber;

    do {
      int randomNumber = random.nextInt((int) Math.pow(10, 4));
      trackingNumber = "TRK" + String.format("%04d", randomNumber);

    } while (trackingRepository.existsByTrackingNumber(trackingNumber));

    return trackingNumber;
  }

}
