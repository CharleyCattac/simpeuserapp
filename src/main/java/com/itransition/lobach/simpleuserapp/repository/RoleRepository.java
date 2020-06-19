package com.itransition.lobach.simpleuserapp.repository;

import com.itransition.lobach.simpleuserapp.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getRoleById(Long id);
    Role getRoleByRole(String role);
}
