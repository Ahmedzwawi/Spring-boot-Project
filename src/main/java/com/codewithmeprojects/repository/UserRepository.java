package com.codewithmeprojects.repository;

import com.codewithmeprojects.entity.user;
import com.codewithmeprojects.enums.userRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<user,Long> {



    Optional<user> findFirstByEmail(String email);

    user findByUserRole(userRole userRole);

    boolean existsByEmail(String email);
}
