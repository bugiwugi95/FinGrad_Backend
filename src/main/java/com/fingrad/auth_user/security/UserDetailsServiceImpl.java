package com.fingrad.auth_user.security;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final com.fingrad.auth_user.repositories.UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {

    com.fingrad.auth_user.entities.User user = userRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

    List<SimpleGrantedAuthority> authorities = user.getUserRoles().stream()
        .map(userRole -> new SimpleGrantedAuthority("ROLE_" + userRole.getRole().getName()))
        .toList();

    return new org.springframework.security.core.userdetails.User(
        user.getEmail(),
        user.getPassword_hash(),
        authorities
    );
  }
}
