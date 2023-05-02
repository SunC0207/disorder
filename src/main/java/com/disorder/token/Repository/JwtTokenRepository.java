package com.disorder.token.Repository;

import com.disorder.token.Entity.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JwtTokenRepository extends JpaRepository<JwtToken, Integer> {

    Optional<JwtToken> findByToken(String token);
    @Query(value = "SELECT t.id, expired, revoked, token, token_type, user_id FROM Jwt_token t JOIN user u ON t.user_id = u.id WHERE u.id = :id AND (t.expired = false OR t.revoked = false)",nativeQuery = true)
    List<JwtToken> findAllValidTokenByUser(Integer id);
}
