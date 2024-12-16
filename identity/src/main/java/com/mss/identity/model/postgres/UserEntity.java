package com.mss.identity.model.postgres;

import com.mss.identity.model.enumeration.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(schema = "public", name = "user")
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
    private String salt;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
