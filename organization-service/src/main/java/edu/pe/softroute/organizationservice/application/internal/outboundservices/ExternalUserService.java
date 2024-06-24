package edu.pe.softroute.organizationservice.application.internal.outboundservices;

import edu.pe.softroute.organizationservice.application.internal.outboundservices.dto.UserResponseDto;
import java.util.List;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "IAM-SERVICE")
public interface ExternalUserService {

  @GetMapping("/api/v1/users/{id}")
  UserResponseDto getById(@PathVariable UUID id);

  @GetMapping("/api/v1/users")
  List<UserResponseDto> getAll(@RequestHeader String companyId);
}
