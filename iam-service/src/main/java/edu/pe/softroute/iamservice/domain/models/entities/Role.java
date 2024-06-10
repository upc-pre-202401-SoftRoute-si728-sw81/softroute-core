package edu.pe.softroute.iamservice.domain.models.entities;

import edu.pe.softroute.iamservice.domain.models.enums.Roles;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private Roles name;

  public Role(Roles name) {
    this.name = name;
  }

  public String getStringName() {
    return name.name();
  }

  public static Role getDefaultRole() {
    return new Role(Roles.ROLE_USER);
  }

  public static Role toRoleFromName(String name) {
    return new Role(Roles.valueOf(name));
  }

  public static List<Role> validateRoleSet(List<Role> roles) {
    if (roles == null || roles.isEmpty()) {
      return List.of(getDefaultRole());
    }
    return roles;
  }
}
