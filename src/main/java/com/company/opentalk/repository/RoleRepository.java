package com.company.opentalk.repository;

import com.company.opentalk.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query(value = "select r from Role r where r.roleName=?1")
    Role findByRoleName(String roleName);

}
