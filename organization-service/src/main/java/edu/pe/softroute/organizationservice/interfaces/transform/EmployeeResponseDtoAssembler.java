package edu.pe.softroute.organizationservice.interfaces.transform;

import edu.pe.softroute.organizationservice.application.internal.outboundservices.dto.UserResponseDto;
import edu.pe.softroute.organizationservice.domain.models.entities.Employee;
import edu.pe.softroute.organizationservice.interfaces.dto.EmployeeResponseDto;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class EmployeeResponseDtoAssembler {
  public static EmployeeResponseDto assemble(Employee resource, UserResponseDto user) {
    return EmployeeResponseDto.builder()
        .id(resource.getId())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .dni(user.getDni())
        .email(user.getEmail())
        .phoneNumber(user.getPhoneNumber())
        .build();
  }

  public static List<EmployeeResponseDto> assemble(List<Employee> resource, Map<UUID, UserResponseDto> clients) {
    return resource.stream().map(r -> assemble(r, clients.get(r.getUserId()))).toList();
  }
}
