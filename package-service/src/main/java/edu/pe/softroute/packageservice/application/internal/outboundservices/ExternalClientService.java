package edu.pe.softroute.packageservice.application.internal.outboundservices;

import edu.pe.softroute.packageservice.application.internal.outboundservices.dto.ClientResponseDto;
import java.util.List;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "ORGANIZATION-SERVICE")
public interface ExternalClientService {

  @GetMapping("/api/v1/organization/clients")
  List<ClientResponseDto> getClients(@RequestHeader String companyId);

  @GetMapping("/api/v1/organization/clients/{id}")
  ClientResponseDto getClientById(@PathVariable UUID id);

}
