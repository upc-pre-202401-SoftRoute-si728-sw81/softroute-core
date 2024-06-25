package edu.pe.softroute.packageservice.application.internal.services;

import edu.pe.softroute.packageservice.domain.exceptions.ShipmentNotFoundException;
import edu.pe.softroute.packageservice.domain.models.entities.Location;
import edu.pe.softroute.packageservice.domain.models.entities.Package;
import edu.pe.softroute.packageservice.domain.models.entities.Shipment;
import edu.pe.softroute.packageservice.domain.models.enums.PackageStatus;
import edu.pe.softroute.packageservice.domain.models.enums.ShipmentStatus;
import edu.pe.softroute.packageservice.domain.services.ShipmentService;
import edu.pe.softroute.packageservice.infrastructure.google.services.maps.decoders.Point;
import edu.pe.softroute.packageservice.infrastructure.google.services.maps.decoders.PolylineDecoder;
import edu.pe.softroute.packageservice.infrastructure.google.services.maps.services.GeoLocationService;
import edu.pe.softroute.packageservice.infrastructure.google.services.maps.services.RoutesApiService;
import edu.pe.softroute.packageservice.infrastructure.persistence.jpa.repositories.PackageRepository;
import edu.pe.softroute.packageservice.infrastructure.persistence.jpa.repositories.ShipmentRepository;
import edu.pe.softroute.packageservice.interfaces.dto.ShipmentReq;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.Pair;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {


  private final RoutesApiService routesApiService;

  private final GeoLocationService geoLocationService;

  private final PackageRepository packageRepository;

  private final ShipmentRepository shipmentRepository;

  @Override
  public Shipment create(UUID companyId, ShipmentReq req) {

    Shipment shipment = new Shipment();
    shipment.setCode(generateCode());
    shipment.setCarrierId(req.getCarrierId());
    shipment.setCreatedAt(LocalDateTime.now());
    shipment.setCompanyId(companyId);
    shipment.setPackagesDelivered(0);
    shipment.setStatus(ShipmentStatus.CREATED);
    Shipment shipmentSaved = shipmentRepository.save(shipment);

    List<Package> packages = packageRepository.findAllById(req.getPackagesIds());
    packages.forEach(pkg -> pkg.setShipment(shipmentSaved));
    shipment.setPackages(packages);
    shipment.setNumPackages(packages.size());

    return shipmentRepository.save(shipment);
  }

  @Override
  public List<Shipment> getAll(UUID companyId) {
    List<Shipment> shipments = shipmentRepository.findAll(Sort.by(Direction.DESC, "createdAt"));

    return shipments.stream().filter( s -> s.getCompanyId() == companyId).toList();
  }

  @Override
  public Shipment getById(UUID id) {
    return shipmentRepository.findById(id)
        .orElseThrow(() -> new ShipmentNotFoundException(id));
  }

  @Override
  public Shipment startShipment(UUID id) {
    Shipment shipment = getById(id);

    shipment.setStatus(ShipmentStatus.IN_PROGRESS);
    shipment.setStartDate(LocalDateTime.now());

    List<Package> packages = packageRepository.findAllByShipmentId(shipment.getId());
    List<String> locations = new ArrayList<>(packages.stream()
        .map(Package::getDestinationAddress).toList());

    String origin = "Prolongación Primavera 2390, Santiago de Surco 15023";
    String destination = locations.get(0);
    locations.remove(0);

    Pair<String, List<Point>> result = routesApiService.getRouteWithMoreDestinations(origin, destination, locations);
    List<Point> routes = result.getRight();
    String encodedPolyline = result.getLeft();

    shipment.setEncodedPolyline(encodedPolyline);
    shipment.setLastStep(routes.size());

    Location currentLocation = geoLocationService.getLocationByAddress(origin);
    shipment.updateCurrentLocation(currentLocation);
    shipment.setCurrentStep(0);

    packages.forEach(pkg -> pkg.setStatus(PackageStatus.IN_TRANSIT));
    shipment.setPackages(packages);

    return shipmentRepository.save(shipment);
  }

  @Override
  public Shipment updateLocationByCode(String code, Location location) {
    Shipment shipment = shipmentRepository.findByCode(code);
    shipment.updateCurrentLocation(location);

    return shipmentRepository.save(shipment);
  }

  @Override
  public String checkoutPackageDelivered(String shipmentCode) {
    Shipment shipment = shipmentRepository.findByCode(shipmentCode);

    if (shipment == null) {
      return null;
    }

    final double NEAR_THRESHOLD = 1.0; // 1 kilómetro
    final double DELIVERED_THRESHOLD = 0.05; // 30 metros

    List<Package> packages = shipment.getPackages();

    for (Package pkg : packages) {
      if (pkg.getStatus().equals(PackageStatus.IN_TRANSIT)) {
        double distance = calculateDistance(
            shipment.getLatitude(), shipment.getLongitude(),
            pkg.getLatitude(), pkg.getLongitude());
        if (distance <= DELIVERED_THRESHOLD) {
          System.out.println(String.format("Package with code %s is Delivered with %f distance", pkg.getCode(), distance));
          Package packageDelivered = packageRepository.findByCode(pkg.getCode());
          packageDelivered.setStatus(PackageStatus.DELIVERED);


          if (shipment.getPackagesDelivered() < shipment.getNumPackages()) {
            shipment.setPackagesDelivered(shipment.getPackagesDelivered() + 1);

            if (shipment.getPackagesDelivered() == shipment.getNumPackages()) {
              shipment.setStatus(ShipmentStatus.COMPLETED);
            }
            shipmentRepository.save(shipment);
          }

          return packageRepository.save(packageDelivered).getCode();
        }
      }
    }

    return "";
  }

  @Override
  public Shipment move(String shipmentCode, Double lat, Double lng) {
    Shipment shipment = shipmentRepository.findByCode(shipmentCode);
    PolylineDecoder polylineDecoder = new PolylineDecoder();
    List<Point> points = polylineDecoder.decode(shipment.getEncodedPolyline());

    if (shipment.getCurrentStep() >= points.size()) {
      return shipment;
    }

    Point currentPoint = points.get(shipment.getCurrentStep());
    Location location = geoLocationService.getLocationByLatLng(currentPoint.getLat(), currentPoint.getLng());
    shipment.updateCurrentLocation(location);

    if (shipment.getCurrentStep() < points.size()) {
      shipment.setCurrentStep(shipment.getCurrentStep() + 1);
    }

    return shipmentRepository.save(shipment);
  }

  private String generateCode() {
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    int length = 7;
    Random random = new SecureRandom();
    StringBuilder code = new StringBuilder(length);

    for (int i = 0; i < length; i++) {
      code.append(characters.charAt(random.nextInt(characters.length())));
    }

    return code.toString();
  }

  private double calculateDistance(Double lat1, Double lon1, Double lat2, Double lon2) {
    final int R = 6371;
    double dLat = Math.toRadians(lat2 - lat1);
    double dLon = Math.toRadians(lon2 - lon1);
    lat1 = Math.toRadians(lat1);
    lat2 = Math.toRadians(lat2);

    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.sin(dLon / 2) * Math.sin(dLon / 2) *
            Math.cos(lat1) * Math.cos(lat2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    return R * c;
  }

}
