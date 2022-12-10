package com.company.opentalk.repository;

import com.company.opentalk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUsersByEmail(String email);
}
