package com.example.wesocial1_0.repositories;

import com.example.wesocial1_0.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
