package edu.pe.softroute.organizationservice.application.internal.services;

import edu.pe.softroute.organizationservice.domain.exceptions.EmployeeNotFoundException;
import edu.pe.softroute.organizationservice.domain.models.entities.Employee;
import edu.pe.softroute.organizationservice.domain.services.EmployeeService;
import edu.pe.softroute.organizationservice.infrastructure.persistence.jpa.repositories.EmployeeRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;

  @Override
  public List<Employee> getAllByCompanyId(UUID companyId) {
    return employeeRepository.findByCompanyId(companyId);
  }

  @Override
  public Employee getById(UUID id) {
    return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
  }
}
