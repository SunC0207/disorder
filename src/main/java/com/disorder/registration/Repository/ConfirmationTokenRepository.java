package com.disorder.registration.Repository;

import com.disorder.registration.Entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer> {
    Optional<ConfirmationToken> findByToken(String token);
    @Transactional
    @Modifying
    @Query(value = "UPDATE Confirmation_Token c SET c.confirm_Time = :confirmTime WHERE c.token = :token",nativeQuery = true)
    void setConfirmTime(String token, LocalDateTime confirmTime);


}
