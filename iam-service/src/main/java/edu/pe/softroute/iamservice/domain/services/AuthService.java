package edu.pe.softroute.iamservice.domain.services;

import edu.pe.softroute.iamservice.domain.models.entities.User;
import edu.pe.softroute.iamservice.infrastructure.jwt.models.JwtInfoDto;
import edu.pe.softroute.iamservice.interfaces.dto.SignInRequest;
import edu.pe.softroute.iamservice.interfaces.dto.SignUpRequest;
import org.apache.commons.lang3.tuple.ImmutablePair;

public interface AuthService {

  User signUp(SignUpRequest request);

  ImmutablePair<User, String> signIn(SignInRequest request);

  JwtInfoDto validateToken(String token);
}
