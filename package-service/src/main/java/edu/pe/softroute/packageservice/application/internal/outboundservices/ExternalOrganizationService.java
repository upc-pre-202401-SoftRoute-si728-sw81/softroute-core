package edu.pe.softroute.packageservice.application.internal.outboundservices;

import edu.pe.softroute.packageservice.application.internal.outboundservices.dto.ClientResponseDto;
import edu.pe.softroute.packageservice.application.internal.outboundservices.dto.EmployeeResponseDto;
import java.util.List;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "ORGANIZATION-SERVICE")
public interface ExternalOrganizationService {

  @GetMapping("/api/v1/clients")
  List<ClientResponseDto> getClients(@RequestHeader String companyId);

  @GetMapping("/api/v1/clients/{id}")
  ClientResponseDto getClientById(@PathVariable UUID id);

  @GetMapping("/api/v1/employees")
  List<EmployeeResponseDto> getAllEmployees(@RequestHeader String companyId);

  @GetMapping("/api/v1/employees/{id}")
  EmployeeResponseDto getEmployeeById(@PathVariable UUID id);

}
