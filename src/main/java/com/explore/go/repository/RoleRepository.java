package com.explore.go.repository;

import com.explore.go.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findRoleByRoleKey(Long roleKey);

}
