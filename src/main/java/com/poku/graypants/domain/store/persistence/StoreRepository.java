package com.poku.graypants.domain.store.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findByStoreName(String StoreName);
    Optional<Store> findByStoreEmail(String StoreEmail);
}
