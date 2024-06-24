package edu.pe.softroute.organizationservice.domain.services;

import edu.pe.softroute.organizationservice.domain.models.entities.Employee;
import java.util.List;
import java.util.UUID;

public interface EmployeeService {

  List<Employee> getAllByCompanyId(UUID companyId);

  Employee getById(UUID id);
}
