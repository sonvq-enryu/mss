package com.mss.identity.model.postgres;

import com.mss.identity.model.enumeration.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@Table(schema = "public", name = "user_profile")
public class UserProfileEntity extends AbstractAuditEntity {
    @Id
    @UuidGenerator
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private UserEntity user;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    @Enumerated(EnumType.ORDINAL)
    private Gender gender;
    private String phoneNumber;
    private String address;
}
