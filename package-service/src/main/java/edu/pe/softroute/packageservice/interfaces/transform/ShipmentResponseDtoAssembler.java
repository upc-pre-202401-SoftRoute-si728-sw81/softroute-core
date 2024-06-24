package edu.pe.softroute.packageservice.interfaces.transform;

import edu.pe.softroute.packageservice.application.internal.outboundservices.dto.EmployeeResponseDto;
import edu.pe.softroute.packageservice.domain.models.entities.Location;
import edu.pe.softroute.packageservice.domain.models.entities.Shipment;
import edu.pe.softroute.packageservice.domain.models.entities.ShipmentCarrier;
import edu.pe.softroute.packageservice.infrastructure.google.services.maps.decoders.Point;
import edu.pe.softroute.packageservice.interfaces.dto.ShipmentResponseDto;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ShipmentResponseDtoAssembler {
  public static ShipmentResponseDto assemble(Shipment resource, EmployeeResponseDto employee) {
    List<Point> destinations = resource.getPackages().stream()
        .map(p -> new Point(p.getLatitude(), p.getLongitude())).toList();

    return ShipmentResponseDto.builder()
        .id(resource.getId())
        .code(resource.getCode())
        .encodedPolyline(resource.getEncodedPolyline())
        .createdAt(resource.getCreatedAt())
        .updatedAt(resource.getUpdatedAt())
        .destinations(destinations)
        .location(Location.builder()
            .latitude(resource.getLatitude())
            .longitude(resource.getLongitude())
            .address(resource.getAddress())
            .timestamp(resource.getTimestamp())
            .build())
        .carrier( ShipmentCarrier.builder()
            .id(employee.getId())
            .firstName(employee.getFirstName())
            .lastName(employee.getLastName())
            .dni(employee.getDni())
            .email(employee.getEmail())
            .phoneNumber(employee.getPhoneNumber())
            .build())
        .numPackages(resource.getNumPackages())
        .status(resource.getStatus())
        .build();
  }

  public static ShipmentResponseDto assemble(Shipment resource) {
    return ShipmentResponseDto.builder()
        .id(resource.getId())
        .code(resource.getCode())
        .location(Location.builder()
            .latitude(resource.getLatitude())
            .longitude(resource.getLongitude())
            .timestamp(resource.getTimestamp())
            .build())
        .numPackages(resource.getNumPackages())
        .status(resource.getStatus())
        .build();
  }

  public static List<ShipmentResponseDto> assemble(List<Shipment> shipments, Map<UUID, EmployeeResponseDto> employees) {
    return shipments.stream().map(shipment -> assemble(shipment, employees.get(shipment.getCarrierId()))).toList();
  }
}
