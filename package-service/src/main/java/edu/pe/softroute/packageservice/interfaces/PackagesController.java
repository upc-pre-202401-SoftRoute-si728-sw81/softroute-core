package edu.pe.softroute.packageservice.interfaces;

import edu.pe.softroute.packageservice.application.internal.outboundservices.ExternalOrganizationService;
import edu.pe.softroute.packageservice.application.internal.outboundservices.dto.ClientResponseDto;
import edu.pe.softroute.packageservice.domain.models.entities.Package;
import edu.pe.softroute.packageservice.domain.models.enums.PackageStatus;
import edu.pe.softroute.packageservice.domain.services.PackageService;
import edu.pe.softroute.packageservice.interfaces.dto.PackageReq;
import edu.pe.softroute.packageservice.interfaces.dto.PackageResponseDto;
import edu.pe.softroute.packageservice.interfaces.transform.PackageResponseDtoAssembler;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/packages")
@RequiredArgsConstructor
public class PackagesController {

  private final PackageService packageService;

  private final ExternalOrganizationService externalClientService;

  @PostMapping
  public ResponseEntity<PackageResponseDto> createPackage(@RequestHeader String companyId, @Valid @RequestBody PackageReq request) {
    ClientResponseDto clientResponse = externalClientService.getClientById(request.getCustomerId());
    Package resource = packageService.create(UUID.fromString(companyId), request);
    PackageResponseDto response = PackageResponseDtoAssembler.assemble(resource, clientResponse);

    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PutMapping("{id}")
  public ResponseEntity<Package> updatePackage(@PathVariable UUID id, @Valid @RequestBody PackageReq request) {
    return new ResponseEntity<>(packageService.update(id, request), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<PackageResponseDto>> getPackages(@RequestHeader String companyId, @RequestParam(required = false) PackageStatus status) {
    List<Package> resource;

    if (status != null) {
      resource = packageService.getPackagesByStatus(UUID.fromString(companyId), status);
    } else {
      resource = packageService.getAll(UUID.fromString(companyId));
    }
    List<UUID> ids = resource.stream().map(Package::getCustomerId).toList();
    List<ClientResponseDto> clientResponse = externalClientService.getClients(companyId).stream().filter(c -> ids.contains(c.getId())).toList();
    Map<UUID, ClientResponseDto> resourceMap = clientResponse.stream().collect(Collectors.toMap(ClientResponseDto::getId, c -> c));
    List<PackageResponseDto> response = PackageResponseDtoAssembler.assemble(resource, resourceMap);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<PackageResponseDto> getPackageById(@PathVariable UUID id) {
    Package resource = packageService.getById(id);
    ClientResponseDto clientResponseDto = externalClientService.getClientById(resource.getCustomerId());
    PackageResponseDto response = PackageResponseDtoAssembler.assemble(resource, clientResponseDto);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PutMapping("{code}/break")
  public void breakPackage(@PathVariable String code) {
    packageService.switchBreakCondition(code);
  }
}
