package edu.pe.softroute.trackingservice.infrastructure.persistence.jpa.repositories;

import edu.pe.softroute.trackingservice.domain.models.entities.Tracking;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingRepository extends JpaRepository<Tracking, UUID> {

  Tracking findByTrackingNumber(String trackingNumber);

  boolean existsByTrackingNumber(String trackingNumber);
}
