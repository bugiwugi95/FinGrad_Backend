package com.fingrad.auth_user.repositories;

import com.fingrad.auth_user.entities.Role;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByNameIgnoreCase(String name);
}