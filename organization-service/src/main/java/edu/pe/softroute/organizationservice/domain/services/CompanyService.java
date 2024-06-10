package edu.pe.softroute.organizationservice.domain.services;

import edu.pe.softroute.organizationservice.domain.models.entities.Company;
import edu.pe.softroute.organizationservice.domain.models.entities.Employee;
import edu.pe.softroute.organizationservice.interfaces.dto.CompanyRequest;
import java.util.List;
import java.util.UUID;

public interface CompanyService {

  Company create(String userId, CompanyRequest request);

  List<Employee> getAllEmployeesByCompanyId(UUID id);
}
