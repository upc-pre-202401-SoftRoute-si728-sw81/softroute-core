package edu.pe.softroute.organizationservice.interfaces.transform;

import edu.pe.softroute.organizationservice.domain.models.entities.Client;
import edu.pe.softroute.organizationservice.interfaces.dto.ClientResponseDto;
import java.util.List;

public class ClientResponseDtoAssembler {
  public static ClientResponseDto assemble(Client resource) {
    return ClientResponseDto
        .builder()
        .id(resource.getId())
        .names(resource.getNames())
        .surnames(resource.getSurnames())
        .dni(resource.getDni())
        .email(resource.getEmail())
        .phoneNumber(resource.getPhoneNumber())
        .build();
  }

  public static List<ClientResponseDto> assemble(List<Client> resource) {
    return resource.stream().map(ClientResponseDtoAssembler::assemble).toList();
  }
}
