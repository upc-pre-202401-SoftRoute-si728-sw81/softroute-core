package edu.pe.softroute.packageservice.domain.services;

import edu.pe.softroute.packageservice.domain.models.entities.Package;
import edu.pe.softroute.packageservice.interfaces.dto.PackageReq;
import java.util.List;
import java.util.UUID;

public interface PackageService {

  Package create(UUID companyId, PackageReq request);

  Package update(UUID id, PackageReq request);

  Package getById(UUID id);

  List<Package> getAll(UUID companyId);

}
