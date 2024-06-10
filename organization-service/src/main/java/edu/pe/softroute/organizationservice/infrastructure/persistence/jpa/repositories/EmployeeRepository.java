package edu.pe.softroute.organizationservice.infrastructure.persistence.jpa.repositories;

import edu.pe.softroute.organizationservice.domain.models.entities.Employee;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

  List<Employee> findByCompanyId(UUID companyId);
}
