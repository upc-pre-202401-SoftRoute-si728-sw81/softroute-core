package edu.pe.softroute.iamservice.domain.services;

import edu.pe.softroute.iamservice.domain.models.entities.User;
import java.util.List;
import java.util.UUID;

public interface UserService {

  User findByEmail(String email);

  List<User> findAllByIds(List<UUID> ids);

  User assignCompany(UUID id, UUID companyId);
}
