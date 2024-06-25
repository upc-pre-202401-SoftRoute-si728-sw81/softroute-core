package edu.pe.softroute.packageservice.interfaces;

import edu.pe.softroute.packageservice.application.internal.outboundservices.ExternalOrganizationService;
import edu.pe.softroute.packageservice.application.internal.outboundservices.dto.ClientResponseDto;
import edu.pe.softroute.packageservice.application.internal.outboundservices.dto.EmployeeResponseDto;
import edu.pe.softroute.packageservice.domain.models.entities.Package;
import edu.pe.softroute.packageservice.domain.models.entities.Shipment;
import edu.pe.softroute.packageservice.domain.services.PackageService;
import edu.pe.softroute.packageservice.domain.services.ShipmentService;
import edu.pe.softroute.packageservice.interfaces.dto.PackageResponseDto;
import edu.pe.softroute.packageservice.interfaces.dto.ShipmentReq;
import edu.pe.softroute.packageservice.interfaces.dto.ShipmentResponseDto;
import edu.pe.softroute.packageservice.interfaces.transform.PackageResponseDtoAssembler;
import edu.pe.softroute.packageservice.interfaces.transform.ShipmentResponseDtoAssembler;
import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/shipments")
@RequiredArgsConstructor
public class ShipmentsController {

  private final ExternalOrganizationService externalClientService;
  private final ExternalOrganizationService externalEmployeeService;
  private final PackageService packageService;
  private final ShipmentService shipmentService;

  @GetMapping
  public ResponseEntity<List<ShipmentResponseDto>> getAllShipments(@RequestHeader String companyId) {
    List<Shipment> shipments = shipmentService.getAll(UUID.fromString(companyId));
    List<UUID> ids = shipments.stream().map(Shipment::getCarrierId).toList();
    List<EmployeeResponseDto> employeeResponse = externalEmployeeService.getAllEmployees(companyId).stream().filter(e -> ids.contains(e.getId())).toList();
    Map<UUID, EmployeeResponseDto> resourceMap = employeeResponse.stream().collect(Collectors.toMap(EmployeeResponseDto::getId, c -> c));
    List<ShipmentResponseDto> response = ShipmentResponseDtoAssembler.assemble(shipments, resourceMap);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<ShipmentResponseDto> getShipmentById(@PathVariable UUID id) {
    Shipment shipment = shipmentService.getById(id);
    EmployeeResponseDto employeeResponse = externalEmployeeService.getEmployeeById(shipment.getCarrierId());
    ShipmentResponseDto response = ShipmentResponseDtoAssembler.assemble(shipment, employeeResponse);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("{id}/packages")
  public ResponseEntity<List<PackageResponseDto>> getAllPackages(@RequestHeader String companyId, @PathVariable UUID id) {
    List<Package> resource = packageService.getAllPackagesByShipmentId(id);
    List<UUID> ids = resource.stream().map(Package::getCustomerId).toList();
    List<ClientResponseDto> clientResponse = externalClientService.getClients(companyId).stream().filter(c -> ids.contains(c.getId())).toList();
    Map<UUID, ClientResponseDto> resourceMap = clientResponse.stream().collect(Collectors.toMap(ClientResponseDto::getId, c -> c));
    List<PackageResponseDto> response = PackageResponseDtoAssembler.assemble(resource, resourceMap);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("{id}/start")
  public ResponseEntity<ShipmentResponseDto> startShipment(@PathVariable UUID id) {
    Shipment shipment = shipmentService.startShipment(id);
    EmployeeResponseDto employeeResponse = externalEmployeeService.getEmployeeById(shipment.getCarrierId());
    ShipmentResponseDto response = ShipmentResponseDtoAssembler.assemble(shipment, employeeResponse);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ShipmentResponseDto> createShipment(@RequestHeader String companyId, @Valid @RequestBody ShipmentReq req) {
    EmployeeResponseDto employeeResponse = externalEmployeeService.getEmployeeById(req.getCarrierId());
    Shipment shipment = shipmentService.create(UUID.fromString(companyId), req);
    ShipmentResponseDto response = ShipmentResponseDtoAssembler.assemble(shipment, employeeResponse);

    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }
}
