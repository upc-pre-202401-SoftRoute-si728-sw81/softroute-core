package edu.pe.softroute.iamservice.application.internal.services;

import edu.pe.softroute.iamservice.domain.exception.UserNotFoundException;
import edu.pe.softroute.iamservice.domain.models.entities.User;
import edu.pe.softroute.iamservice.domain.services.UserService;
import edu.pe.softroute.iamservice.infrastructure.persistence.jpa.repositories.UserRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public User findByEmail(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new UserNotFoundException(email));
  }

  @Override
  public List<User> findAllByIds(List<UUID> ids) {
    return userRepository.findAllById(ids);
  }

  @Override
  public User assignCompany(UUID id, UUID companyId) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException(id));

    user.setCompanyId(companyId);

    return userRepository.save(user);
  }

}
