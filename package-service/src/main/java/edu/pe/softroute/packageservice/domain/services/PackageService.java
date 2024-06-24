package edu.pe.softroute.packageservice.domain.services;

import edu.pe.softroute.packageservice.domain.models.entities.Package;
import edu.pe.softroute.packageservice.domain.models.enums.PackageStatus;
import edu.pe.softroute.packageservice.interfaces.dto.PackageReq;
import java.util.List;
import java.util.UUID;

public interface PackageService {

  Package create(UUID companyId, PackageReq request);

  List<Package> getAllPackagesByShipmentId(UUID shipmentId);

  Package update(UUID id, PackageReq request);

  Package getById(UUID id);

  Package getByCode(String code);

  List<Package> getAll(UUID companyId);

  Package updateTemperatureAndHumidity(String code, Double humidityFrom, Double temperatureFrom);

  List<Package> getPackagesByStatus(UUID companyID, PackageStatus status);
}
