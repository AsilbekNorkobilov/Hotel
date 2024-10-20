package org.example.hotelmanagementsystem.repo;


import org.example.hotelmanagementsystem.entity.Role;
import org.example.hotelmanagementsystem.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findRoleByRoleName(RoleName roleName);


}