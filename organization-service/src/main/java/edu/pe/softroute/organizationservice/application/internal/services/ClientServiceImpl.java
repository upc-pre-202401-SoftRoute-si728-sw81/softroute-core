package edu.pe.softroute.organizationservice.application.internal.services;

import edu.pe.softroute.organizationservice.domain.ClientNotFoundException;
import edu.pe.softroute.organizationservice.domain.models.entities.Client;
import edu.pe.softroute.organizationservice.domain.services.ClientService;
import edu.pe.softroute.organizationservice.infrastructure.persistence.jpa.repositories.ClientRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl  implements ClientService {

  private final ClientRepository clientRepository;

  @Override
  public List<Client> getAll(UUID companyId) {
    return clientRepository.findAllByCompanyId(companyId);
  }

  @Override
  public Client getById(UUID id) {
    return clientRepository.findById(id)
        .orElseThrow(() -> new ClientNotFoundException(id));
  }

  @Override
  public List<Client> getAllByIds(List<UUID> ids) {
    return clientRepository.findAllById(ids);
  }

}
