package com.poku.graypants.domain.item.persistence;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findById(Long id);
    List<Item> findAllByItemNameContaining(String itemName, Sort sort);
    List<Item> findAllByCategory(Category category, Sort sort);
    List<Item> findByStore_StoreId(Long storeId);
}
