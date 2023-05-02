package com.disorder.user.Repository;

import com.disorder.user.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
    @Transactional
    @Modifying
    @Query(value = "UPDATE User u SET u.enable = true WHERE u.email = :email",nativeQuery = true)
    void enableUser(String email);
}
