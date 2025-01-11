package com.mss.identity.repository.postgres;

import com.mss.identity.model.postgres.InvalidToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InvalidTokenRepository extends JpaRepository<InvalidToken, UUID> {
}
