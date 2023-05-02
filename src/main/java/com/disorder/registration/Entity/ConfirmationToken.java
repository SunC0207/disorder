package com.disorder.registration.Entity;

import com.disorder.user.Entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class ConfirmationToken {
    @Id
    @GeneratedValue
    private Integer id;
    private String token;
    private LocalDateTime createTime;
    private LocalDateTime expireTime;
    private LocalDateTime confirmTime;
    @ManyToOne
    @JoinColumn(nullable = false,name = "user")
    private User user;
}
