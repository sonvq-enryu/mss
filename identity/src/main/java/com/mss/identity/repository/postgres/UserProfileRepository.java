package com.mss.identity.repository.postgres;

import com.mss.identity.model.postgres.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserProfileRepository extends JpaRepository<UserProfileEntity, UUID> {

}
