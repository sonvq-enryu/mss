package com.mss.identity.model.postgres;

import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

@Getter
@Setter(AccessLevel.PRIVATE)
@MappedSuperclass
public class AbstractAuditEntity {
    @CreationTimestamp
    private ZonedDateTime createdAt;
}
