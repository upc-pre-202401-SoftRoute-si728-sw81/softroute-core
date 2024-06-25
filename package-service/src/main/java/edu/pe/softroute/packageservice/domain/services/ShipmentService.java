package edu.pe.softroute.packageservice.domain.services;

import edu.pe.softroute.packageservice.domain.models.entities.Location;
import edu.pe.softroute.packageservice.domain.models.entities.Shipment;
import edu.pe.softroute.packageservice.interfaces.dto.ShipmentReq;
import java.util.List;
import java.util.UUID;

public interface ShipmentService {

  Shipment create(UUID companyId, ShipmentReq req);

  List<Shipment> getAll(UUID companyId);

  Shipment getById(UUID id);

  Shipment startShipment(UUID id);

  Shipment updateLocationByCode(String code, Location location);

  String checkoutPackageDelivered(String shipmentCode);

  Shipment move(String shipmentCode, Double lat, Double lng);
}
