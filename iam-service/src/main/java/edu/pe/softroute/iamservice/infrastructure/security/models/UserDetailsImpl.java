package edu.pe.softroute.iamservice.infrastructure.security.models;

import edu.pe.softroute.iamservice.domain.models.entities.Role;
import edu.pe.softroute.iamservice.domain.models.entities.User;
import java.util.Collection;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@EqualsAndHashCode
public class UserDetailsImpl implements UserDetails {

  private final String username;

  private final String password;

  private final boolean accountNonExpired;

  private final boolean accountNonLocked;

  private final boolean credentialsNonExpired;

  private final boolean enabled;

  private final Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
    this.username = username;
    this.password = password;
    this.accountNonExpired = true;
    this.accountNonLocked = true;
    this.credentialsNonExpired = true;
    this.enabled = true;
    this.authorities = authorities;
  }

  public static UserDetailsImpl build(User user) {
    var authorities = user.getRoles().stream()
        .map(Role::getStringName)
        .map(SimpleGrantedAuthority::new)
        .toList();
    return new UserDetailsImpl(user.getEmail(), user.getPassword(), authorities);
  }

}
