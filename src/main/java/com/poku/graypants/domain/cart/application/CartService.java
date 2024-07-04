package com.poku.graypants.domain.cart.application;

import com.poku.graypants.domain.cart.persistence.Cart;
import com.poku.graypants.domain.cart.persistence.CartItem;
import com.poku.graypants.domain.cart.persistence.CartItemRepository;
import com.poku.graypants.domain.cart.persistence.CartRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ItemRepository itemRepository; // Item 엔티티를 위한 레포지토리

    public Cart createCart() {
        return cartRepository.save(new Cart());
    }

    public CartItem addItemToCart(Long cartId, Long itemId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("장바구니가 존재 하지 않습니다."));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("상품이 존재 하지 않습니다."));

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .item(item)
                .cartItemQuantity(quantity)
                .build();

        return cartItemRepository.save(cartItem);
    }

    @Transactional(readOnly = true)
    public List<CartItem> getCartItems(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("장바구니를 찾을 수 없습니다."));
        return cartItemRepository.findByCart(cart);
    }

    public void removeItemFromCart(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    public void updateCartItemQuantity(Long cartItemId, int newQuantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException("상품을 찾을 수 없습니다."));
        cartItem.updateQuantity(newQuantity);
        cartItemRepository.save(cartItem);
    }
}
