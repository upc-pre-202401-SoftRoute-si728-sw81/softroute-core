package edu.pe.softroute.trackingservice.interfaces;

import edu.pe.softroute.trackingservice.domain.models.entities.Tracking;
import edu.pe.softroute.trackingservice.domain.models.entities.TrackingHistory;
import edu.pe.softroute.trackingservice.domain.services.TrackingService;
import edu.pe.softroute.trackingservice.interfaces.dto.InitTrackingReq;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tracking")
public class TrackingController {

  @Autowired
  TrackingService trackingService;

  @PostMapping
  public Tracking initTracking(@RequestBody InitTrackingReq req) {
    return trackingService.initTracking(req);
  }

  @GetMapping("{trackingNumber}")
  public Tracking getTrackingByTrackingNumber(@PathVariable String trackingNumber) {
    return trackingService.getTrackingByTrackingNumber(trackingNumber);
  }

  @GetMapping("{trackingNumber}/history")
  public List<TrackingHistory> getAllTrackingHistoryByTrackingNumber(@PathVariable String trackingNumber) {
    return trackingService.getAllTrackingHistoryByTrackingNumber(trackingNumber);
  }

}
