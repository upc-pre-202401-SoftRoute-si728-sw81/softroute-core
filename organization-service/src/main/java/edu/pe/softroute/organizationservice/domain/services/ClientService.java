package edu.pe.softroute.organizationservice.domain.services;

import edu.pe.softroute.organizationservice.domain.models.entities.Client;
import java.util.List;
import java.util.UUID;

public interface ClientService {

  List<Client> getAll(UUID companyId);

  Client getById(UUID id);

  List<Client> getAllByIds(List<UUID> ids);
}
