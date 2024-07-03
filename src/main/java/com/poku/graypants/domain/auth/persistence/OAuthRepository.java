package com.poku.graypants.domain.auth.persistence;

import com.poku.graypants.domain.user.persistence.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthRepository extends JpaRepository<OAuth, Long> {
    Optional<OAuth> findByoAuthId(String oAuthId);
}
