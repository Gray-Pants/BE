package com.poku.graypants.domain.store.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findByStoreName(String StoreName);
}
