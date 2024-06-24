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
    double tempChange = 0.5 + (1.5 - 0.5) * random.nextDouble();
    double humidityChange = 0.5 + (1.5 - 0.5) * random.nextDouble();

    double temperature = packageToUpdate.getTemperature();
    double humidity = packageToUpdate.getHumidity();

    if (!breakCondition) {
      temperature += (random.nextBoolean() ? 1 : -1) * tempChange;
      humidity += (random.nextBoolean() ? 1 : -1) * humidityChange;

      if (temperature < minTemperature) temperature = minTemperature;
      if (temperature > maxTemperature) temperature = maxTemperature;
      if (humidity < minHumidity) humidity = minHumidity;
      if (humidity > maxHumidity) humidity = maxHumidity;
    } else {
      if (temperature >= minTemperature && temperature <= maxTemperature) {
        temperature += (temperature > (minTemperature + maxTemperature) / 2) ? tempChange + 5 : -tempChange - 5;
      } else {
        temperature += (temperature < minTemperature) ? -tempChange : tempChange;
      }

      if (humidity >= minHumidity && humidity <= maxHumidity) {
        humidity += (humidity > (minHumidity + maxHumidity) / 2) ? humidityChange + 5 : -humidityChange - 5;
      } else {
        humidity += (humidity < minHumidity) ? -humidityChange : humidityChange;
      }

      if (temperature >= minTemperature && temperature <= maxTemperature) {
        temperature = temperature > (minTemperature + maxTemperature) / 2 ? maxTemperature + 5 + random.nextDouble() * 5 : minTemperature - 5 - random.nextDouble() * 5;
      }
      if (humidity >= minHumidity && humidity <= maxHumidity) {
        humidity = humidity > (minHumidity + maxHumidity) / 2 ? maxHumidity + 5 + random.nextDouble() * 5 : minHumidity - 5 - random.nextDouble() * 5;
      }
    }

    packageToUpdate.setTemperature(temperature);
    packageToUpdate.setHumidity(humidity);

    return packageRepository.save(packageToUpdate);
  }

  @Override
  public List<Package> getPackagesByStatus(UUID companyId, PackageStatus status) {
    return packageRepository.findAllByCompanyIdAndShipmentStatus(companyId, status);
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
