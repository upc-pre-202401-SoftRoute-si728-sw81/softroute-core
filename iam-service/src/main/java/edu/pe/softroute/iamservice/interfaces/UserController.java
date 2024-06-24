package edu.pe.softroute.iamservice.interfaces;

import edu.pe.softroute.iamservice.domain.models.entities.User;
import edu.pe.softroute.iamservice.domain.services.UserService;
import edu.pe.softroute.iamservice.interfaces.dto.UserResponseDto;
import edu.pe.softroute.iamservice.interfaces.transform.UserResponseDtoAssembler;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PutMapping("{id}/assignCompany")
  public ResponseEntity<UserResponseDto> assignCompany(@PathVariable UUID id, @RequestParam UUID companyId) {
    UserResponseDto userResponseDto = UserResponseDtoAssembler.assemble(userService.assignCompany(id, companyId));

    return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<UserResponseDto>> getAllUsers(@RequestHeader String companyId) {
    List<UserResponseDto> response = UserResponseDtoAssembler.assemble(userService.getAllUsersByCompanyId(UUID.fromString(companyId)));

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<UserResponseDto> getById(@PathVariable UUID id) {
    UserResponseDto response = UserResponseDtoAssembler.assemble(userService.getById(id));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/hello")
  public String hello() {
    return "Hello World!";
  }

}
