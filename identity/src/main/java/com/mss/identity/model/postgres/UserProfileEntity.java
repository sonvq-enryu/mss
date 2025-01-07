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
@Table(name = "user_profile")
public class UserProfileEntity extends AbstractAuditEntity {
    @Id
    @UuidGenerator
    private UUID id;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String address;
    @Column(length = 15)
    private String phoneNumber;
    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "smallint")
    private Gender gender;

    @OneToOne(mappedBy = "profile")
    public UserEntity user;
}
