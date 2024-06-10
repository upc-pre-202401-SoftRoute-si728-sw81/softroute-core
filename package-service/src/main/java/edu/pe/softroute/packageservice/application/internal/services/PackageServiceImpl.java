package edu.pe.softroute.packageservice.application.internal.services;

import edu.pe.softroute.packageservice.domain.exceptions.PackageNotFoundException;
import edu.pe.softroute.packageservice.domain.models.entities.Package;
import edu.pe.softroute.packageservice.domain.models.enums.PackageStatus;
import edu.pe.softroute.packageservice.domain.services.PackageService;
import edu.pe.softroute.packageservice.infrastructure.persistence.jpa.repositories.PackageRepository;
import edu.pe.softroute.packageservice.interfaces.dto.PackageReq;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PackageServiceImpl implements PackageService {

  private final PackageRepository packageRepository;

  @Override
  public Package create(UUID companyId, PackageReq request) {
    Package newPackage = fromReq(request, null);
    newPackage.setCompanyId(companyId);

    return packageRepository.save(newPackage);
  }

  @Override
  public Package update(UUID id, PackageReq request) {
    Package pkg = fromReq(request, id);

    return packageRepository.save(pkg);
  }

  @Override
  public List<Package> getAll(UUID companyId) {
    return packageRepository.findAllByCompanyId(companyId);
  }

  @Override
  public Package getById(UUID id) {
    return packageRepository.findById(id)
        .orElseThrow(() -> new PackageNotFoundException(id));
  }

  private Package fromReq(PackageReq request, UUID id) {
    Package pkg = new Package();

    if (id != null) {
      pkg = getById(id);
    }

    pkg.setDescription(request.getDescription());
    pkg.setLength(request.getLength());
    pkg.setWidth(request.getWidth());
    pkg.setHeight(request.getHeight());
    pkg.setWeight(request.getWeight());
    pkg.setOwnerId(request.getOwnerId());
    pkg.setStatus(PackageStatus.CREATED);

    return pkg;
  }
}
