package com.disorder.authentication.jwt.Repository;

import com.disorder.authentication.jwt.Entity.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JwtTokenRepository extends JpaRepository<JwtToken, Integer> {

    Optional<JwtToken> findByToken(String token);
    @Query(value = "SELECT t FROM Jwt_token t INNER JOIN User u ON t.user.id = u.id WHERE u.id = :id AND (t.expired = false OR t.revoked = false)",nativeQuery = true)
    List<JwtToken> findAllValidTokenByUser(Integer id);
}
