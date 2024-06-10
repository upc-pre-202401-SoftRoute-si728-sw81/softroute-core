package edu.pe.softroute.iamservice.application.internal.services;

import edu.pe.softroute.iamservice.domain.exception.InvalidPasswordException;
import edu.pe.softroute.iamservice.domain.exception.ResourceValidationException;
import edu.pe.softroute.iamservice.domain.models.entities.Role;
import edu.pe.softroute.iamservice.domain.models.entities.User;
import edu.pe.softroute.iamservice.domain.services.AuthService;
import edu.pe.softroute.iamservice.infrastructure.jwt.models.JwtInfoDto;
import edu.pe.softroute.iamservice.infrastructure.jwt.services.JwtService;
import edu.pe.softroute.iamservice.infrastructure.persistence.jpa.repositories.RoleRepository;
import edu.pe.softroute.iamservice.infrastructure.persistence.jpa.repositories.UserRepository;
import edu.pe.softroute.iamservice.interfaces.dto.SignInRequest;
import edu.pe.softroute.iamservice.interfaces.dto.SignUpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;

  private final RoleRepository roleRepository;

  private final PasswordEncoder passwordEncoder;

  private final AuthenticationManager authenticationManager;

  private final JwtService jwtService;

  @Override
  public User signUp(SignUpRequest request) {

    if (userRepository.existsByEmail(request.getEmail())) {
      throw new ResourceValidationException("User with " + request.getEmail() + " already exists");
    }

    List<Role> rolesFromRequest = request.getRoles() != null ? request.getRoles().stream()
        .map(Role::toRoleFromName)
        .toList() : Role.validateRoleSet(new ArrayList<>());

    List<Role> roles = rolesFromRequest.stream()
        .map(role -> roleRepository.findByName(role.getName())
        .orElseThrow(() -> new RuntimeException("Role name not found"))).toList();

    String passwordEncoded = passwordEncoder.encode(request.getPassword());

    User user = new User(request.getEmail(), passwordEncoded, roles);

    return userRepository.save(user);
  }

  @Override
  public ImmutablePair<User, String > signIn(SignInRequest request) {

    Optional<User> user = userRepository.findByEmail(request.getEmail());

    if (user.isEmpty()) {
      throw new ResourceValidationException("User with " + request.getEmail() + " does not exist");
    }

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

    if (authentication.isAuthenticated()) {

      String token = jwtService.generateToken(user.get());

      return new ImmutablePair<>(user.get(), token);
    } else {
      throw new InvalidPasswordException("Invalid Password");
    }
  }

  @Override
  public JwtInfoDto validateToken(String token) {

    if (!jwtService.validateToken(token)) {
      throw new RuntimeException("Invalid Token");
    }
    return jwtService.getTokenDto(token);

  }
}
