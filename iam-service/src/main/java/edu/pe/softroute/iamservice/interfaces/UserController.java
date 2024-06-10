package edu.pe.softroute.iamservice.interfaces;

import edu.pe.softroute.iamservice.domain.services.UserService;
import edu.pe.softroute.iamservice.interfaces.dto.UserDto;
import edu.pe.softroute.iamservice.interfaces.transform.UserDtoFromEntityAssembler;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PutMapping("{id}/assignCompany")
  public ResponseEntity<UserDto> assignCompany(@PathVariable UUID id, @RequestParam UUID companyId) {
    UserDto userDto = UserDtoFromEntityAssembler.toDtoFromEntity(userService.assignCompany(id, companyId));

    return new ResponseEntity<>(userDto, HttpStatus.OK);
  }

  @GetMapping("/hello")
  public String hello() {
    return "Hello World!";
  }

}
