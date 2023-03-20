package edu.cpp.CYS;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.cpp.CYS.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
