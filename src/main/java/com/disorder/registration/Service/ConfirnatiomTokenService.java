package com.disorder.registration.Service;

import com.disorder.registration.Entity.ConfirmationToken;
import com.disorder.registration.Repository.ConfirmationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfirnatiomTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;
    @Transactional
    public void saveConfirmationToken(ConfirmationToken token){
        confirmationTokenRepository.save(token);
    }
    public Optional<ConfirmationToken> findByToken(String token){
        return confirmationTokenRepository.findByToken(token);
    }

    public void setConfirmTime(String token) {
        confirmationTokenRepository.setConfirmTime(token, LocalDateTime.now());
    }
}
