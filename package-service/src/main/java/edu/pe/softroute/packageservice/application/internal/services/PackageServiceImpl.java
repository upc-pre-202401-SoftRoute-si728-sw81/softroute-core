package edu.pe.softroute.packageservice.application.internal.services;

import edu.pe.softroute.packageservice.domain.exceptions.PackageNotFoundException;
import edu.pe.softroute.packageservice.domain.models.entities.Location;
import edu.pe.softroute.packageservice.domain.models.entities.Package;
import edu.pe.softroute.packageservice.domain.models.enums.PackageStatus;
import edu.pe.softroute.packageservice.domain.services.PackageService;
import edu.pe.softroute.packageservice.infrastructure.google.services.maps.services.GeoLocationService;
import edu.pe.softroute.packageservice.infrastructure.persistence.jpa.repositories.PackageRepository;
import edu.pe.softroute.packageservice.interfaces.dto.PackageReq;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PackageServiceImpl implements PackageService {

  private final GeoLocationService geoLocationService;

  private final PackageRepository packageRepository;

  @Override
  public Package create(UUID companyId, PackageReq request) {
    Package newPackage = fromReq(request, null);
    newPackage.setCompanyId(companyId);
    newPackage.setCode(generateCode());
    newPackage.setCreatedAt(LocalDateTime.now());
    newPackage.setHumidity(0.00);
    newPackage.setTemperature(0.00);
    newPackage.setBreakCondition(false);
    Location location = geoLocationService.getLocationByAddress(newPackage.getDestinationAddress());
    newPackage.setLatitude(location.getLatitude());
    newPackage.setLongitude(location.getLongitude());

    return packageRepository.save(newPackage);
  }

  @Override
  public List<Package> getAllPackagesByShipmentId(UUID shipmentId) {
    return packageRepository.findAllByShipmentId(shipmentId);
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
  public Package updateTemperatureAndHumidity(String code, Double humidityFrom, Double temperatureFrom) {
    Package packageToUpdate = packageRepository.findByCode(code);

    if (packageToUpdate == null) {
      throw new IllegalArgumentException("Package not found with code: " + code);
    }

    boolean breakCondition = packageToUpdate.getBreakCondition();

    double minTemperature = packageToUpdate.getMinTemperature();
    double maxTemperature = packageToUpdate.getMaxTemperature();
    double minHumidity = packageToUpdate.getMinHumidity();
    double maxHumidity = packageToUpdate.getMaxHumidity();

    Random random = new Random();
    double tempChange = 0.1 + (1.0 - 0.1) * random.nextDouble();  // Cambios más pequeños y precisos
    double humidityChange = 0.1 + (1.0 - 0.1) * random.nextDouble();

    double temperature = packageToUpdate.getTemperature();
    double humidity = packageToUpdate.getHumidity();

    if (!breakCondition) {
      temperature += (random.nextBoolean() ? tempChange : -tempChange);
      humidity += (random.nextBoolean() ? humidityChange : -humidityChange);
    } else {
      temperature += (random.nextBoolean() ? tempChange + 2 : -tempChange - 2);
      humidity += (random.nextBoolean() ? humidityChange + 2 : -humidityChange - 2);
    }

    temperature = Math.min(Math.max(temperature, minTemperature), maxTemperature);
    humidity = Math.min(Math.max(humidity, minHumidity), maxHumidity);

    packageToUpdate.setTemperature(temperature);
    packageToUpdate.setHumidity(humidity);

    return packageRepository.save(packageToUpdate);
  }

  @Override
  public List<Package> getPackagesByStatus(UUID companyId, PackageStatus status) {
    return packageRepository.findAllByCompanyIdAndShipmentStatus(companyId, status);
  }

  @Override
  public void switchBreakCondition(String packageCode) {
    Package packageTo = packageRepository.findByCode(packageCode);

    packageTo.switchBreakCondition();

    packageRepository.save(packageTo);
  }

  @Override
  public Package getById(UUID id) {
    return packageRepository.findById(id)
        .orElseThrow(() -> new PackageNotFoundException(id));
  }

  @Override
  public Package getByCode(String code) {
    return packageRepository.findByCode(code);
  }

  private String generateCode() {
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    int length = 5;
    Random random = new SecureRandom();
    StringBuilder code = new StringBuilder(length);

    for (int i = 0; i < length; i++) {
      code.append(characters.charAt(random.nextInt(characters.length())));
    }

    return code.toString();
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
    pkg.setCustomerId(request.getCustomerId());
    pkg.setMinHumidity(request.getMinHumidity());
    pkg.setMaxHumidity(request.getMaxHumidity());
    pkg.setMinTemperature(request.getMinTemperature());
    pkg.setMaxTemperature(request.getMaxTemperature());
    pkg.setDestinationAddress(request.getDestinationAddress());
    pkg.setStatus(PackageStatus.CREATED);

    return pkg;
  }
}
