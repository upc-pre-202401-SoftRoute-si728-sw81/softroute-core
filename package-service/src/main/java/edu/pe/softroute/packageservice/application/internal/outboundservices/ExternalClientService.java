package edu.pe.softroute.packageservice.application.internal.outboundservices;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ORGANIZATION-SERVICE")
public interface ExternalOrganizationService {
}
