package edu.pe.softroute.iamservice.interfaces.transform;

import edu.pe.softroute.iamservice.domain.models.entities.User;
import edu.pe.softroute.iamservice.interfaces.dto.AuthenticatedUserDto;

public class AuthenticatedUserDtoFromEntityAssembler {
  public static AuthenticatedUserDto toDtoFromEntity(User user, String token) {
    return AuthenticatedUserDto.builder()
        .id(user.getId())
        .email(user.getEmail())
        .token(token)
        .build();
  }
}
