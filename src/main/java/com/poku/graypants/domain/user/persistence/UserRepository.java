package com.poku.graypants.domain.user.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository User 엔티티의 JPA Repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 이메일로 유저 정보를 조회하는 메소드
     *
     * @param email String
     * @return Optional<User>
     */
    Optional<User> findByEmail(String email);
}