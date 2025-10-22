package com.fingrad.auth_user.services.impl;

import com.fingrad.auth_user.dto.request.RegistrationRequest;
import com.fingrad.auth_user.dto.response.MessageResponse;
import com.fingrad.auth_user.entities.User;
import com.fingrad.auth_user.entities.UserRole;
import com.fingrad.auth_user.entities.UserRoleId;
import com.fingrad.auth_user.repositories.RoleRepository;
import com.fingrad.auth_user.repositories.UserRepository;
import com.fingrad.auth_user.repositories.UserRoleRepository;
import com.fingrad.auth_user.security.JwtToken;
import com.fingrad.auth_user.services.AuthUserService;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthUserServiceImpl implements AuthUserService {

  private final UserRepository userRepository;

  private final UserRoleRepository userRoleRepository;

  private final RoleRepository roleRepository;

  private final UserDetailsService userDetailsService;

  private final JwtToken jwtToken;

  private final PasswordEncoder passwordEncoder;


  @Override
  @Transactional
  public MessageResponse registrationUser(RegistrationRequest request) {

    if (userRepository.existsByEmail(request.email())) {
      log.warn("Попытка регистрации существующего пользователя: {}", request.email());
      throw new RuntimeException("Пользователь с таким именем уже существует");
    }

    var role = roleRepository.findByNameIgnoreCase("USER")
        .orElseThrow(() -> {
          log.error("Роль USER не найдена при регистрации пользователя {}", request.email());
          return new RuntimeException("Роль USER не найдена");
        });

    var user = User.builder()
        .email(request.email())
        .phone(request.phone())
        .password_hash(passwordEncoder.encode(request.password()))
        .created_at(Timestamp.valueOf(LocalDateTime.now()))
        .build();

    userRepository.save(user);

    UserRole userRole = new UserRole();
    UserRoleId id = new UserRoleId();
    id.setUserId(user.getId());
    id.setRoleId(role.getId());
    userRole.setId(id);
    userRole.setUser(user);
    userRole.setRole(role);

    user.setUserRoles(Collections.singleton(userRole));
    userRoleRepository.save(userRole);
    log.info("Зарегистрирован новый пользователь: {}", user.getEmail());

    UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());

    return new MessageResponse("Вы успешно зарегистрировались.",
        jwtToken.generatorToken(userDetails));
  }
}
