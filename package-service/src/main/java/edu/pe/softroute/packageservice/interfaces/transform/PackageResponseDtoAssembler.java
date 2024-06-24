package edu.pe.softroute.packageservice.interfaces.transform;

import edu.pe.softroute.packageservice.application.internal.outboundservices.dto.ClientResponseDto;
import edu.pe.softroute.packageservice.domain.models.entities.Package;
import edu.pe.softroute.packageservice.domain.models.entities.PackageDetails;
import edu.pe.softroute.packageservice.domain.models.entities.PackageOwner;
import edu.pe.softroute.packageservice.interfaces.dto.PackageResponseDto;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PackageResponseDtoAssembler {
  public static PackageResponseDto assemble(Package resource, ClientResponseDto client) {
    return PackageResponseDto.builder()
        .id(resource.getId())
        .code(resource.getCode())
        .humidity(resource.getHumidity())
        .temperature(resource.getTemperature())
        .description(resource.getDescription())
        .createdAt(resource.getCreatedAt())
        .destinationAddress(resource.getDestinationAddress())
        .status(resource.getStatus())
        .details(PackageDetails.builder()
            .height(resource.getHeight())
            .weight(resource.getWeight())
            .length(resource.getLength())
            .width(resource.getWidth())
            .build())
        .owner(PackageOwner.builder()
            .names(client.getNames())
            .surnames(client.getSurnames())
            .dni(client.getDni())
            .email(client.getEmail())
            .phoneNumber(client.getPhoneNumber())
            .build())
        .build();
  }

  public static PackageResponseDto assemble(Package resource) {
    return PackageResponseDto.builder()
        .id(resource.getId())
        .code(resource.getCode())
        .humidity(resource.getHumidity())
        .temperature(resource.getTemperature())
        .destinationAddress(resource.getDestinationAddress())
        .createdAt(resource.getCreatedAt())
        .description(resource.getDescription())
        .status(resource.getStatus())
        .details(PackageDetails.builder()
            .height(resource.getHeight())
            .weight(resource.getWeight())
            .length(resource.getLength())
            .width(resource.getWidth())
            .build())
        .build();
  }

  public static List<PackageResponseDto> assemble(List<Package> resource, Map<UUID, ClientResponseDto> clients) {
    return resource.stream().map(r -> assemble(r, clients.get(r.getCustomerId()))).toList();
  }
}
