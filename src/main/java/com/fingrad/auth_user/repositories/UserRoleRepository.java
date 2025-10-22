package com.fingrad.auth_user.repositories;

import com.fingrad.auth_user.entities.UserRole;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {

  Optional<UserRole> findByRole_Name(String user);
}