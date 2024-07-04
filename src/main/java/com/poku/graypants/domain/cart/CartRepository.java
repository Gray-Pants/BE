package com.poku.graypants.domain.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *  CartRepository Cart 엔티티의 JPA Repository
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    /**
     *
     * @param cartId
     * @return Optional<Cart>
     */
    Optional<Cart> findByCartId(Long cartId);
}
