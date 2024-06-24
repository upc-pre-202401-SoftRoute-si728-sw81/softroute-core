package edu.pe.softroute.organizationservice.interfaces;

import edu.pe.softroute.organizationservice.application.internal.outboundservices.ExternalUserService;
import edu.pe.softroute.organizationservice.application.internal.outboundservices.dto.UserResponseDto;
import edu.pe.softroute.organizationservice.domain.models.entities.Employee;
import edu.pe.softroute.organizationservice.domain.services.EmployeeService;
import edu.pe.softroute.organizationservice.interfaces.dto.EmployeeResponseDto;
import edu.pe.softroute.organizationservice.interfaces.transform.EmployeeResponseDtoAssembler;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

  private final EmployeeService employeeService;

  private final ExternalUserService externalUserService;

  @GetMapping("{id}")
  public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable UUID id) {
    Employee resource = employeeService.getById(id);
    UserResponseDto userResponse = externalUserService.getById(resource.getUserId());
    EmployeeResponseDto response = EmployeeResponseDtoAssembler.assemble(resource, userResponse);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees(@RequestHeader String companyId) {
    List<Employee> employees = employeeService.getAllByCompanyId(UUID.fromString(companyId));
    List<UUID> ids = employees.stream().map(Employee::getUserId).toList();

    List<UserResponseDto> userResponse = externalUserService.getAll(companyId).stream().filter(user -> ids.contains(user.getId())).toList();
    Map<UUID, UserResponseDto> resourceMap = userResponse.stream().collect(Collectors.toMap(UserResponseDto::getId, c -> c));
    List<EmployeeResponseDto> response = EmployeeResponseDtoAssembler.assemble(employees, resourceMap);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
