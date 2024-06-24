package edu.pe.softroute.iamservice.interfaces;

import edu.pe.softroute.iamservice.domain.models.entities.User;
import edu.pe.softroute.iamservice.domain.services.AuthService;
import edu.pe.softroute.iamservice.infrastructure.jwt.models.JwtInfoDto;
import edu.pe.softroute.iamservice.interfaces.dto.AuthenticatedUserDto;
import edu.pe.softroute.iamservice.interfaces.dto.SignInRequest;
import edu.pe.softroute.iamservice.interfaces.dto.SignUpRequest;
import edu.pe.softroute.iamservice.interfaces.dto.UserResponseDto;
import edu.pe.softroute.iamservice.interfaces.transform.AuthenticatedUserDtoFromEntityAssembler;
import edu.pe.softroute.iamservice.interfaces.transform.UserResponseDtoAssembler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController   {

  private final AuthService authService;

  @PostMapping("/sign-up")
  public ResponseEntity<UserResponseDto> signUp(@Valid @RequestBody SignUpRequest request) {
    UserResponseDto userResponseDto = UserResponseDtoAssembler.assemble(authService.signUp(request));

    return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
  }

  @PostMapping("/sign-in")
  public ResponseEntity<AuthenticatedUserDto> signIn(@Valid @RequestBody SignInRequest request) {
    ImmutablePair<User, String> result = authService.signIn(request);
    AuthenticatedUserDto authenticatedUserDto = AuthenticatedUserDtoFromEntityAssembler
          .toDtoFromEntity(result.getLeft(), result.getRight());

    return new ResponseEntity<>(authenticatedUserDto, HttpStatus.OK);
  }

  @GetMapping("/validate")
  public JwtInfoDto validateToken(@RequestParam String token) {
    return authService.validateToken(token);
  }

  @GetMapping("/access")
  public String access() {
      return "You have access to this service";
  }

}
