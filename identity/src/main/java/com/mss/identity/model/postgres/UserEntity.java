package com.mss.identity.model.postgres;

import com.mss.identity.model.enumeration.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "account")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends AbstractAuditEntity {
    @Id
    @UuidGenerator
    private UUID id;
    @Column(nullable = false, unique = true, updatable = false)
    private String username;
    @Column(nullable = false)
    private String hashPassword;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    public UserProfileEntity profile;
}
