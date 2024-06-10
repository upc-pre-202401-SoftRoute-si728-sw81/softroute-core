package edu.pe.softroute.trackingservice.infrastructure.persistence.jpa.repositories;

import edu.pe.softroute.trackingservice.domain.models.entities.TrackingHistory;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingHistoryRepository extends JpaRepository<TrackingHistory, UUID> {

  List<TrackingHistory> findAllByTrackingId(UUID trackingId);
}
