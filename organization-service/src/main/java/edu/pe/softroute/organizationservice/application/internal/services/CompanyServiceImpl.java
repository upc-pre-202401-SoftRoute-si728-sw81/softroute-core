package edu.pe.softroute.organizationservice.application.internal.services;

import edu.pe.softroute.organizationservice.domain.models.entities.Company;
import edu.pe.softroute.organizationservice.domain.models.entities.Employee;
import edu.pe.softroute.organizationservice.domain.services.CompanyService;
import edu.pe.softroute.organizationservice.domain.services.EmployeeService;
import edu.pe.softroute.organizationservice.infrastructure.persistence.jpa.repositories.CompanyRepository;
import edu.pe.softroute.organizationservice.interfaces.dto.CompanyRequest;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

  private final EmployeeService employeeService;

  private final CompanyRepository companyRepository;

  @Override
  public Company create(String userId, CompanyRequest request) {

    Company company = Company.builder()
        .ruc(request.getRuc())
        .name(request.getName())
        .createdBy(UUID.fromString(userId))
        .build();

    return companyRepository.save(company);
  }

  @Override
  public List<Employee> getAllEmployeesByCompanyId(UUID id) {
    return employeeService.findAllByCompanyId(id);
  }

}
