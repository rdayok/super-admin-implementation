package com.rid.superadminimplementation.data.repositories;

import com.rid.superadminimplementation.data.models.Role;
import com.rid.superadminimplementation.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByRole(Role role);
}
