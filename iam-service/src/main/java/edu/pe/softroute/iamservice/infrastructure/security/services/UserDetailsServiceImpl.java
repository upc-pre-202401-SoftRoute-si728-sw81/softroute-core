package edu.pe.softroute.iamservice.infrastructure.security.services;

import edu.pe.softroute.iamservice.domain.models.entities.User;
import edu.pe.softroute.iamservice.infrastructure.persistence.jpa.repositories.UserRepository;
import edu.pe.softroute.iamservice.infrastructure.security.models.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

    return UserDetailsImpl.build(user);
  }
}
