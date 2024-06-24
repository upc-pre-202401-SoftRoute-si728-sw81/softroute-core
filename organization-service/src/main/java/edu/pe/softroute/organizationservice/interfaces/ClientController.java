package edu.pe.softroute.organizationservice.interfaces;

import edu.pe.softroute.organizationservice.domain.services.ClientService;
import edu.pe.softroute.organizationservice.interfaces.dto.ClientResponseDto;
import edu.pe.softroute.organizationservice.interfaces.transform.ClientResponseDtoAssembler;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientController {

  private final ClientService clientService;

  @GetMapping
  public ResponseEntity<List<ClientResponseDto>> getAllClients(@RequestHeader String companyId) {
    List<ClientResponseDto> response = ClientResponseDtoAssembler
        .assemble(clientService.getAll(UUID.fromString(companyId)));

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<ClientResponseDto> getClientById(@PathVariable UUID id) {
    ClientResponseDto response = ClientResponseDtoAssembler.assemble(clientService.getById(id));

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
