package com.poku.graypants.domain.addr.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddrRepository extends JpaRepository<UserAddr, Long> {
    Optional<UserAddr> findById(Long userAddrId);
}
