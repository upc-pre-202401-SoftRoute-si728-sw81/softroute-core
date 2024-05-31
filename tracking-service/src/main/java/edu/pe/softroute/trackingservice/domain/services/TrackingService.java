package edu.pe.softroute.trackingservice.domain.services;

import edu.pe.softroute.trackingservice.domain.models.entities.Location;
import edu.pe.softroute.trackingservice.domain.models.entities.Tracking;
import edu.pe.softroute.trackingservice.domain.models.entities.TrackingHistory;
import edu.pe.softroute.trackingservice.interfaces.dto.InitTrackingReq;
import java.util.List;

public interface TrackingService {

  List<TrackingHistory> getAllTrackingHistoryByTrackingNumber(String trackingNumber);

  Tracking getTrackingByTrackingNumber(String trackingNumber);

  Tracking initTracking(InitTrackingReq request);

  Tracking updateLocationByTrackingNumber(String trackingNumber, Location location);
}
