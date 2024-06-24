package edu.pe.softroute.iamservice.interfaces.transform;

import edu.pe.softroute.iamservice.domain.models.entities.Role;
import edu.pe.softroute.iamservice.domain.models.entities.User;
import edu.pe.softroute.iamservice.interfaces.dto.UserResponseDto;
import java.util.List;

public class UserResponseDtoAssembler {
  public static UserResponseDto assemble(User user) {
    List<String> roles = user.getRoles().stream().map(Role::getStringName).toList();

    return UserResponseDto.builder()
        .id(user.getId())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .dni(user.getDni())
        .birthDate(user.getBirthDate())
        .phoneNumber(user.getPhoneNumber())
        .companyId(user.getCompanyId())
        .email(user.getEmail())
        .roles(roles)
        .build();
  }

  public static List<UserResponseDto> assemble(List<User> users) {
    return users.stream().map(UserResponseDtoAssembler::assemble).toList();
  }
}
